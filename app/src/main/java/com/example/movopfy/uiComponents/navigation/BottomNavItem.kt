package com.example.movopfy.uiComponents.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {

    data object Home :
        BottomNavItem(
            route = "home_screen",
            label = "Home",
            icon = Icons.Default.Home
        )

    data object Favorites :
        BottomNavItem(
            route = "favorites_screen",
            label = "Favorites",
            icon = Icons.Default.Favorite
        )
}