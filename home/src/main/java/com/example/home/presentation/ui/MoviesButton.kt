package com.example.home.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.home.R
import com.example.uiComponents.components.Screen
import com.example.uiComponents.theme.dimensions

@Composable
fun MoviesButton(navController: NavController, category: String) {
    Text(
        text = stringResource(id = R.string.more_movies_button),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = MaterialTheme.dimensions.paddingEnd)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }) {
                navController.navigate(route = Screen.Movies.passCategory(category = category))
            }
    )
}