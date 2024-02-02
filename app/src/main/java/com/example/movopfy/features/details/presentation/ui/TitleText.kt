package com.example.movopfy.features.details.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.movopfy.uiComponents.theme.AppTheme

@Composable
fun TitleText(name: String?) {
    Text(
        modifier = Modifier.padding(start = AppTheme.dimensions.paddingStart),
        text = name.toString(),
        style = AppTheme.typography.textLarge,
        color = AppTheme.colorScheme.textPrimary
    )
}