package com.example.alejandroexamen.navegacion

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.alejandroexamen.MVVM.JugadoresScreen
import com.example.malaga.screens.LoginScreenUI
import com.google.firebase.auth.FirebaseAuth

@Composable
fun GestionNavegacion(auth: FirebaseAuth){

    val pilaNavegacion = remember { mutableStateListOf<Any>(Routes.Home) }

    NavDisplay(
        backStack = pilaNavegacion,
        onBack = { pilaNavegacion.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is Routes.Login -> NavEntry(key) {
                    LoginScreenUI(
                        auth = auth,
                        onLoginOk = {
                            pilaNavegacion.clear()
                            pilaNavegacion.add(Routes.Home)
                        },
                        onRegisterClick = {pilaNavegacion.add(Routes.Home)}
                    )
                }
                is Routes.Home -> NavEntry(key)
                {
                    JugadoresScreen(onNavigateToDetail = { id ->
                        pilaNavegacion.add(Routes.Home(id))
                    })
                }
                else -> NavEntry(key) {
                    Text("Página no encontrada")
                }
            }
        }


    )

}