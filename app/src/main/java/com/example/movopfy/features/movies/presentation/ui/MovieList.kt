package com.example.movopfy.features.movies.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movopfy.network.kinopoisk.models.KinopoiskDocs
import com.example.movopfy.uiComponents.theme.dimensions

@Composable
fun MovieList(
    modifier: Modifier = Modifier,
    list: List<KinopoiskDocs>,
    state: LazyGridState,
    navController: NavController
) {
    LazyVerticalGrid(
        modifier = modifier,
        contentPadding = PaddingValues(
            start = MaterialTheme.dimensions.paddingStart,
            end = MaterialTheme.dimensions.paddingEnd
        ),
        horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimensions.lazySpace),
        verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimensions.lazySpace),
        state = state,
        columns = GridCells.Fixed(count = 3),
        content = {
            items(count = list.size) {
                AsyncImage(
                    model = list[it].poster?.previewUrl,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(height = 180.dp)
                        .width(width = 120.dp)
                        .clip(shape = MaterialTheme.shapes.medium)
                        .clickable {
                        }
                )
            }
        }
    )
}