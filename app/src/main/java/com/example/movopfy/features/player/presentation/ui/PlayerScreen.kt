package com.example.movopfy.features.player.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.movopfy.features.player.presentation.viewmodel.PlayerViewModel
import com.example.movopfy.uiComponents.components.ProgressBarLoading
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlayerScreen(
    modifier: Modifier = Modifier,
    id: Int,
    episode: Int,
    viewModel: PlayerViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getTitle(id = id)
    }

    when (val state = uiState) {
        is PlayerViewModel.PlayerUiState.Loading -> {
            ProgressBarLoading()
        }

        is PlayerViewModel.PlayerUiState.Loaded -> {
            Player(
                modifier = modifier,
                title = state.title,
                episode = episode,
                viewModel = viewModel
            )
        }
    }
}