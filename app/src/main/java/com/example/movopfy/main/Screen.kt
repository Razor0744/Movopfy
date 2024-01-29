package com.example.movopfy.main

sealed class Screen(val route: String) {

    data object Home: Screen(route = "home_screen")

    data object Title: Screen(route = "title_screen")
}