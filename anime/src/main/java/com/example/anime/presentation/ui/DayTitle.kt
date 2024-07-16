package com.example.anime.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uiComponents.theme.dimensions

@Composable
fun DayTitle(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier.padding(
            start = MaterialTheme.dimensions.paddingStart,
            top = 10.dp
        ),
        text = text,
        color = MaterialTheme.colorScheme.onPrimary,
        style = MaterialTheme.typography.titleLarge
    )
}