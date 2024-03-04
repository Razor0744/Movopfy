package com.example.movopfy.features.player.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CustomControls(
    modifier: Modifier = Modifier,
    isPlaying: () -> Boolean,
    onPreviousClick: () -> Unit,
    onReplayClick: () -> Unit,
    onPlayClick: () -> Unit,
    onForwardClick: () -> Unit,
    onNextClick: () -> Unit,
    totalDuration: () -> Long,
    currentTime: () -> Long,
    bufferPercentage: () -> Int,
    onSeekChanged: (timeMs: Float) -> Unit
) {
    Box(
        modifier = modifier
            .background(Color.Black.copy(alpha = 0.6f))
            .fillMaxSize()
    ) {
        CenterControls(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .fillMaxWidth(),
            isPlaying = { isPlaying() },
            onPreviousClick = { onPreviousClick() },
            onReplayClick = { onReplayClick() },
            onPlayClick = { onPlayClick() },
            onForwardClick = { onForwardClick() },
            onNextClick = { onNextClick() })
    }
}