package com.example.mathquiz

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "questions") {
        composable("questions") {
            QuestionsScreen(navController = navController)
        }
        composable("result/{answers}") { backStackEntry ->
            val answers = backStackEntry.arguments?.getString("answers")?.split(",") ?: emptyList()
            ResultScreen(navController = navController, answers = answers)
        }
    }
}
