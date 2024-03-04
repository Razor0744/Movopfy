package com.example.movopfy.features.anime.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movopfy.R
import com.example.movopfy.common.mappers.anilibria.mapToAnimeSeriesList
import com.example.movopfy.features.anime.presentation.viewmodel.AnimeViewModel
import com.example.movopfy.uiComponents.components.ProgressBarLoading
import org.koin.androidx.compose.koinViewModel

@Composable
fun AnimeScreen(
    modifier: Modifier = Modifier,
    viewModel: AnimeViewModel = koinViewModel(),
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(modifier = modifier) { padding ->
        when (val state = uiState) {
            is AnimeViewModel.AnimeUiState.Loading -> {
                ProgressBarLoading()
            }

            is AnimeViewModel.AnimeUiState.Loaded -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    verticalArrangement = Arrangement.spacedBy(space = 10.dp)
                ) {
                    items(state.list.size) {
                        DayTitle(text = stringArrayResource(id = R.array.day_list)[it])

                        AnimeList(
                            list = mapToAnimeSeriesList(state.list[it]),
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}