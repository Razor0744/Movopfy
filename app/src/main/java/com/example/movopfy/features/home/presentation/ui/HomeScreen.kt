package com.example.movopfy.features.home.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movopfy.features.home.presentation.viewmodel.HomeViewModel
import com.example.movopfy.uiComponents.components.ProgressBarLoading
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel(), navController: NavController) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it),
        ) {
            SchedulesHeader()

            when (val state = uiState) {
                is HomeViewModel.HomeUiState.Loading -> {
                    ProgressBarLoading()
                }

                is HomeViewModel.HomeUiState.Loaded -> {
                    SchedulesList(
                        list = state.schedules,
                        navController = navController
                    )
                }
            }
        }
    }
}