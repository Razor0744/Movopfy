package com.example.movopfy.features.details.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movopfy.database.models.favourite.Favourite
import com.example.movopfy.features.details.domain.models.DetailsState
import com.example.movopfy.features.details.domain.repository.AnilibriaRepository
import com.example.movopfy.features.details.domain.repository.FavouriteRepository
import com.example.movopfy.features.details.domain.repository.KinopoiskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val anilibriaRepository: AnilibriaRepository,
    private val kinopoiskRepository: KinopoiskRepository,
    private val favouriteRepository: FavouriteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiState: StateFlow<DetailsUiState> = _uiState.asStateFlow()

    fun getTitleAnilibria(id: Int) {
        viewModelScope.launch {
            val data = anilibriaRepository.getTitle(id = id)

            val favourite = favouriteRepository.getFavouriteById(id = id)

            _uiState.emit(
                DetailsUiState.Loaded(
                    detailsState = DetailsState(
                        detailsData = data,
                        favourite = favourite
                    )
                )
            )
        }
    }

    fun getTitleKinopoisk(id: Int) {
        viewModelScope.launch {
            val data = kinopoiskRepository.getTitle(id = id)

            val favourite = favouriteRepository.getFavouriteById(id = id)

            _uiState.emit(
                DetailsUiState.Loaded(
                    detailsState = DetailsState(
                        detailsData = data,
                        favourite = favourite
                    )
                )
            )
        }
    }

    fun addToFavourite(favourite: Favourite) {
        viewModelScope.launch {
            favouriteRepository.addToFavourite(favourite = favourite)
        }
    }

    fun removeFromFavourite(favourite: Favourite) {
        viewModelScope.launch {
            favouriteRepository.removeFromFavourite(favourite = favourite)
        }
    }

    sealed interface DetailsUiState {

        data object Loading : DetailsUiState

        data class Loaded(val detailsState: DetailsState?) : DetailsUiState
    }
}