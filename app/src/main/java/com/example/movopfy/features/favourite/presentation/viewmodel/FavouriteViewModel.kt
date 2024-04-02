package com.example.movopfy.features.favourite.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movopfy.database.models.favourite.Favourite
import com.example.movopfy.features.favourite.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavouriteViewModel(private val favouriteRepository: FavouriteRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<FavoritesUiState>(FavoritesUiState.Loading)
    val uiState: StateFlow<FavoritesUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val list = favouriteRepository.getFavourite()

            _uiState.emit(FavoritesUiState.Loaded(list = list))
        }
    }

    fun removeFromFavorite(favourite: Favourite) {
        viewModelScope.launch {
            favouriteRepository.removeFromFavourite(favourite = favourite)

            val list = favouriteRepository.getFavourite()

            _uiState.emit(FavoritesUiState.Loaded(list = list))
        }
    }

    sealed interface FavoritesUiState {
        data object Loading : FavoritesUiState

        data class Loaded(val list: List<Favourite>) : FavoritesUiState
    }
}