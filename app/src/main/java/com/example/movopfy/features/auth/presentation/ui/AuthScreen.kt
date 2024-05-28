package com.example.movopfy.features.auth.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movopfy.R
import com.example.movopfy.features.auth.presentation.viewModel.AuthViewModel
import com.example.movopfy.uiComponents.navigation.Screen
import com.example.movopfy.uiComponents.theme.dimensions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = koinViewModel()
) {
    val auth = Firebase.auth
    val currentUser = auth.currentUser
    if (currentUser != null) {
        navController.navigate(Screen.Home.route)
    }

    val uiState by viewModel.uiState.collectAsState()

    Surface(modifier = modifier) {
        when (val state = uiState) {
            is AuthViewModel.AuthUiState.Loading -> {

            }

            is AuthViewModel.AuthUiState.Loaded -> {
                Box(modifier = Modifier) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Text(
                            modifier = Modifier
                                .align(alignment = Alignment.CenterHorizontally)
                                .padding(top = 50.dp),
                            text = stringResource(id = R.string.auth_welcome),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onPrimary
                        )

                        var email by remember { mutableStateOf("") }
                        TextField(
                            modifier = Modifier
                                .padding(
                                    paddingValues = PaddingValues(
                                        top = 30.dp,
                                        start = MaterialTheme.dimensions.paddingStart,
                                        end = MaterialTheme.dimensions.paddingEnd
                                    )
                                )
                                .fillMaxWidth(),
                            value = email,
                            onValueChange = { email = it },
                            placeholder = { Text(text = stringResource(id = R.string.auth_password_placeholder)) }
                        )

                        var password by remember { mutableStateOf("") }
                        TextField(
                            modifier = Modifier
                                .padding(
                                    paddingValues = PaddingValues(
                                        top = 10.dp,
                                        start = MaterialTheme.dimensions.paddingStart,
                                        end = MaterialTheme.dimensions.paddingEnd
                                    )
                                )
                                .fillMaxWidth(),
                            value = password,
                            onValueChange = { password = it },
                            placeholder = { Text(text = stringResource(id = R.string.auth_password_placeholder)) }
                        )
                    }

                    Button(
                        modifier = Modifier
                            .align(alignment = Alignment.BottomCenter)
                            .padding(
                                paddingValues = PaddingValues(
                                    bottom = 20.dp,
                                    start = MaterialTheme.dimensions.paddingStart,
                                    end = MaterialTheme.dimensions.paddingEnd
                                )
                            )
                            .height(height = 50.dp)
                            .fillMaxWidth(),
                        onClick = { /*TODO*/ }) {
                        Text(
                            modifier = Modifier,
                            text = stringResource(id = R.string.auth_continue_button),
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }
    }
}