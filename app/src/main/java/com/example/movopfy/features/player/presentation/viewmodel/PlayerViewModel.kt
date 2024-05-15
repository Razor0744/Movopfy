package com.example.movopfy.features.player.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movopfy.features.player.domain.models.PlayerMark
import com.example.movopfy.features.player.domain.models.PlayerState
import com.example.movopfy.features.player.domain.repository.AnilibriaRepository
import com.example.movopfy.features.player.domain.repository.PlayerMarksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlayerViewModel(
    private val anilibriaRepository: AnilibriaRepository,
    private val playerMarksRepository: PlayerMarksRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<PlayerUiState>(PlayerUiState.Loading)
    val uiState: StateFlow<PlayerUiState> = _uiState.asStateFlow()

    var isFullScreen = false

    fun getPlayerState(id: Int, episode: Int) {
        viewModelScope.launch {
            val title = anilibriaRepository.getPlayerData(id = id, episode = episode)

            val currentTime = playerMarksRepository.getTimeById(id = id, episode = episode)

            _uiState.emit(
                PlayerUiState.Loaded(
                    PlayerState(
                        currentTime = currentTime,
                        url = title.url,
                        episodesCount = title.episodesCount,
                        episode = episode
                    )
                )
            )
        }
    }

    fun setCurrentTime(playerMark: PlayerMark) {
        viewModelScope.launch {
            playerMarksRepository.setTime(playerMark = playerMark)
        }
    }

    interface PlayerUiState {

        data object Loading : PlayerUiState

        data class Loaded(
            val playerState: PlayerState
        ) : PlayerUiState
    }
}