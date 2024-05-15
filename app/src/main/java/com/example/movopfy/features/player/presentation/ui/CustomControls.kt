package com.example.movopfy.features.player.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CustomControls(
    isPlaying: Boolean,
    onPreviousClick: () -> Unit,
    onReplayClick: () -> Unit,
    onPlayClick: () -> Unit,
    onForwardClick: () -> Unit,
    onNextClick: () -> Unit,
    totalDuration: Long,
    currentTime: Long,
    onSeekChanged: (timeMs: Float) -> Unit,
    isVisible: Boolean,
    onFullScreenClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isVisibleState by remember(isVisible) { mutableStateOf(isVisible) }

    AnimatedVisibility(
        modifier = modifier,
        visible = isVisibleState,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier
                .background(Color.Black.copy(alpha = 0.6f))
                .fillMaxSize()
        ) {
            CenterControls(
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .fillMaxWidth(),
                isPlaying = isPlaying,
                onPreviousClick = { onPreviousClick() },
                onReplayClick = { onReplayClick() },
                onPlayClick = { onPlayClick() },
                onForwardClick = { onForwardClick() },
                onNextClick = { onNextClick() })

            BottomControls(
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .animateEnterExit(
                        enter =
                        slideInVertically(
                            initialOffsetY = { fullHeight: Int ->
                                fullHeight
                            }
                        ),
                        exit =
                        slideOutVertically(
                            targetOffsetY = { fullHeight: Int ->
                                fullHeight
                            }
                        )
                    ),
                totalDuration = totalDuration,
                currentTime = currentTime,
                onSeekChanged = { timeMs: Float -> onSeekChanged(timeMs) },
                onFullScreenClick = { onFullScreenClick() })

        }
    }
}