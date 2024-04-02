package com.example.movopfy.features.favourite.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movopfy.features.favourite.domain.models.FavouriteItem
import com.example.movopfy.uiComponents.navigation.Screen
import com.example.movopfy.uiComponents.theme.dimensions

@Composable
fun FavouriteItem(
    modifier: Modifier = Modifier,
    list: List<FavouriteItem>,
    navController: NavController
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(count = 3),
        contentPadding = PaddingValues(
            start = MaterialTheme.dimensions.paddingStart,
            end = MaterialTheme.dimensions.paddingEnd
        ),
        horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimensions.lazySpace),
        verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimensions.lazySpace)
    ) {
        items(list) { item ->
            AsyncImage(
                model = item.url,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(height = 180.dp)
                    .width(width = 120.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
                    .clickable {
                        navController.navigate(
                            Screen.Details.passId(
                                id = item.titleId,
                                category = item.category
                            )
                        )
                    }
            )
        }
    }
}