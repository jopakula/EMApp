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
        iconResId = R.drawable.house
    )
    object Favorites : BottomBarScreens(
        route = "favorites",
        title = "Избранное",
        iconResId = R.drawable.bookmark
    )
    object Account : BottomBarScreens(
        route = "account",
        title = "Аккаунт",
        iconResId = R.drawable.person
    )
}