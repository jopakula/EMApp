package com.work.emapp.navigation.screens

import com.work.emapp.R

sealed class BottomBarScreens(
    val route: String,
    val title: String,
    val iconResId: Int
) {
    object Home : BottomBarScreens(
        route = "home",
        title = "Главная",
        iconResId = R.drawable.ic_launcher_background
    )
    object Favorites : BottomBarScreens(
        route = "favorites",
        title = "Избранное",
        iconResId = R.drawable.ic_launcher_background
    )
    object Account : BottomBarScreens(
        route = "account",
        title = "Аккаунт",
        iconResId = R.drawable.ic_launcher_background
    )
}