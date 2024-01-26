package com.example.movopfy.main

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")
}