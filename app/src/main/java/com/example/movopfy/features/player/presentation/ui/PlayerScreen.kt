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
        viewModel.getPlayerState(id = id, episode = episode)
    }

    when (val state = uiState) {
        is PlayerViewModel.PlayerUiState.Loading -> {
            ProgressBarLoading()
        }

        is PlayerViewModel.PlayerUiState.Loaded -> {
            Player(
                modifier = modifier,
                episodesCount = state.playerState.episodesCount,
                url = state.playerState.url,
                id = id,
                episode = state.playerState.episode,
                lastTime = state.playerState.currentTime,
                viewModel = viewModel
            )
        }
    }
}