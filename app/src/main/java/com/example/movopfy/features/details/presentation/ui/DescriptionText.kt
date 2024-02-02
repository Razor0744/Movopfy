package com.example.movopfy.features.details.presentation.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.movopfy.uiComponents.theme.AppTheme

@Composable
fun DescriptionText(description: String?) {
    Text(
        text = description.toString(),
        style = AppTheme.typography.textMedium,
        color = AppTheme.colorScheme.textSecondary
    )
}