package com.example.movopfy.features.movies.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movopfy.features.movies.domain.repository.KinopoiskRepository
import com.example.movopfy.network.kinopoisk.models.KinopoiskDocs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

const val HORRORS = "Ужасы"
const val COMEDY = "Комедия"
const val DRAMA = "Драма"
const val MELODRAMA = "Мелодрама"
const val HORRORS_CATEGORY = "+ужасы"
const val COMEDY_CATEGORY = "комедия"
const val DRAMA_CATEGORY = "драма"
const val MELODRAMA_CATEGORY = "!мелодрама"

class MoviesViewModel(private val kinopoiskRepository: KinopoiskRepository) : ViewModel() {

    private val _uiState =
        MutableStateFlow<MoviesUiState>(MoviesUiState.Loading)
    val uiState: StateFlow<MoviesUiState> = _uiState.asStateFlow()

    private val list = arrayListOf<KinopoiskDocs>()
    private var page = 1

    private fun categoryList(category: String) = when (category) {
        HORRORS -> HORRORS_CATEGORY
        COMEDY -> COMEDY_CATEGORY
        DRAMA -> DRAMA_CATEGORY
        MELODRAMA -> MELODRAMA_CATEGORY
        else -> HORRORS_CATEGORY
    }

    fun getKinopoiskList(category: String) {
        viewModelScope.launch {
            list += kinopoiskRepository.getList(
                page = page,
                category = categoryList(category = category)
            )
            page += 1

            _uiState.emit(MoviesUiState.Loaded(list = list))
        }
    }

    sealed interface MoviesUiState {

        data object Loading : MoviesUiState

        data class Loaded(val list: List<KinopoiskDocs>) : MoviesUiState
    }
}