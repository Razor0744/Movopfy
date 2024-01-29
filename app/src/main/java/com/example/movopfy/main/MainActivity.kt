package com.example.movopfy.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.movopfy.main.ui.theme.MovopfyTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovopfyTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}