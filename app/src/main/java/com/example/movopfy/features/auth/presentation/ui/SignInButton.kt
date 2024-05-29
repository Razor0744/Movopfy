package com.example.movopfy.features.auth.presentation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movopfy.uiComponents.components.ProgressBarLoading
import com.example.movopfy.uiComponents.theme.dimensions

@Composable
fun ButtonSignIn(
    onClick: () -> Unit,
    textButton: String,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier
            .padding(
                paddingValues = PaddingValues(
                    bottom = 20.dp,
                    start = MaterialTheme.dimensions.paddingStart,
                    end = MaterialTheme.dimensions.paddingEnd
                )
            )
            .height(height = 50.dp)
            .fillMaxWidth(),
        onClick = onClick
    ) {
        if (textButton.isNotEmpty()) {
            Text(
                modifier = Modifier,
                text = textButton,
                style = MaterialTheme.typography.labelMedium
            )
        } else {
            ProgressBarLoading()
        }
    }
}