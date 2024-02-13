package com.example.movopfy.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movopfy.common.mappers.mapAnilibriaUrlToImageUrl
import com.example.movopfy.features.home.domain.models.WaitingListToday
import com.example.movopfy.features.home.domain.repository.AnilibriaRepository
import com.example.movopfy.features.home.domain.repository.KinopoiskRepository
import com.example.movopfy.network.kinopoisk.models.KinopoiskDocs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val anilibriaRepository: AnilibriaRepository,
    private val kinopoiskRepository: KinopoiskRepository
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private var anime = emptyList<WaitingListToday>()
    private var movieList = mutableListOf<Pair<String, List<KinopoiskDocs>>>()

    init {
        viewModelScope.launch {
            anime =
                mapAnilibriaUrlToImageUrl(anilibriaSchedule = anilibriaRepository.getSchedule()[6])


            _uiState.emit(HomeUiState.Loaded(anime = anime, movieList = movieList))
        }

        viewModelScope.launch {
            movieList.add(
                Pair(
                    "Ужасы",
                    kinopoiskRepository.getList(page = 1, category = "+ужасы")
                )
            )

            _uiState.emit(HomeUiState.Loaded(anime = anime, movieList = movieList))
        }

        viewModelScope.launch {
            movieList.add(
                Pair(
                    "Комедия",
                    kinopoiskRepository.getList(page = 1, category = "комедия")
                )
            )

            _uiState.emit(HomeUiState.Loaded(anime = anime, movieList = movieList))
        }

        viewModelScope.launch {
            movieList.add(
                Pair(
                    "Драма",
                    kinopoiskRepository.getList(page = 1, category = "драма")
                )
            )

            _uiState.emit(HomeUiState.Loaded(anime = anime, movieList = movieList))
        }

        viewModelScope.launch {
            movieList.add(
                Pair(
                    "Мелодрама",
                    kinopoiskRepository.getList(page = 1, category = "!мелодрама")
                )
            )

            _uiState.emit(HomeUiState.Loaded(anime = anime, movieList = movieList))
        }
    }

    sealed interface HomeUiState {
        data object Loading : HomeUiState
        data class Loaded(
            val anime: List<WaitingListToday>,
            val movieList: MutableList<Pair<String, List<KinopoiskDocs>>>
        ) : HomeUiState
    }
}