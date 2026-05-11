package com.rheivalseptian8600.asessment2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rheivalseptian8600.asessment2.ui.theme.screen.AboutScreen
import com.rheivalseptian8600.asessment2.ui.theme.screen.DetailScreen
import com.rheivalseptian8600.asessment2.ui.theme.screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable(route = "home") {
            MainScreen(navController = navController)
        }
        composable(route = "detail") {
            DetailScreen(navController = navController)
        }
        composable(route = "detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1
            DetailScreen(navController = navController, id = id)
        }
        composable(route = "about") {
            AboutScreen(navController = navController)
        }
    }
}