package com.example.movopfy.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movopfy.features.home.domain.models.WaitingListToday
import com.example.movopfy.features.home.domain.usecase.GetWaitingListTodayUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val getWaitingListTodayUseCase: GetWaitingListTodayUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val schedules = getWaitingListTodayUseCase.execute(day = 6)

            _uiState.emit(HomeUiState.Loaded(schedules = schedules))
        }
    }

    sealed interface HomeUiState {
        data object Loading : HomeUiState
        data class Loaded(val schedules: List<WaitingListToday>) : HomeUiState
    }
}