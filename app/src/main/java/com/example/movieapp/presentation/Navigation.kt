package com.example.movieapp.presentation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.Screen

@ExperimentalMaterialApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)

        }
        composable(route = Screen.DetailsScreen.route+"/{id}",
        arguments = listOf(navArgument("id"){
            type = NavType.IntType
            defaultValue=0
        })
        ) {  entry->
            DetailsScreen(entry.arguments?.getInt("id"))
        }
    }
}
