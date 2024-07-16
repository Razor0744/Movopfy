package com.example.movopfy.app

import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.firebase.user.UserManager
import com.example.uiComponents.components.TopBar
import com.example.movopfy.navigation.BottomNavItem
import com.example.movopfy.navigation.BottomNavigationBar
import com.example.uiComponents.components.Screen
import com.example.movopfy.navigation.SetupNavGraph
import com.example.uiComponents.theme.MovopfyTheme
import com.example.workManager.AnimeWorkUpdate

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.uiState.value
            }
        }


        AnimeWorkUpdate().startWork(context = this)

        setContent {
            MovopfyTheme {
                navController = rememberNavController()
                var showBottomBar by rememberSaveable { mutableStateOf(true) }
                var showArrowTopBar by rememberSaveable { mutableStateOf(false) }
                var showTopBar by rememberSaveable { mutableStateOf(true) }
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                showBottomBar = when (navBackStackEntry?.destination?.route) {
                    Screen.Details.route -> false
                    Screen.Player.route -> false
                    Screen.Auth.route -> false
                    else -> true
                }

                showArrowTopBar = when (navBackStackEntry?.destination?.route) {
                    Screen.Details.route -> true
                    Screen.Anime.route -> true
                    Screen.Movies.route -> true
                    else -> false
                }

                showTopBar = when (navBackStackEntry?.destination?.route) {
                    Screen.Player.route -> false
                    Screen.Search.route -> false
                    Screen.Auth.route -> false
                    else -> true
                }

                Scaffold(
                    topBar = {
                        if (showTopBar) {
                            TopBar(
                                isBackEnabled = showArrowTopBar,
                                navController = navController
                            )
                        }
                    },
                    bottomBar = {
                        if (showBottomBar) {
                            BottomNavigationBar(
                                items = listOf(
                                    BottomNavItem.Home,
                                    BottomNavItem.Favorites,
                                    BottomNavItem.Settings
                                ),
                                navController = navController,
                                onItemClick = {
                                    navController.navigate(it.route)
                                }
                            )
                        }
                    }
                ) { padding ->
                    SetupNavGraph(
                        navController = navController,
                        paddingValues = padding,
                        userManager = UserManager()
                    )
                }
            }
        }
    }
}