package com.example.movopfy.features.home.presentation.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.movopfy.R
import com.example.movopfy.features.home.presentation.viewmodel.HomeViewModel
import com.example.movopfy.uiComponents.components.ProgressBarLoading
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
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Title(text = stringResource(id = R.string.schedule_for_today_text))

                            AnimeButton(navController = navController)
                        }

                        SchedulesList(
                            list = state.homeState.animeSeriesList,
                            navController = navController
                        )
                    }

                    items(count = state.homeState.movieList.size) { item ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Title(text = state.homeState.movieList[item].first)

                            MoviesButton(
                                navController = navController,
                                state.homeState.movieList[item].first
                            )
                        }

                        KinopoiskList(
                            list = state.homeState.movieList[item].second,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}