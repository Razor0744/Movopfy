package com.example.movopfy.features.favourite.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.movopfy.R
import com.example.movopfy.features.favourite.presentation.viewmodel.FavouriteViewModel
import com.example.movopfy.uiComponents.components.ProgressBarLoading
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavouriteScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: FavouriteViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Surface(modifier = modifier) {
        when (val state = uiState) {
            is FavouriteViewModel.FavoritesUiState.Loading -> {
                ProgressBarLoading()
            }

            is FavouriteViewModel.FavoritesUiState.Loaded -> {
                if (state.favouriteItems.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            modifier = Modifier.align(alignment = Alignment.Center),
                            text = stringResource(
                                id = R.string.favourite_is_empty
                            )
                        )
                    }
                } else {
                    FavouriteItem(
                        favouriteItems = state.favouriteItems,
                        navController = navController
                    )
                }
            }
        }
    }
}