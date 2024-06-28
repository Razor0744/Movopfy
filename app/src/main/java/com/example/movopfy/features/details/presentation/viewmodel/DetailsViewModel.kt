package com.example.movopfy.features.details.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.constants.API_CATEGORY_ANILIBRIA
import com.example.common.constants.API_CATEGORY_KINOPOISK
import com.example.common.extensions.date
import com.example.movopfy.database.models.favourite.FavouriteModel
import com.example.movopfy.features.details.domain.models.DetailsState
import com.example.movopfy.features.details.domain.repository.AnilibriaRepository
import com.example.movopfy.features.details.domain.repository.FavouriteRepository
import com.example.movopfy.features.details.domain.repository.KinopoiskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class DetailsViewModel(
    private val anilibriaRepository: AnilibriaRepository,
    private val kinopoiskRepository: KinopoiskRepository,
    private val favouriteRepository: FavouriteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiState: StateFlow<DetailsUiState> = _uiState.asStateFlow()

    fun getTitleAnilibria(id: Int) {
        viewModelScope.launch {
            val data =
                anilibriaRepository.getTitle(
                    id = id,
                    dateTime = Calendar.getInstance().date()
                )

            val favourite = favouriteRepository.getFavouriteById(id = id)

            _uiState.emit(
                DetailsUiState.Loaded(
                    detailsState = DetailsState(
                        detailsData = data,
                        favouriteModel = favourite
                    )
                )
            )
        }
    }

    fun getTitleKinopoisk(id: Int) {
        viewModelScope.launch {
            val data = kinopoiskRepository.getTitle(
                id = id,
                dateTime = Calendar.getInstance().date()
            )

            val favourite = favouriteRepository.getFavouriteById(id = id)

            _uiState.emit(
                DetailsUiState.Loaded(
                    detailsState = DetailsState(
                        detailsData = data,
                        favouriteModel = favourite
                    )
                )
            )
        }
    }

    fun addToFavourite(favouriteModel: FavouriteModel) {
        viewModelScope.launch {
            favouriteRepository.addToFavourite(favouriteModel = favouriteModel)

            when (favouriteModel.category) {
                com.example.common.constants.API_CATEGORY_ANILIBRIA -> getTitleAnilibria(id = favouriteModel.titleId)
                com.example.common.constants.API_CATEGORY_KINOPOISK -> getTitleKinopoisk(id = favouriteModel.titleId)
            }
        }
    }

    fun removeFromFavourite(favouriteModel: FavouriteModel) {
        viewModelScope.launch {
            favouriteRepository.removeFromFavourite(favouriteModel = favouriteModel)

            when (favouriteModel.category) {
                com.example.common.constants.API_CATEGORY_ANILIBRIA -> getTitleAnilibria(id = favouriteModel.titleId)
                com.example.common.constants.API_CATEGORY_KINOPOISK -> getTitleKinopoisk(id = favouriteModel.titleId)
            }
        }
    }

    sealed interface DetailsUiState {

        data object Loading : DetailsUiState

        data class Loaded(val detailsState: DetailsState?) : DetailsUiState
    }
}