package com.work.emapp.ui.bottom

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.work.emapp.navigation.graph.BottomNavGraph
import com.work.uikit.common.DarkGray

@Composable
fun BottomNavScreen(rootNavController: NavHostController) {
    val navController = rememberNavController()
    ChangeNavigationBarColor(color = DarkGray)
    ChangeStatusBarColor(color = Color.Black)
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        },
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            BottomNavGraph(
                navController = navController,
                rootNavController = rootNavController
            )
        }
    }
}

@Composable
fun ChangeStatusBarColor(color: Color, isIconsLight: Boolean = true) {
    val view = LocalView.current
    SideEffect {
        val window = view.context.findActivity().window
        window.statusBarColor = color.toArgb()
        WindowCompat.getInsetsController(window, view)?.isAppearanceLightStatusBars = !isIconsLight
    }
}


@Composable
fun ChangeNavigationBarColor(color: Color) {
    val view = LocalView.current
    SideEffect {
        val window = view.context.findActivity().window
        window.navigationBarColor = color.toArgb()
    }
}

fun Context.findActivity(): Activity = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> throw IllegalStateException("Context is not an Activity")
}