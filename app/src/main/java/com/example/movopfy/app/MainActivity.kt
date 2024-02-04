package com.example.movopfy.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.movopfy.uiComponents.navigation.BottomNavItem
import com.example.movopfy.uiComponents.navigation.BottomNavigationBar
import com.example.movopfy.uiComponents.navigation.SetupNavGraph
import com.example.movopfy.uiComponents.theme.AppTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                navController = rememberNavController()
                Scaffold(
                    containerColor = AppTheme.colorScheme.background,
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(BottomNavItem.Home, BottomNavItem.Favorites),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) {
                    SetupNavGraph(navController = navController)
                    println(it)
                }
            }
        }
    }
}

