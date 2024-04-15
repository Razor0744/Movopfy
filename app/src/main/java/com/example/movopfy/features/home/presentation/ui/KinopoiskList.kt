package com.example.movopfy.features.home.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movopfy.common.constants.API_CATEGORY_KINOPOISK
import com.example.movopfy.features.home.domain.models.KinopoiskItem
import com.example.movopfy.uiComponents.navigation.Screen
import com.example.movopfy.uiComponents.theme.dimensions

@Composable
fun KinopoiskList(
    kinopoiskItems: List<KinopoiskItem>,
    navController: NavController
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 180.dp)
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimensions.lazySpace),
        contentPadding = PaddingValues(
            start = MaterialTheme.dimensions.paddingStart,
            end = MaterialTheme.dimensions.paddingEnd
        )
    ) {
        items(kinopoiskItems) {
            AsyncImage(
                model = it.previewUrl,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(height = 180.dp)
                    .width(width = 120.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
                    .clickable {
                        navController.navigate(
                            route = Screen.Details.passId(
                                id = it.id,
                                category = API_CATEGORY_KINOPOISK
                            )
                        )
                    }
            )
        }
    }
}