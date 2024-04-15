package com.example.movopfy.features.search.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movopfy.features.search.presentation.viewmodel.SearchViewModel
import com.example.movopfy.uiComponents.components.ProgressBarLoading
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SearchViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Surface(modifier = modifier) {
        when (val state = uiState) {
            is SearchViewModel.SearchUiState.Loading -> {
                ProgressBarLoading()
            }

            is SearchViewModel.SearchUiState.Loaded -> {
                Column {
                    SearchField(
                        viewModel = viewModel
                    ) {
                        if (state.searchTitles.isNotEmpty()) {
                            viewModel.searchTitles(searchText = "")
                        } else {
                            navController.popBackStack()
                        }
                    }

                    RecentItems(
                        navController = navController,
                        recentTitles = state.recentTitles,
                        viewModel = viewModel
                    )

                    SearchItems(
                        navController = navController,
                        searchTitles = state.searchTitles,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}