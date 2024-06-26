package com.example.movopfy.features.movies.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.mappers.kinopoisk.mapToKinopoiskCategory
import com.example.movopfy.features.movies.domain.models.KinopoiskItem
import com.example.movopfy.features.movies.domain.repository.KinopoiskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(private val kinopoiskRepository: KinopoiskRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<MoviesUiState>(MoviesUiState.Loading)
    val uiState: StateFlow<MoviesUiState> = _uiState.asStateFlow()

    private val items = arrayListOf<KinopoiskItem>()
    private var page = 1

    fun getKinopoiskList(category: String) {
        viewModelScope.launch {
            items += kinopoiskRepository.getList(
                page = page,
                category = com.example.common.mappers.kinopoisk.mapToKinopoiskCategory(category = category)
            )
            page += 1

            _uiState.emit(MoviesUiState.Loaded(kinopoiskItems = items))
        }
    }

    sealed interface MoviesUiState {

        data object Loading : MoviesUiState

        data class Loaded(val kinopoiskItems: List<KinopoiskItem>) : MoviesUiState
    }
}