package com.example.movopfy.features.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movopfy.features.search.domain.models.SearchTitle
import com.example.movopfy.features.search.domain.repository.AnilibriaRepository
import com.example.movopfy.features.search.domain.repository.KinopoiskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val anilibriaRepository: AnilibriaRepository,
    private val kinopoiskRepository: KinopoiskRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Loading)
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun searchTitles(searchText: String) {
        if (searchText.isNotEmpty()) {
            viewModelScope.launch {
                val anilibriaList = anilibriaRepository.search(searchText = searchText)

                val kinopoiskList = kinopoiskRepository.search(searchText = searchText)

                _uiState.emit(SearchUiState.Loaded(list = anilibriaList + kinopoiskList))
            }
        } else {
            viewModelScope.launch {
                _uiState.emit(SearchUiState.Loaded(list = emptyList()))
            }
        }
    }

    sealed interface SearchUiState {

        data object Loading : SearchUiState

        data class Loaded(val list: List<SearchTitle>) : SearchUiState
    }
}