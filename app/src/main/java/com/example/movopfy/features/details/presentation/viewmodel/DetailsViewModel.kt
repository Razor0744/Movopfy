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
            val anilibriaTitle = LocalState.anilibriaMap[id]

            if (anilibriaTitle != null) {
                _uiState.emit(
                    DetailsUiState.Loaded(
                        anilibriaTitle = anilibriaTitle,
                        kinopoiskTitle = null
                    )
                )
            } else {
                val title = anilibriaRepository.getTitle(id = id)

                title?.let { LocalState.anilibriaMap[id] = it }

                _uiState.emit(
                    DetailsUiState.Loaded(
                        anilibriaTitle = title,
                        kinopoiskTitle = null
                    )
                )
            }
        }
    }

    fun getTitleKinopoisk(id: Int) {
        viewModelScope.launch {
            val kinopoiskTitle = LocalState.kinopoiskMap[id]

            if (kinopoiskTitle != null) {
                _uiState.emit(
                    DetailsUiState.Loaded(
                        kinopoiskTitle = kinopoiskTitle,
                        anilibriaTitle = null
                    )
                )
            } else {
                val title = kinopoiskRepository.getTitle(id = id)

                title?.let { LocalState.kinopoiskMap[id] = it }

                _uiState.emit(
                    DetailsUiState.Loaded(
                        kinopoiskTitle = title,
                        anilibriaTitle = null
                    )
                )
            }
        }
    }

    sealed interface DetailsUiState {

        data object Loading : DetailsUiState

        data class Loaded(
            val anilibriaTitle: AnilibriaTitle?,
            val kinopoiskTitle: KinopoiskTitle?
        ) : DetailsUiState
    }

    private object LocalState {

        val anilibriaMap = mutableMapOf<Int, AnilibriaTitle>()

        val kinopoiskMap = mutableMapOf<Int, KinopoiskTitle>()
    }
}