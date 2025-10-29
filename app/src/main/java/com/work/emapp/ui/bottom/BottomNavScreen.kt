package com.work.emapp.ui.bottom

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.work.emapp.navigation.graph.BottomNavGraph

@Composable
fun BottomNavScreen(rootNavController: NavHostController) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        },
//        containerColor = Color(0xFFFCE4EC)
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            BottomNavGraph(
                navController = navController,
                rootNavController = rootNavController
            )
        }
    }
}