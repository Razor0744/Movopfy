package com.example.movopfy.features.auth.presentation.viewModel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movopfy.features.auth.domain.repository.FirebaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val firebaseRepository: FirebaseRepository) : ViewModel() {

    private var _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Loading)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun createUser(email: String, password: String, activity: Activity) {
        viewModelScope.launch {
            firebaseRepository.createUser(email = email, password = password, activity = activity)
        }
    }

    fun signInUser(email: String, password: String, activity: Activity) {
        viewModelScope.launch {
            firebaseRepository.signInUser(email = email, password = password, activity = activity)
        }
    }

    fun checkUser(){

    }

    init {
        viewModelScope.launch {
            _uiState.emit(AuthUiState.Loaded(name = ""))
        }
    }

    sealed interface AuthUiState {

        data object Loading : AuthUiState

        data class Loaded(val name: String) : AuthUiState
    }
}