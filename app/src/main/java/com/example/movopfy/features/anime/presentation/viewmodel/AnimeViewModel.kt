package com.example.movopfy.features.anime.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.extensions.date
import com.example.common.models.AnimeSeries
import com.example.movopfy.features.anime.domain.repository.AnilibriaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class AnimeViewModel(private val anilibriaRepository: AnilibriaRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<AnimeUiState>(AnimeUiState.Loading)
    val uiState: StateFlow<AnimeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val animeSchedules = anilibriaRepository.getSchedules(dateTime = Calendar.getInstance().date())

            _uiState.emit(AnimeUiState.Loaded(animeSchedules = animeSchedules))
        }
    }

    sealed interface AnimeUiState {

        data object Loading : AnimeUiState

        data class Loaded(val animeSchedules: List<List<AnimeSeries>>): AnimeUiState
    }
}