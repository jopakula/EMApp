package com.work.emapp.navigation.screens

sealed class HomeScreens(val route: String) {
    object Home : HomeScreens("home_screen")
    object CardDetail : HomeScreens("card_detail")
}