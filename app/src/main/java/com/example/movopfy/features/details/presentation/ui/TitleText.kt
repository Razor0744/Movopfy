package com.example.movopfy.features.details.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.movopfy.uiComponents.theme.dimensions

@Composable
fun TitleText(text: String?) {
    Text(
        modifier = Modifier
            .padding(
                start = MaterialTheme.dimensions.paddingStart,
                top = MaterialTheme.dimensions.paddingTop
            ),
        text = text.toString(),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onPrimary
    )
}