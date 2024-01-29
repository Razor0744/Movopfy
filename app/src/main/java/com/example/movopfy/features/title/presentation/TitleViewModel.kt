package com.example.movopfy.features.title.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movopfy.features.title.data.models.AnilibriaTitle
import com.example.movopfy.features.title.domain.repository.AnilibriaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TitleViewModel(private val anilibriaRepository: AnilibriaRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<TitleUiState>(TitleUiState.Loading)
    val uiState: StateFlow<TitleUiState> = _uiState.asStateFlow()

    fun getTitleById(id: Int) {
        viewModelScope.launch {
            val title = anilibriaRepository.getTitle(id = id)

            _uiState.emit(TitleUiState.Loaded(title = title))
        }
    }

    sealed interface TitleUiState {

        data object Loading : TitleUiState

        data class Loaded(val title: AnilibriaTitle?) : TitleUiState
    }
}