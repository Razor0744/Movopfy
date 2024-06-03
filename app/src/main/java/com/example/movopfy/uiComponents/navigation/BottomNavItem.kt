package com.example.movopfy.uiComponents.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {

    data object Home :
        BottomNavItem(
            route = Screen.Home.route,
            label = "Home",
            icon = Icons.Default.Home
        )

    data object Favorites :
        BottomNavItem(
            route = Screen.Favourite.route,
            label = "Favorites",
            icon = Icons.Default.Favorite
        )

    data object Settings : BottomNavItem(
        route = Screen.Settings.route,
        label = "Settings",
        icon = Icons.Default.Settings
    )
}