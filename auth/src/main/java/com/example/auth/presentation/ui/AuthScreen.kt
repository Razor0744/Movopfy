package com.example.auth.presentation.ui

import android.app.Activity
import android.widget.Toast
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.auth.presentation.viewModel.AuthViewModel
import com.example.uiComponents.components.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

const val ANIMATION_DURATION = 500

@Composable
fun AuthScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val activity = LocalContext.current as Activity

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    Surface(modifier = modifier) {
        when (val state = uiState) {
            is AuthViewModel.AuthUiState.SignInUser -> {
                LaunchedEffect(key1 = state.toastMessage) {
                    if (state.toastMessage.isNotEmpty())
                        Toast.makeText(activity, state.toastMessage, Toast.LENGTH_LONG).show()
                }

                SignInUserScreen(
                    email = email,
                    password = password,
                    onEmailChange = { email = it },
                    onPasswordChange = { password = it },
                    changeAuthOptionClick = { viewModel.changeScreen(isLoginState = true) },
                    signInButtonClick = {
                        viewModel.signInUser(
                            email = email,
                            password = password,
                            activity = activity
                        )
                    }
                )
            }

            is AuthViewModel.AuthUiState.CreateUser -> {
                LaunchedEffect(key1 = state.toastMessage) {
                    if (state.toastMessage.isNotEmpty())
                        Toast.makeText(activity, state.toastMessage, Toast.LENGTH_LONG).show()
                }

                var isAnimationVisible by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) {
                    isAnimationVisible = true
                }

                val coroutineScope = rememberCoroutineScope()

                CreateUserScreen(
                    email = email,
                    password = password,
                    name = name,
                    onEmailChange = { email = it },
                    onPasswordChange = { password = it },
                    onNameChange = { name = it },
                    changeAuthOptionClick = {
                        coroutineScope.launch {
                            isAnimationVisible = false

                            delay(timeMillis = ANIMATION_DURATION.toLong())

                            viewModel.changeScreen(isLoginState = false)
                        }
                    },
                    createUserButtonClick = {
                        viewModel.createUser(
                            email = email,
                            password = password,
                            activity = activity
                        )
                    },
                    isAnimationVisible = isAnimationVisible
                )
            }

            is AuthViewModel.AuthUiState.CreatingUser -> {
                CreatingUserScreen(
                    email = email,
                    password = password,
                    name = name
                )
            }

            is AuthViewModel.AuthUiState.SigningInUser -> {
                SigningInUserScreen(
                    email = email,
                    password = password,
                )
            }

            is AuthViewModel.AuthUiState.SuccessfulResult -> {
                navController.navigate(Screen.Home.route)
            }
        }
    }
}