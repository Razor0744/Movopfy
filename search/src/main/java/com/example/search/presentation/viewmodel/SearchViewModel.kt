package com.example.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.search.domain.models.RecentModel
import com.example.search.domain.models.SearchTitle
import com.example.search.domain.repository.AnilibriaRepository
import com.example.search.domain.repository.KinopoiskRepository
import com.example.search.domain.repository.RecentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val anilibriaRepository: AnilibriaRepository,
    private val kinopoiskRepository: KinopoiskRepository,
    private val recentRepository: RecentRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Loading)
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private var recentList: List<RecentModel> = emptyList()

    fun searchTitles(searchText: String) {
        if (searchText.isNotEmpty()) {
            viewModelScope.launch {
                val anilibriaList = anilibriaRepository.search(searchText = searchText)

                val kinopoiskList = kinopoiskRepository.search(searchText = searchText)

                _uiState.emit(
                    SearchUiState.Loaded(
                        searchTitles = anilibriaList + kinopoiskList,
                        recentTitles = emptyList()
                    )
                )
            }
        } else {
            viewModelScope.launch {
                if (recentList.isEmpty()) {
                    recentList = recentRepository.getRecent()
                }

                _uiState.emit(
                    SearchUiState.Loaded(
                        searchTitles = emptyList(),
                        recentTitles = recentList
                    )
                )
            }
        }
    }

    fun removeFromRecent(recentModel: RecentModel) {
        viewModelScope.launch {
            recentRepository.removeFromRecent(recentModel = recentModel)

            recentList = recentRepository.getRecent()

            _uiState.emit(
                SearchUiState.Loaded(
                    searchTitles = emptyList(),
                    recentTitles = recentList
                )
            )
        }
    }

    fun addToRecent(recentModel: RecentModel) {
        viewModelScope.launch {
            recentRepository.addToRecent(recentModel = recentModel)
        }
    }

    init {
        searchTitles("")
    }

    sealed interface SearchUiState {

        data object Loading : SearchUiState

        data class Loaded(
            val searchTitles: List<SearchTitle>,
            val recentTitles: List<RecentModel>
        ) : SearchUiState
    }
}