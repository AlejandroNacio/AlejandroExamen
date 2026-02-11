package com.example.alejandroexamen.navegacion

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes: NavKey {

    @Serializable
    data class Login : Routes()

    @Serializable
    data class Home : Routes()
}
