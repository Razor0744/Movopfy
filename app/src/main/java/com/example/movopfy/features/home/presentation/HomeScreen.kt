package com.example.movopfy.features.home.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.example.movopfy.features.home.domain.models.WaitingListToday
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {

    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is HomeViewModel.HomeUiState.Loading -> {}
        is HomeViewModel.HomeUiState.Loaded -> {
            val list = (uiState as HomeViewModel.HomeUiState.Loaded).schedules
            LazyRowWaitingListToday(list = list)
        }
    }
}

@Composable
fun LazyRowWaitingListToday(list: List<WaitingListToday>) {
    LazyRow(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(list.size) {
            AsyncImage(model = list[it].pictureUrl, contentDescription = null)
        }
    }
}