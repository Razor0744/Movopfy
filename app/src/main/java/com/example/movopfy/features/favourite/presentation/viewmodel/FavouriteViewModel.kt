package com.example.movopfy.features.favourite.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movopfy.database.models.favourite.FavouriteModel
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

    fun removeFromFavorite(favouriteModel: FavouriteModel) {
        viewModelScope.launch {
            favouriteRepository.removeFromFavourite(favouriteModel = favouriteModel)

            val list = favouriteRepository.getFavourite()

            _uiState.emit(FavoritesUiState.Loaded(list = list))
        }
    }

    sealed interface FavoritesUiState {
        data object Loading : FavoritesUiState

        data class Loaded(val list: List<FavouriteModel>) : FavoritesUiState
    }
}