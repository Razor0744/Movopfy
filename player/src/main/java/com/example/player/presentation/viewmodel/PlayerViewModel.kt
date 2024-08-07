package com.example.player.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.database.models.player.PlayerMarks
import com.example.player.domain.models.PlayerState
import com.example.player.domain.repository.AnilibriaRepository
import com.example.player.domain.repository.PlayerMarksRepository
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

            val playerMarks = playerMarksRepository.getTimeById(id = id, episode = episode)

            _uiState.emit(
                PlayerUiState.Loaded(
                    PlayerState(
                        playerMarks = playerMarks,
                        url = title.url,
                        episodesCount = title.episodesCount,
                        episode = episode
                    )
                )
            )
        }
    }

    fun saveTime(playerMarks: PlayerMarks) {
        viewModelScope.launch {
            playerMarksRepository.setTime(playerMarks = playerMarks)
        }
    }

    interface PlayerUiState {

        data object Loading : PlayerUiState

        data class Loaded(
            val playerState: PlayerState
        ) : PlayerUiState
    }
}