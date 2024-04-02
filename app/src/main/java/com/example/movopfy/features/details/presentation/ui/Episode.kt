package com.example.movopfy.features.details.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movopfy.R

@Composable
fun Episode(
    modifier: Modifier = Modifier,
    episode: Int?,
    name: String?,
    onSeriesClick: () -> Unit
) {
    Column(modifier = modifier
        .width(width = 180.dp)
        .clickable {
            onSeriesClick()
        }) {
        Image(
            modifier = Modifier
                .height(height = 120.dp)
                .width(width = 180.dp)
                .background(color = MaterialTheme.colorScheme.onPrimary),
            painter = painterResource(id = R.drawable.ic_play),
            contentDescription = null
        )

        Text(text = stringResource(id = R.string.episode_anime_list, episode.toString()))

        Text(text = name.toString())
    }
}