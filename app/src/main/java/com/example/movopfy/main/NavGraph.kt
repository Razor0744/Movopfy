package com.example.movopfy.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movopfy.features.home.presentation.HomeScreen
import com.example.movopfy.features.title.presentation.TitleScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Title.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            val id = it.arguments?.getInt("id")
            TitleScreen(id = id!!)
        }
    }
}