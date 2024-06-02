package com.example.movopfy.features.auth.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movopfy.R

@Composable
fun SigningInUserScreen(
    email: String,
    password: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            WelcomeText(modifier = Modifier.align(alignment = Alignment.CenterHorizontally))

            AuthTextField(
                modifier = Modifier.padding(top = 30.dp),
                value = email,
                onValueChange = { },
                placeHolder = { Text(text = stringResource(id = R.string.auth_email_placeholder)) })

            AuthTextField(
                modifier = Modifier.padding(top = 10.dp),
                value = password,
                onValueChange = { },
                placeHolder = { Text(text = stringResource(id = R.string.auth_password_placeholder)) })
        }

        SignInButton(
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter),
            onClick = { },
            textButton = ""
        )
    }
}