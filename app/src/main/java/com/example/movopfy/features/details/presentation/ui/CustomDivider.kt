package com.example.movopfy.features.details.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movopfy.uiComponents.theme.dimensions

@Composable
fun CustomDivider(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(
                start = MaterialTheme.dimensions.paddingStart,
                end = MaterialTheme.dimensions.paddingEnd,
                top = MaterialTheme.dimensions.paddingTop
            )
            .height(height = 1.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.onPrimary)

    )
}