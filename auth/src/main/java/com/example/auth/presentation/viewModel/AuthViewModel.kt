package com.example.auth.presentation.viewModel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.domain.models.CreateUserResult
import com.example.auth.domain.models.SignInUserResult
import com.example.auth.domain.repository.FirebaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val firebaseRepository: FirebaseRepository) : ViewModel() {

    private var _uiState = MutableStateFlow<AuthUiState>(AuthUiState.SignInUser(toastMessage = ""))
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun createUser(email: String, password: String, activity: Activity) {
        viewModelScope.launch {
            _uiState.emit(AuthUiState.CreatingUser)

            val result = firebaseRepository.createUser(
                email = email,
                password = password,
                activity = activity
            )

            when (result) {
                is CreateUserResult.Success -> {
                    _uiState.emit(AuthUiState.SuccessfulResult)
                }

                is CreateUserResult.Fail -> {
                    _uiState.emit(AuthUiState.CreateUser(toastMessage = result.exception))
                }
            }
        }
    }

    fun signInUser(email: String, password: String, activity: Activity) {
        viewModelScope.launch {
            _uiState.emit(AuthUiState.SigningInUser)

            val result = firebaseRepository.signInUser(
                email = email,
                password = password,
                activity = activity
            )

            when (result) {
                is SignInUserResult.Success -> {
                    _uiState.emit(AuthUiState.SuccessfulResult)
                }

                is SignInUserResult.Fail -> {
                    _uiState.emit(AuthUiState.SignInUser(toastMessage = result.exception))
                }
            }
        }
    }

    fun changeScreen(isLoginState: Boolean) {
        viewModelScope.launch {
            if (isLoginState) _uiState.emit(AuthUiState.CreateUser(toastMessage = ""))
            else _uiState.emit(AuthUiState.SignInUser(toastMessage = ""))
        }
    }

    sealed interface AuthUiState {

        data class CreateUser(val toastMessage: String) : AuthUiState

        data class SignInUser(val toastMessage: String) : AuthUiState

        data object CreatingUser : AuthUiState

        data object SigningInUser : AuthUiState

        data object SuccessfulResult : AuthUiState
    }
}