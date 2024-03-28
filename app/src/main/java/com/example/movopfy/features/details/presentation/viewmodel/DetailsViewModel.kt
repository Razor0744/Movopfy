package com.example.movopfy.features.details.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movopfy.database.models.favourite.RoomFavourite
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
            val state = anilibriaRepository.getTitle(id = id)

            val isFavourite = favouriteRepository.getFavouriteById(id = id)

            _uiState.emit(
                DetailsUiState.Loaded(
                    detailsState = state,
                    roomFavourite = isFavourite
                )
            )
        }
    }

    fun getTitleKinopoisk(id: Int) {
        viewModelScope.launch {
            val state = kinopoiskRepository.getTitle(id = id)

            val isFavourite = favouriteRepository.getFavouriteById(id = id)

            _uiState.emit(
                DetailsUiState.Loaded(
                    detailsState = state,
                    roomFavourite = isFavourite
                )
            )
        }
    }

    fun addToFavourite(roomFavourite: RoomFavourite) {
        viewModelScope.launch {
            favouriteRepository.addToFavourite(roomFavourite = roomFavourite)
        }
    }

    fun removeFromFavourite(roomFavourite: RoomFavourite) {
        viewModelScope.launch {
            favouriteRepository.removeFromFavourite(roomFavourite = roomFavourite)
        }
    }

    sealed interface DetailsUiState {

        data object Loading : DetailsUiState

        data class Loaded(
            val detailsState: DetailsState?,
            val roomFavourite: RoomFavourite?
        ) : DetailsUiState
    }
}