package com.example.movopfy.features.player.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movopfy.features.player.domain.repository.AnilibriaRepository
import com.example.movopfy.network.anilibria.models.AnilibriaTitle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlayerViewModel(private val anilibriaRepository: AnilibriaRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<PlayerUiState>(PlayerUiState.Loading)
    val uiState: StateFlow<PlayerUiState> = _uiState.asStateFlow()

    var isFullScreen = false

    fun getTitle(id: Int) {
        viewModelScope.launch {
            val title = anilibriaRepository.getTitle(id = id)

            _uiState.emit(PlayerUiState.Loaded(title = title))
        }
    }

    sealed interface PlayerUiState {
        data object Loading : PlayerUiState

        data class Loaded(val title: AnilibriaTitle?) : PlayerUiState
    }
}