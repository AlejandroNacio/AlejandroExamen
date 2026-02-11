package com.example.alejandroexamen.MVVM

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color

@Composable
fun JugadoresScreen(viewModel: AñadirJugadoresViewModel = viewModel(), onNavigateToDetail: (String) -> Unit) {
    Scaffold { paddingValues ->

        val jugadores by viewModel.jugadores.collectAsState()
        var nombre by remember { mutableStateOf("") }
        var numero by remember { mutableStateOf("") }
        var nacionalidad by remember { mutableStateOf("") }
        var posicion by remember { mutableStateOf("") }
        var imagenURL by remember { mutableStateOf("") }

        var jugadorIdSeleccionado by remember { mutableStateOf<String?>(null) }

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(text = "Nuevo jugador", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre del Jugador") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = numero,
                onValueChange = { numero = it },
                label = { Text("numero ") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )

            Spacer(modifier = Modifier.height(8.dp))


            OutlinedTextField(
                value = nacionalidad,
                onValueChange = { nacionalidad = it },
                label = { Text("Posicion") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = posicion,
                onValueChange = { posicion = it },
                label = { Text("Posicion") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = imagenURL,
                onValueChange = { imagenURL = it },
                label = { Text("URL imagen") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                maxLines = 1,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {

                    if (jugadorIdSeleccionado == null) {
                        viewModel.addJugadores(nombre, numero, nacionalidad, posicion, imagenURL)
                    } else {
                        viewModel.updateJugador(
                            idJugador = jugadorIdSeleccionado!!,
                            nuevoNombre = nombre,
                            nuevoNumero = numero,
                            nuevaNacionalidad = nacionalidad,
                            nuevaPosicion = posicion,
                            nuevaUrl = imagenURL
                        )
                    }

                    nombre = ""; numero = ""; nacionalidad = ""; posicion = ""; imagenURL = ""
                    jugadorIdSeleccionado = null
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = nombre.isNotBlank() && numero.isNotBlank()
            ) {
                Text(if (jugadorIdSeleccionado == null) "Agregar Jugador" else "Guardar Cambios")
            }

            if (jugadorIdSeleccionado != null) {
                Button(
                    onClick = {
                        nombre = ""; numero = ""; nacionalidad = ""; posicion = ""; imagenURL = ""
                        jugadorIdSeleccionado = null
                    },
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF27D21F)
                    )
                ) {
                    Text("Cancelar")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}