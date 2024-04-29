package com.example.movopfy.features.favourite.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movopfy.features.favourite.domain.models.FavouriteItem
import com.example.movopfy.features.favourite.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavouriteViewModel(private val favouriteRepository: FavouriteRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<FavoritesUiState>(FavoritesUiState.Loading)
    val uiState: StateFlow<FavoritesUiState> = _uiState.asStateFlow()

    fun getFavourites() {
        viewModelScope.launch {
            val favouriteModels = favouriteRepository.getFavourites()

            _uiState.emit(FavoritesUiState.Loaded(favouriteItems = favouriteModels.map {
                FavouriteItem(
                    titleId = it.titleId,
                    url = it.url,
                    category = it.category
                )
            }))
        }
    }

    sealed interface FavoritesUiState {
        data object Loading : FavoritesUiState

        data class Loaded(val favouriteItems: List<FavouriteItem>) : FavoritesUiState
    }
}