package com.example.player.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.player.R

@Composable
fun CenterControls(
    isPlaying: Boolean,
    onPreviousClick: () -> Unit,
    onReplayClick: () -> Unit,
    onPlayClick: () -> Unit,
    onForwardClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isPlayingState by remember(isPlaying) { mutableStateOf(isPlaying) }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(modifier = Modifier.size(40.dp), onClick = { onPreviousClick() }) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_previous),
                contentDescription = "Previous series"
            )
        }

        IconButton(modifier = Modifier.size(40.dp), onClick = { onReplayClick() }) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_replay_10),
                contentDescription = "Replay 10 sec"
            )
        }

        IconButton(modifier = Modifier.size(40.dp), onClick = { onPlayClick() }) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = if (!isPlayingState) painterResource(id = R.drawable.ic_play)
                else painterResource(id = R.drawable.ic_pause),
                contentDescription = "Play / Pause"
            )
        }

        IconButton(modifier = Modifier.size(40.dp), onClick = { onForwardClick() }) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_forward_10),
                contentDescription = "Forward 10 sec"
            )
        }

        IconButton(modifier = Modifier.size(40.dp), onClick = { onNextClick() }) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_next),
                contentDescription = "Next series"
            )
        }
    }
}