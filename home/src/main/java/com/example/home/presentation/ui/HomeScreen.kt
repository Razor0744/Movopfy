package com.example.home.presentation.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.home.R
import com.example.home.presentation.viewmodel.HomeViewModel
import com.example.uiComponents.components.ProgressBarLoading
import com.example.uiComponents.theme.dimensions
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(modifier = modifier) { padding ->
        when (val state = uiState) {
            is HomeViewModel.HomeUiState.Loading -> {
                ProgressBarLoading()
            }

            is HomeViewModel.HomeUiState.Loaded -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                ) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = MaterialTheme.dimensions.paddingTop),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Title(text = stringResource(id = R.string.schedule_for_today_text))

                            AnimeButton(navController = navController)
                        }

                        SchedulesList(
                            animeSeriesList = state.homeState.animeSeriesList,
                            navController = navController
                        )
                    }

                    items(state.homeState.movieList) { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = MaterialTheme.dimensions.paddingTop),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Title(text = item.first)

                            MoviesButton(
                                navController = navController,
                                category = item.first
                            )
                        }

                        KinopoiskList(
                            kinopoiskItems = item.second,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}