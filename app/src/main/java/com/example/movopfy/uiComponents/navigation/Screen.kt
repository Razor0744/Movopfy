package com.example.movopfy.uiComponents.navigation

const val DETAILS_ID = "id"

sealed class Screen(val route: String) {

    data object Home : Screen(route = "home_screen")

    data object Details : Screen(route = "details_screen/{$DETAILS_ID}") {
        fun passId(id: Int): String {
            return this.route.replace(oldValue = "{$DETAILS_ID}", newValue = id.toString())
        }
    }
}