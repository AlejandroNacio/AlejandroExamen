package com.example.alejandroexamen.navegacion

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.alejandroexamen.HomeScreen
import com.example.alejandroexamen.LoginScreenUI
import com.example.alejandroexamen.MVVM.AñadirJugadoresViewModel
import com.example.alejandroexamen.MVVM.JugadoresScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun GestionNavegacion(auth: FirebaseAuth){

    val vm: AñadirJugadoresViewModel = viewModel()

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
                is Routes.Home -> NavEntry(key) {
                    HomeScreen(
                        viewModel = vm,
                        onNavigateToAdd = { pilaNavegacion.add(Routes.JugadorAñadir("")) },
                        onEditJugador = { pilaNavegacion.add(Routes.JugadorAñadir(it.idJugador)) }
                    )
                }
                is Routes.JugadorAñadir -> NavEntry(key) {
                    JugadoresScreen(
                        viewModel = vm,
                        idJugadorParaEditar = key.id
                    ) {
                        pilaNavegacion.removeLastOrNull()
                    }
                }
                else -> NavEntry(key) {
                    Text("Página no encontrada")
                }
            }
        }
    )
}