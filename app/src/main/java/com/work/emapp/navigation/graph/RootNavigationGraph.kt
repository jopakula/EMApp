package com.work.emapp.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.work.emapp.navigation.Graph
import com.work.emapp.navigation.screens.AuthScreens
import com.work.emapp.ui.bottom.BottomNavScreen
import com.work.emapp.ui.registration.RegistrationScreen

@Composable
fun RootNavigationGraph(rootNavController: NavHostController) {
    NavHost(
        navController = rootNavController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH
    ) {
        navigation(
            route = Graph.AUTH,
            startDestination = AuthScreens.Registration.route
        ) {
            composable(route = AuthScreens.Registration.route) {
                RegistrationScreen(
                    onRegisterSuccess = {
                        rootNavController.navigate(Graph.BOTTOM_NAV) {
                            popUpTo(Graph.AUTH) { inclusive = true }
                        }
                    }
                )
            }
        }
        composable(route = Graph.BOTTOM_NAV) {
            BottomNavScreen(rootNavController = rootNavController)
        }
    }
}