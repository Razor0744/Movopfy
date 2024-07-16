package com.example.auth.presentation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.uiComponents.theme.dimensions

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (text: String) -> Unit,
    placeHolder: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        modifier = modifier
            .padding(
                paddingValues = PaddingValues(
                    start = MaterialTheme.dimensions.paddingStart,
                    end = MaterialTheme.dimensions.paddingEnd
                )
            )
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        placeholder = placeHolder
    )
}