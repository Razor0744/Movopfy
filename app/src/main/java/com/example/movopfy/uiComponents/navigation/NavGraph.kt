package com.example.movopfy.uiComponents.navigation

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movopfy.features.anime.presentation.ui.AnimeScreen
import com.example.movopfy.features.details.presentation.ui.DetailsScreen
import com.example.movopfy.features.home.presentation.ui.HomeScreen
import com.example.movopfy.features.movies.presentation.ui.MoviesScreen
import com.example.movopfy.features.player.presentation.ui.PlayerScreen

@OptIn(UnstableApi::class)
@Composable
fun SetupNavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(modifier = Modifier.padding(paddingValues), navController = navController)
        }

        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument(DETAILS_ID) { type = NavType.IntType },
                navArgument(DETAILS_CATEGORY) { type = NavType.StringType }
            )
        ) {
            val id = it.arguments?.getInt(DETAILS_ID) ?: 0
            val category = it.arguments?.getString(DETAILS_CATEGORY) ?: "null"
            DetailsScreen(
                id = id,
                category = category,
                navController = navController
            )
        }

        composable(
            route = Screen.Movies.route,
            arguments = listOf(navArgument(MOVIES_CATEGORY) { type = NavType.StringType })
        ) {
            val category = it.arguments?.getString(MOVIES_CATEGORY) ?: ""
            MoviesScreen(
                modifier = Modifier.padding(paddingValues),
                navController = navController,
                category = category
            )
        }

        composable(route = Screen.Anime.route) {
            AnimeScreen(
                modifier = Modifier.padding(paddingValues),
                navController = navController
            )
        }

        composable(
            route = Screen.Player.route,
            arguments = listOf(
                navArgument(PLAYER_ID) { type = NavType.IntType },
                navArgument(PLAYER_EPISODE) { type = NavType.IntType }
            )
        ) {
            val id = it.arguments?.getInt(PLAYER_ID) ?: 0
            val episode = it.arguments?.getInt(PLAYER_EPISODE) ?: 0
            PlayerScreen(id = id, episode = episode)
        }
    }
}