package com.example.movopfy.uiComponents.navigation

const val DETAILS_ID = "id"
const val DETAILS_CATEGORY = "category"
const val MOVIES_CATEGORY = "category"

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
}