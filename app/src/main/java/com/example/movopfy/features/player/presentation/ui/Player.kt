package com.example.movopfy.features.player.presentation.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.movopfy.common.extensions.findActivity
import com.example.movopfy.common.extensions.setLandscape
import com.example.movopfy.common.extensions.setPortrait
import com.example.movopfy.database.models.player.PlayerMarks
import com.example.movopfy.features.player.presentation.viewmodel.PlayerViewModel
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

const val SEEK_NUMBER = 10000

@SuppressLint("SourceLockedOrientationActivity")
@OptIn(UnstableApi::class)
@Composable
fun Player(
    url: String,
    episodesCount: Int,
    id: Int,
    episode: Int,
    playerMarks: PlayerMarks?,
    viewModel: PlayerViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Log.i("play", "Player: $episode")
    var isPlaying by remember { mutableStateOf(true) }

    var totalDuration by remember { mutableLongStateOf(0L) }

    var currentTime by remember { mutableLongStateOf(playerMarks?.currentTime ?: 0) }

    var isVisible by remember { mutableStateOf(false) }

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            playWhenReady = true
        }
    }

    LaunchedEffect(url) {
        exoPlayer.apply {
            setMediaItem(MediaItem.fromUri(url))
            prepare()
        }
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
            .clickable {
                isVisible = isVisible.not()
            }
            .background(Color.Black)
    )

    LaunchedEffect(Unit) {
        exoPlayer.seekTo(currentTime)

        while (true) {
            delay(1.seconds)
            currentTime = exoPlayer.currentPosition.coerceAtLeast(0L)
        }
    }

    DisposableEffect(Unit) {
        val window = context.findActivity()?.window ?: return@DisposableEffect onDispose {}
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)

        insetsController.apply {
            hide(WindowInsetsCompat.Type.statusBars())
            hide(WindowInsetsCompat.Type.navigationBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        val listener =
            object : Player.Listener {
                override fun onEvents(player: Player, events: Player.Events) {
                    super.onEvents(player, events)
                    totalDuration = player.duration.coerceAtLeast(0L)
                }
            }

        exoPlayer.apply {
            addListener(listener)
        }

        onDispose {
            exoPlayer.apply {
                removeListener(listener)
                release()
            }

            context.setPortrait()

            insetsController.apply {
                show(WindowInsetsCompat.Type.statusBars())
                show(WindowInsetsCompat.Type.navigationBars())
                systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
            }
        }
    }

    DisposableEffect(episode) {
        onDispose {
            viewModel.saveTime(
                playerMarks = PlayerMarks(
                    id = playerMarks?.id,
                    currentTime = currentTime,
                    episodeId = playerMarks?.episodeId ?: episode,
                    titleId = playerMarks?.titleId ?: id
                )
            )
            Log.i("play", "Player: $playerMarks")
            Log.i("play", "Player: $episode")
        }
    }

    CustomControls(
        isPlaying = isPlaying,
        onPreviousClick = {
            if (episode != 0) viewModel.getPlayerState(
                id = id,
                episode = episode - 1
            )
        },
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
        onNextClick = {
            if (episode != episodesCount) viewModel.getPlayerState(
                id = id,
                episode = episode + 1
            )
        },
        totalDuration = totalDuration,
        currentTime = currentTime,
        onSeekChanged = { timeMs: Float -> exoPlayer.seekTo(timeMs.toLong()) },
        isVisible = isVisible,
        onFullScreenClick = {
            if (!viewModel.isFullScreen) {
                context.setLandscape()
            } else {
                context.setPortrait()
            }

            viewModel.isFullScreen = viewModel.isFullScreen.not()
        }
    )
}