package com.example.movies.presentation.ui

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movies.presentation.viewmodel.MoviesViewModel
import com.example.uiComponents.components.ProgressBarLoading
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

    Surface(modifier = modifier) {
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
                    modifier = Modifier,
                    kinopoiskItems = state.kinopoiskItems,
                    state = gridState,
                    navController = navController
                )
            }
        }
    }
}