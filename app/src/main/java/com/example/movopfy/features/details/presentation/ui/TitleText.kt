package com.example.movopfy.features.details.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.movopfy.uiComponents.theme.dimensions

@Composable
fun TitleText(name: String?) {
    Text(
        modifier = Modifier.padding(start = MaterialTheme.dimensions.paddingStart),
        text = name.toString(),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onPrimary
    )
}