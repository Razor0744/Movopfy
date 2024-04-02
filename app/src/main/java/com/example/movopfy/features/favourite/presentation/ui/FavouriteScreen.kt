package com.example.movopfy.features.favourite.presentation.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.movopfy.features.favourite.presentation.viewmodel.FavouriteViewModel
import com.example.movopfy.uiComponents.components.ProgressBarLoading
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavouriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavouriteViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Surface(modifier = modifier) {
        when (val state = uiState) {
            is FavouriteViewModel.FavoritesUiState.Loading -> {
                ProgressBarLoading()
            }

            is FavouriteViewModel.FavoritesUiState.Loaded -> {
                LazyColumn {
                    items(state.list) { item ->
                        FavouriteItem(
                            item = item,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}