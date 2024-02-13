package com.example.movopfy.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movopfy.common.mappers.mapAnilibriaUrlToImageUrl
import com.example.movopfy.features.home.domain.models.AnimeSeries
import com.example.movopfy.features.home.domain.repository.AnilibriaRepository
import com.example.movopfy.features.home.domain.repository.KinopoiskRepository
import com.example.movopfy.network.kinopoisk.models.KinopoiskDocs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

const val HORRORS = "Ужасы"
const val COMEDY = "Комедия"
const val DRAMA = "Драма"
const val MELODRAMA = "Мелодрама"
const val HORRORS_CATEGORY = "+ужасы"
const val COMEDY_CATEGORY = "комедия"
const val DRAMA_CATEGORY = "драма"
const val MELODRAMA_CATEGORY = "!мелодрама"
const val KINOPOISK_PAGE = 1

class HomeViewModel(
    private val anilibriaRepository: AnilibriaRepository,
    private val kinopoiskRepository: KinopoiskRepository
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private var animeSeriesList = emptyList<AnimeSeries>()
    private var movieList = mutableListOf<Pair<String, List<KinopoiskDocs>>>()

    init {
        viewModelScope.launch {
            animeSeriesList =
                mapAnilibriaUrlToImageUrl(anilibriaSchedule = anilibriaRepository.getSchedule()[getCurrentDay()])


            _uiState.emit(HomeUiState.Loaded(anime = animeSeriesList, movieList = movieList))
        }

        viewModelScope.launch {
            movieList.add(
                Pair(
                    HORRORS,
                    kinopoiskRepository.getList(page = KINOPOISK_PAGE, category = HORRORS_CATEGORY)
                )
            )

            _uiState.emit(HomeUiState.Loaded(anime = animeSeriesList, movieList = movieList))
        }

        viewModelScope.launch {
            movieList.add(
                Pair(
                    COMEDY,
                    kinopoiskRepository.getList(page = KINOPOISK_PAGE, category = COMEDY_CATEGORY)
                )
            )

            _uiState.emit(HomeUiState.Loaded(anime = animeSeriesList, movieList = movieList))
        }

        viewModelScope.launch {
            movieList.add(
                Pair(
                    DRAMA,
                    kinopoiskRepository.getList(page = KINOPOISK_PAGE, category = DRAMA_CATEGORY)
                )
            )

            _uiState.emit(HomeUiState.Loaded(anime = animeSeriesList, movieList = movieList))
        }

        viewModelScope.launch {
            movieList.add(
                Pair(
                    MELODRAMA,
                    kinopoiskRepository.getList(page = KINOPOISK_PAGE, category = MELODRAMA_CATEGORY)
                )
            )

            _uiState.emit(HomeUiState.Loaded(anime = animeSeriesList, movieList = movieList))
        }
    }

    private fun getCurrentDay() = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 2

    sealed interface HomeUiState {
        data object Loading : HomeUiState
        data class Loaded(
            val anime: List<AnimeSeries>,
            val movieList: MutableList<Pair<String, List<KinopoiskDocs>>>
        ) : HomeUiState
    }
}