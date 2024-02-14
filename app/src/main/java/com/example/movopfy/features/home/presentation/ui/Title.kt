package com.example.movopfy.features.home.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movopfy.uiComponents.theme.dimensions

@Composable
fun Title(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onPrimary,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(
            start = MaterialTheme.dimensions.paddingStart, top = 10.dp
        )
    )
}