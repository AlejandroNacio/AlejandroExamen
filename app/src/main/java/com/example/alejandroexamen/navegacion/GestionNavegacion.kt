package com.example.alejandroexamen.navegacion

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.malaga.screens.LoginScreenUI
import com.google.firebase.auth.FirebaseAuth

@Composable
fun GestionNavegacion(auth: FirebaseAuth){

    val pilaNavegacion = rememberNavBackStack(Routes.Login)

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
                        }
                    )
                }
                else -> NavEntry(key) {
                    Text("Página no encontrada")
                }
            }
        }
    )

}