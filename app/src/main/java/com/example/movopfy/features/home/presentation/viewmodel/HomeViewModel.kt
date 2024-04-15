package com.example.movopfy.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movopfy.common.extensions.currentDay
import com.example.movopfy.features.home.domain.models.HomeState
import com.example.movopfy.features.home.domain.usecase.GetHomeDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class HomeViewModel(private val getHomeDataUseCase: GetHomeDataUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val homeState = getHomeDataUseCase.execute(currentDay = Calendar.getInstance().currentDay())

            _uiState.emit(
                HomeUiState.Loaded(
                    homeState = homeState
                )
            )
        }
    }

    sealed interface HomeUiState {

        data object Loading : HomeUiState

        data class Loaded(
            val homeState: HomeState
        ) : HomeUiState
    }
}