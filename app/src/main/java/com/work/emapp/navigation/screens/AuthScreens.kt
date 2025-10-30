package com.work.emapp.navigation.screens

sealed class AuthScreens(val route: String) {
    object Registration : AuthScreens("registration")
}