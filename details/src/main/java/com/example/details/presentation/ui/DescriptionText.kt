package com.example.details.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.uiComponents.theme.dimensions

@Composable
fun DescriptionText(description: String?) {
    Text(
        text = description.toString(),
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSecondary,
        modifier = Modifier.padding(
            start = MaterialTheme.dimensions.paddingStart,
            end = MaterialTheme.dimensions.paddingEnd,
            top = MaterialTheme.dimensions.paddingTop
        )
    )
}