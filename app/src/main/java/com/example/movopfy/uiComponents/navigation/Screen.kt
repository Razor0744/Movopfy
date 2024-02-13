package com.example.movopfy.uiComponents.navigation

const val DETAILS_ID = "id"
const val MOVIES_CATEGORY = "category"

sealed class Screen(val route: String) {

    data object Home : Screen(route = "home_screen")

    data object Details : Screen(route = "details_screen/{$DETAILS_ID}") {
        fun passId(id: Int): String {
            return this.route.replace(oldValue = "{$DETAILS_ID}", newValue = id.toString())
        }
    }

    data object Movies : Screen(route = "movies_screen/{$MOVIES_CATEGORY}") {
        fun passCategory(category: String): String {
            return this.route.replace(oldValue = "{$MOVIES_CATEGORY}", newValue = category)
        }
    }
}