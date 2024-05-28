package com.example.movopfy.uiComponents.navigation

const val DETAILS_ID = "id"
const val DETAILS_CATEGORY = "category"
const val MOVIES_CATEGORY = "category"
const val PLAYER_ID = "id"
const val PLAYER_EPISODE = "episode"

sealed class Screen(val route: String) {

    data object Home : Screen(route = "home_screen")

    data object Details : Screen(route = "details_screen/{$DETAILS_ID}/{$DETAILS_CATEGORY}") {
        fun passId(id: Int, category: String): String {
            return this.route
                .replace(oldValue = "{$DETAILS_ID}", newValue = id.toString())
                .replace(oldValue = "{$DETAILS_CATEGORY}", newValue = category)
        }
    }

    data object Movies : Screen(route = "movies_screen/{$MOVIES_CATEGORY}") {
        fun passCategory(category: String): String {
            return this.route.replace(oldValue = "{$MOVIES_CATEGORY}", newValue = category)
        }
    }

    data object Anime : Screen(route = "anime_screen")

    data object Player : Screen(route = "player_screen/{$PLAYER_ID}/{$PLAYER_EPISODE}") {
        fun passId(id: Int, episode: Int): String {
            return this.route
                .replace(oldValue = "{$PLAYER_ID}", newValue = id.toString())
                .replace(oldValue = "{$PLAYER_EPISODE}", newValue = episode.toString())
        }
    }

    data object Favourite : Screen(route = "favorites_screen")

    data object Search : Screen(route = "search_screen")

    data object Auth : Screen(route = "auth_screen")
}