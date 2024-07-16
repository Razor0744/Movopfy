package com.example.auth.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChangeAuthOptionText(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .padding(top = 10.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() },
        text = text,
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.labelSmall
    )
}