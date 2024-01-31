package com.example.movopfy.uiComponents.navigation

const val TITLE_ID = "id"

sealed class Screen(val route: String) {

    data object Home : Screen(route = "home_screen")

    data object Title : Screen(route = "title_screen/{$TITLE_ID}") {
        fun passId(id: Int): String {
            return this.route.replace(oldValue = "{$TITLE_ID}", newValue = id.toString())
        }
    }
}