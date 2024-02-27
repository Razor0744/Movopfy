package com.example.movopfy.features.details.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movopfy.features.details.domain.repository.AnilibriaRepository
import com.example.movopfy.features.details.domain.repository.KinopoiskRepository
import com.example.movopfy.network.anilibria.models.AnilibriaTitle
import com.example.movopfy.network.kinopoisk.models.KinopoiskTitle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val anilibriaRepository: AnilibriaRepository,
    private val kinopoiskRepository: KinopoiskRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiState: StateFlow<DetailsUiState> = _uiState.asStateFlow()

    fun getTitleAnilibria(id: Int) {
        viewModelScope.launch {
            val title = anilibriaRepository.getTitle(id = id)

            _uiState.emit(
                DetailsUiState.Loaded(
                    anilibriaTitle = title,
                    kinopoiskTitle = null
                )
            )
        }
    }

    fun getTitleKinopoisk(id: Int) {
        viewModelScope.launch {
            val title = kinopoiskRepository.getTitle(id = id)

            _uiState.emit(
                DetailsUiState.Loaded(
                    kinopoiskTitle = title,
                    anilibriaTitle = null
                )
            )
        }
    }

    sealed interface DetailsUiState {

        data object Loading : DetailsUiState

        data class Loaded(
            val anilibriaTitle: AnilibriaTitle?,
            val kinopoiskTitle: KinopoiskTitle?
        ) : DetailsUiState
    }
}