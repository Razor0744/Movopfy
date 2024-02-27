package com.example.movopfy.features.details.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.movopfy.uiComponents.theme.dimensions

@Composable
fun PlayerButton() {
    OutlinedButton(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = MaterialTheme.dimensions.paddingStart,
                end = MaterialTheme.dimensions.paddingEnd,
                top = MaterialTheme.dimensions.paddingTop
            )
    ) {
        Text(
            text = "Начать просмотр",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}