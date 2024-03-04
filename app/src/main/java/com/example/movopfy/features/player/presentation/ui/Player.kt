package com.example.movopfy.features.player.presentation.ui

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

const val SEEK_NUMBER = 10000

@OptIn(UnstableApi::class)
@Composable
fun Player(
    modifier: Modifier = Modifier,
    url: String
) {
    val context = LocalContext.current

    var isPlaying by remember { mutableStateOf(true) }

    var totalDuration by remember { mutableLongStateOf(0L) }

    var currentTime by remember { mutableLongStateOf(0L) }

    var bufferedPercentage by remember { mutableIntStateOf(0) }

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            this.playWhenReady = true
        }
    }

    LaunchedEffect(url) {
        exoPlayer.setMediaItem(MediaItem.fromUri(url))
        exoPlayer.prepare()
    }

    AndroidView(
        factory = { ctx ->
            PlayerView(ctx).apply {
                player = exoPlayer
                this.useController = false
            }
        },
        modifier = modifier
            .fillMaxSize()
    )

    DisposableEffect(Unit) {
        val listener =
            object : Player.Listener {
                override fun onEvents(player: Player, events: Player.Events) {
                    super.onEvents(player, events)
                    totalDuration = player.duration.coerceAtLeast(0L)
                    currentTime = player.currentPosition.coerceAtLeast(0L)
                    bufferedPercentage = player.bufferedPercentage
                }
            }

        exoPlayer.addListener(listener)

        onDispose {
            exoPlayer.removeListener(listener)
            exoPlayer.release()
        }
    }

    CustomControls(
        isPlaying = { isPlaying },
        onPreviousClick = { exoPlayer.seekToPreviousMediaItem() },
        onReplayClick = {
            val seekReplay = exoPlayer.currentPosition - SEEK_NUMBER

            if (seekReplay >= 0) exoPlayer.seekTo(seekReplay)
            else exoPlayer.seekTo(0)
        },
        onPlayClick = {
            if (exoPlayer.isPlaying) {
                exoPlayer.pause()
            } else {
                exoPlayer.play()
            }
            isPlaying = isPlaying.not()
        },
        onForwardClick = {
            val seekForward = exoPlayer.currentPosition + SEEK_NUMBER

            if (seekForward <= exoPlayer.duration) exoPlayer.seekTo(seekForward)
            else exoPlayer.seekTo(exoPlayer.duration)
        },
        onNextClick = { exoPlayer.seekToNextMediaItem() },
        totalDuration = { totalDuration },
        currentTime = { currentTime },
        bufferPercentage = { bufferedPercentage },
        onSeekChanged = { timeMs: Float -> exoPlayer.seekTo(timeMs.toLong()) }
    )
}