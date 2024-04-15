package com.example.movopfy.features.movies.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movopfy.features.movies.presentation.viewmodel.MoviesViewModel
import com.example.movopfy.uiComponents.components.ProgressBarLoading
import org.koin.androidx.compose.koinViewModel

@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    category: String,
    viewModel: MoviesViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getKinopoiskList(category = category)
    }

    Scaffold(modifier = modifier) { padding ->
        when (val state = uiState) {
            is MoviesViewModel.MoviesUiState.Loading -> {
                ProgressBarLoading()
            }

            is MoviesViewModel.MoviesUiState.Loaded -> {
                val gridState = rememberLazyGridState()
                if (!gridState.canScrollForward) {
                    viewModel.getKinopoiskList(category = category)
                }

                MoviesList(
                    modifier = Modifier.padding(padding),
                    kinopoiskItems = state.kinopoiskItems,
                    state = gridState,
                    navController = navController
                )
            }
        }
    }
}