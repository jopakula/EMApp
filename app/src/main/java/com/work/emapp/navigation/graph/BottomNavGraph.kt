package com.work.emapp.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.work.emapp.navigation.Graph
import com.work.emapp.navigation.screens.BottomBarScreens
import com.work.emapp.navigation.screens.HomeScreens
import com.work.emapp.ui.account.AccountScreen
import com.work.emapp.ui.favorites.FavoritesScreen
import com.work.emapp.ui.home.CardDetailScreen
import com.work.emapp.ui.home.HomeScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    rootNavController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreens.Home.route,
        route = Graph.BOTTOM_NAV
    ) {
        navigation(
            route = BottomBarScreens.Home.route,
            startDestination = HomeScreens.Home.route
        ) {
            composable(route = HomeScreens.Home.route) {
                HomeScreen(
                    onCardClick = { navController.navigate(HomeScreens.CardDetail.route) }
                )
            }
            composable(route = HomeScreens.CardDetail.route) {
                CardDetailScreen(

                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(route = BottomBarScreens.Favorites.route) {
            FavoritesScreen()
        }
        composable(route = BottomBarScreens.Account.route) {
            AccountScreen(
                onLogOutClick = {
                    rootNavController.navigate(Graph.AUTH) {
                        popUpTo(Graph.BOTTOM_NAV) { inclusive = true }
                    }
                }
            )
        }
    }
}