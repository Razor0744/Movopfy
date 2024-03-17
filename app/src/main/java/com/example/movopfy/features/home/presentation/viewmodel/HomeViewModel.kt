package com.example.movopfy.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movopfy.common.constants.LOCAL_UI_STATE
import com.example.movopfy.features.home.domain.models.HomeState
import com.example.movopfy.features.home.domain.usecase.GetDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class HomeViewModel(
    private val getHomeDataUseCase: GetDataUseCase
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private fun getCurrentDay(): Int {
        val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 2

        return if (day == -1) 6 else day
    }

    init {
        viewModelScope.launch {
            val localState = LocalState.map[LOCAL_UI_STATE]

            if (localState != null) {
                _uiState.emit(localState)
            } else {
                val homeState = getHomeDataUseCase.execute(currentDay = getCurrentDay())

                LocalState.map[LOCAL_UI_STATE] = HomeUiState.Loaded(
                    homeState = homeState
                )

                _uiState.emit(
                    HomeUiState.Loaded(
                        homeState = homeState
                    )
                )
            }
        }
    }

    sealed interface HomeUiState {

        data object Loading : HomeUiState

        data class Loaded(
            val homeState: HomeState
        ) : HomeUiState
    }

    private object LocalState {

        val map = mutableMapOf<String, HomeUiState>()
    }
}