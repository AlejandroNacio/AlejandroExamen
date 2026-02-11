package com.example.alejandroexamen.navegacion

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes: NavKey {

    @Serializable
    data class Login(val id: String) : Routes()

    @Serializable
    data class Home(val id: String): Routes()

    @Serializable
    data class JugadorAñadir(val id: String): Routes()


}
