package com.example.movopfy.features.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movopfy.features.search.domain.models.RecentModel
import com.example.movopfy.features.search.domain.models.SearchTitle
import com.example.movopfy.features.search.domain.repository.AnilibriaRepository
import com.example.movopfy.features.search.domain.repository.KinopoiskRepository
import com.example.movopfy.features.search.domain.repository.RoomRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val anilibriaRepository: AnilibriaRepository,
    private val kinopoiskRepository: KinopoiskRepository,
    private val roomRepository: RoomRepository
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
                        list = anilibriaList + kinopoiskList,
                        recentList = emptyList()
                    )
                )
            }
        } else {
            viewModelScope.launch {
                if (recentList.isEmpty()) {
                    recentList = roomRepository.getRecent()
                }

                _uiState.emit(
                    SearchUiState.Loaded(
                        list = emptyList(),
                        recentList = recentList
                    )
                )
            }
        }
    }

    fun removeFromRecent(recentModel: RecentModel) {
        viewModelScope.launch {
            roomRepository.removeFromRecent(recentModel = recentModel)

            recentList = roomRepository.getRecent()

            _uiState.emit(
                SearchUiState.Loaded(
                    list = emptyList(),
                    recentList = recentList
                )
            )
        }
    }

    fun addToRecent(recentModel: RecentModel) {
        viewModelScope.launch {
            roomRepository.addToRecent(recentModel = recentModel)
        }
    }

    init {
        searchTitles("")
    }

    sealed interface SearchUiState {

        data object Loading : SearchUiState

        data class Loaded(
            val list: List<SearchTitle>,
            val recentList: List<RecentModel>
        ) : SearchUiState
    }
}