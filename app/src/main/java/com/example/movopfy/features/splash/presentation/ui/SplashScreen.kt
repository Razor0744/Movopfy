package com.example.movopfy.features.splash.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movopfy.R
import com.example.movopfy.features.splash.presentation.viewModel.SplashViewModel
import com.example.movopfy.firebase.user.UserManager
import com.example.movopfy.uiComponents.navigation.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    navController: NavController,
    userManager: UserManager,
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        SplashViewModel.SplashUiState.Synchronization -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
            ) {
                Image(
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .size(size = 200.dp),
                    painter = painterResource(id = R.drawable.ic_top_bar),
                    contentDescription = null
                )
            }
        }

        SplashViewModel.SplashUiState.Synchronized -> {
            navController.navigate(
                if (userManager.getCurrentUser() != null) Screen.Home.route
                else Screen.Auth.route
            )
        }
    }
}