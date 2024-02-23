package com.example.movopfy.features.anime.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movopfy.features.anime.domain.repository.AnilibriaRepository
import com.example.movopfy.network.anilibria.models.AnilibriaSchedule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeViewModel(private val anilibriaRepository: AnilibriaRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<AnimeUiState>(AnimeUiState.Loading)
    val uiState: StateFlow<AnimeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val list = anilibriaRepository.getSchedules()

            _uiState.emit(AnimeUiState.Loaded(list = list))
        }
    }

    sealed interface AnimeUiState {

        data object Loading : AnimeUiState

        data class Loaded(val list: List<AnilibriaSchedule>): AnimeUiState
    }
}