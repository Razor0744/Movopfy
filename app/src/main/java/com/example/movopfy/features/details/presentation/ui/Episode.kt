package com.example.movopfy.features.details.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movopfy.R
import com.example.movopfy.uiComponents.theme.dimensions

@Composable
fun Episode(
    modifier: Modifier = Modifier,
    episode: Int?,
    name: String,
    color: Color,
    onSeriesClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(height = 52.dp)
            .background(color = color)
            .clickable { onSeriesClick() }
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MaterialTheme.dimensions.paddingStart),
            text = stringResource(id = R.string.episode_anime_list, episode.toString(), name)
        )
    }
}