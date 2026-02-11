package com.example.alejandroexamen.MVVM

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AñadirJugadoresViewModel : ViewModel() {
    private val db = Firebase.firestore
    private val jugadoresCollection = db.collection("jugadores")
    private val _jugadores = MutableStateFlow<List<Jugador>>(emptyList())
    val jugadores: StateFlow<List<Jugador>> = _jugadores

    init {
        getJugador()
    }

    fun updateJugador(idJugador: String, nuevoNombre: String, nuevoNumero: String, nuevaNacionalidad: String, nuevaPosicion : String, nuevaUrl: String) {
        val datosActualizados = mapOf(
            "nombre" to nuevoNombre,
            "numero" to nuevoNumero,
            "nacionalidad" to nuevaNacionalidad,
            "posicion" to nuevaPosicion,
            "imagen" to nuevaUrl
        )

        jugadoresCollection.document(idJugador)
            .update(datosActualizados)
            .addOnSuccessListener { Log.i("Firebase", "Actualizado con éxito") }
            .addOnFailureListener { e -> Log.e("Firebase", "Error: ${e.message}") }
    }

    private fun getJugador() {
        jugadoresCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                Log.e("Firebase", "Error en el listener: ${error.message}")
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val jugadoresList = snapshot.documents.mapNotNull { doc ->
                    val jugador = doc.toObject(Jugador::class.java)
                    jugador?.idJugador = doc.id
                    jugador
                }
                _jugadores.value = jugadoresList
            }
        }
    }

    fun addJugadores(nombre: String, numero: String, nacionalidad: String, posicion: String, imagen: String) {
        val jugador = Jugador(
            nombre = nombre,
            numero = numero,
            nacionalidad = nacionalidad,
            posicion = posicion,
            imagenURL = imagen
        )

        jugadoresCollection.add(jugador)
            .addOnSuccessListener { doc ->
                Log.i("Firebase", "Añadido con ID: ${doc.id}")
            }
            .addOnFailureListener { e ->
                Log.e("Firebase", "Error al guardar: ${e.message}")
            }
    }

    fun deleteJugador(idJugador: String) {
        jugadoresCollection.document(idJugador)
            .delete()
            .addOnSuccessListener {
                Log.i("Firebase", "Borrado correcto")
            }
            .addOnFailureListener { e ->
                Log.e("Firebase", "Error al borrar: ${e.message}")
            }
    }

    fun obtenerJugadoresPorId(id: String): Jugador? {
        return _jugadores.value.find { it.idJugador == id }
    }
}