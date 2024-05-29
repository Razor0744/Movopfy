package com.example.movopfy.features.auth.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movopfy.R

@Composable
fun WelcomeText(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .padding(top = 50.dp),
        text = stringResource(id = R.string.auth_welcome),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onPrimary
    )
}