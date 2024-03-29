package com.example.movopfy.features.player.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.movopfy.R
import com.example.movopfy.common.extensions.formatMinSec

@Composable
fun BottomControls(
    modifier: Modifier = Modifier,
    totalDuration: () -> Long,
    currentTime: () -> Long,
    onSeekChanged: (timeMs: Float) -> Unit,
    onFullScreenClick: () -> Unit
) {
    val duration = remember(totalDuration()) { totalDuration() }

    val videoTime = remember(currentTime()) { currentTime() }

    Column(modifier = modifier.padding(bottom = 32.dp)) {
        Slider(
            modifier = Modifier.fillMaxWidth(),
            value = videoTime.toFloat(),
            onValueChange = onSeekChanged,
            valueRange = 0f..duration.toFloat(),
            colors =
            SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTickColor = MaterialTheme.colorScheme.primary
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = videoTime.formatMinSec(),
                color = MaterialTheme.colorScheme.primary,
            )

            IconButton(
                onClick = { onFullScreenClick() }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_fullscreen),
                    contentDescription = "Enter/Exit fullscreen"
                )
            }
        }
    }
}