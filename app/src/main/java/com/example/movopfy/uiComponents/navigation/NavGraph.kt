package com.example.movopfy.uiComponents.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movopfy.features.anime.presentation.ui.AnimeScreen
import com.example.movopfy.features.auth.presentation.ui.AuthScreen
import com.example.movopfy.features.details.presentation.ui.DetailsScreen
import com.example.movopfy.features.favourite.presentation.ui.FavouriteScreen
import com.example.movopfy.features.home.presentation.ui.HomeScreen
import com.example.movopfy.features.movies.presentation.ui.MoviesScreen
import com.example.movopfy.features.player.presentation.ui.PlayerScreen
import com.example.movopfy.features.search.presentation.ui.SearchScreen
import com.example.movopfy.firebase.user.UserManager

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    userManager: UserManager
) {
    val startRoute = if (userManager.getCurrentUser() != null) Screen.Home.route else Screen.Auth.route

    NavHost(
        navController = navController,
        startDestination = startRoute
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

        composable(route = Screen.Favourite.route) {
            FavouriteScreen(
                modifier = Modifier.padding(paddingValues),
                navController = navController
            )
        }

        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }

        composable(route = Screen.Auth.route) {
            AuthScreen(
                modifier = Modifier.padding(paddingValues = paddingValues),
                navController = navController
            )
        }
    }
}