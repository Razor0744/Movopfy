package com.example.movopfy.features.settings.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movopfy.R
import com.example.firebase.user.UserManager
import com.example.movopfy.uiComponents.navigation.Screen
import com.example.movopfy.uiComponents.theme.dimensions

@Composable
fun SettingsScreen(
    navController: NavController,
    userManager: com.example.firebase.user.UserManager,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier.fillMaxSize()) {
        Box {
            Text(
                modifier = Modifier
                    .padding(
                        paddingValues = PaddingValues(
                            start = MaterialTheme.dimensions.paddingStart,
                            top = 30.dp
                        )
                    ),
                text = stringResource(id = R.string.setting_text),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )

            Button(modifier = Modifier
                .padding(
                    paddingValues = PaddingValues(
                        start = MaterialTheme.dimensions.paddingStart,
                        end = MaterialTheme.dimensions.paddingEnd,
                        top = MaterialTheme.dimensions.paddingTop
                    )
                )
                .align(alignment = Alignment.BottomCenter)
                .fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                onClick = {
                    userManager.logOut()
                    navController.navigate(Screen.Auth.route)
                }) {
                Text(
                    text = stringResource(id = R.string.login_out_button)
                )

                Icon(
                    modifier = Modifier.padding(start = 5.dp),
                    painter = painterResource(id = R.drawable.ic_logout),
                    contentDescription = null,
                    tint = Color.Red
                )
            }
        }
    }
}