package com.example.movopfy.features.anime.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movopfy.uiComponents.theme.dimensions

@Composable
fun DayTitle(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(
            start = MaterialTheme.dimensions.paddingStart,
            top = 10.dp
        ),
        style = MaterialTheme.typography.titleLarge
    )
}