package com.example.movopfy.features.details.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.movopfy.R
import com.example.movopfy.uiComponents.navigation.Screen
import com.example.movopfy.uiComponents.theme.dimensions

@Composable
fun PlayerButton(
    modifier: Modifier = Modifier,
    navController: NavController,
    id: Int,
    episode: Int
) {
    TextButton(
        onClick = { navController.navigate(Screen.Player.passId(id = id, episode = episode)) },
        modifier = modifier
            .padding(
                start = MaterialTheme.dimensions.paddingStart,
                end = MaterialTheme.dimensions.paddingEnd,
                top = MaterialTheme.dimensions.paddingTop
            )
            .clip(shape = MaterialTheme.shapes.large)
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = stringResource(id = R.string.play_anime_button),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}