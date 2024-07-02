package com.example.details.presentation.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TitleText(modifier: Modifier = Modifier, text: String?) {
    Text(
        modifier = modifier,
        text = text.toString(),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onPrimary
    )
}