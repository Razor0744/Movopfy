package com.example.movopfy.features.auth.presentation.ui

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movopfy.R
import com.example.movopfy.features.auth.presentation.viewModel.AuthViewModel
import com.example.movopfy.uiComponents.navigation.Screen
import org.koin.androidx.compose.koinViewModel

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

    Surface(modifier = modifier) {
        when (val state = uiState) {
            is AuthViewModel.AuthUiState.CheckingUser -> {
                viewModel.checkUser()
            }

            is AuthViewModel.AuthUiState.HasUser -> {
                navController.navigate(Screen.Home.route)
            }

            is AuthViewModel.AuthUiState.SignInUser -> {
                if (state.toastMessage.isNotEmpty())
                    Toast.makeText(activity, state.toastMessage, Toast.LENGTH_LONG).show()

                Box(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        WelcomeText(modifier = Modifier.align(alignment = Alignment.CenterHorizontally))

                        AuthTextField(
                            modifier = Modifier.padding(top = 30.dp),
                            value = email,
                            onValueChange = { value ->
                                email = value
                            },
                            placeHolder = { Text(text = stringResource(id = R.string.auth_email_placeholder)) })

                        AuthTextField(
                            modifier = Modifier.padding(top = 10.dp),
                            value = password,
                            onValueChange = { value ->
                                password = value
                            },
                            placeHolder = { Text(text = stringResource(id = R.string.auth_password_placeholder)) })

                        ChangeAuthOptionText(
                            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                            text = stringResource(id = R.string.create_user_text),
                            onClick = { viewModel.changeScreen(isLoginState = true) })
                    }

                    ButtonSignIn(
                        modifier = Modifier
                            .align(alignment = Alignment.BottomCenter),
                        onClick = {
                            viewModel.signInUser(
                                email = email,
                                password = password,
                                activity = activity
                            )
                        },
                        textButton = stringResource(id = R.string.sign_in_user_button)
                    )
                }
            }

            is AuthViewModel.AuthUiState.CreateUser -> {
                if (state.toastMessage.isNotEmpty())
                    Toast.makeText(activity, state.toastMessage, Toast.LENGTH_LONG).show()

                Box(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        WelcomeText(modifier = Modifier.align(alignment = Alignment.CenterHorizontally))

                        AuthTextField(
                            modifier = Modifier.padding(top = 30.dp),
                            value = email,
                            onValueChange = { value ->
                                email = value
                            },
                            placeHolder = { Text(text = stringResource(id = R.string.auth_email_placeholder)) })

                        AuthTextField(
                            modifier = Modifier.padding(top = 10.dp),
                            value = password,
                            onValueChange = { value ->
                                password = value
                            },
                            placeHolder = { Text(text = stringResource(id = R.string.auth_password_placeholder)) })

                        ChangeAuthOptionText(
                            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                            text = stringResource(id = R.string.sign_in_user_text),
                            onClick = { viewModel.changeScreen(isLoginState = false) })
                    }

                    ButtonSignIn(
                        modifier = Modifier
                            .align(alignment = Alignment.BottomCenter),
                        onClick = {
                            viewModel.createUser(
                                email = email,
                                password = password,
                                activity = activity
                            )
                        },
                        textButton = stringResource(id = R.string.create_user_button)
                    )
                }
            }

            is AuthViewModel.AuthUiState.CreatingOrSignInUser -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        WelcomeText(modifier = Modifier.align(alignment = Alignment.CenterHorizontally))

                        AuthTextField(
                            modifier = Modifier.padding(top = 30.dp),
                            value = email,
                            onValueChange = { value ->
                                email = value
                            },
                            placeHolder = { Text(text = stringResource(id = R.string.auth_email_placeholder)) })

                        AuthTextField(
                            modifier = Modifier.padding(top = 10.dp),
                            value = password,
                            onValueChange = { value ->
                                password = value
                            },
                            placeHolder = { Text(text = stringResource(id = R.string.auth_password_placeholder)) })
                    }

                    ButtonSignIn(
                        modifier = Modifier
                            .align(alignment = Alignment.BottomCenter),
                        onClick = { },
                        textButton = ""
                    )
                }
            }

            is AuthViewModel.AuthUiState.SuccessfulResult -> {
                navController.navigate(Screen.Home.route)
            }
        }
    }
}