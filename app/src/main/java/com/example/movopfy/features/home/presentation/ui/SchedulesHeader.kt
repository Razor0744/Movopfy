package com.example.movopfy.features.home.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.movopfy.R
import com.example.movopfy.uiComponents.theme.AppTheme

@Composable
fun SchedulesHeader() {
    Text(
        text = stringResource(R.string.schedule_for_today_text),
        color = AppTheme.colorScheme.textPrimary,
        style = AppTheme.typography.textLarge,
        modifier = Modifier.padding(
            start = AppTheme.dimensions.paddingStart
        )
    )
}