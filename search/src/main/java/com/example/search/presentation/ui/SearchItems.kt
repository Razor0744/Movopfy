package com.example.search.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.search.domain.models.RecentModel
import com.example.search.domain.models.SearchTitle
import com.example.search.presentation.viewmodel.SearchViewModel
import com.example.uiComponents.components.Screen
import com.example.uiComponents.theme.dimensions

@Composable
fun SearchItems(
    navController: NavController,
    searchTitles: List<SearchTitle>,
    viewModel: SearchViewModel
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimensions.lazySpace),
        contentPadding = PaddingValues(top = MaterialTheme.dimensions.paddingTop)
    ) {
        items(searchTitles) {
            Row(modifier = Modifier
                .height(height = 50.dp)
                .fillMaxWidth()
                .clickable {
                    viewModel.addToRecent(
                        recentModel = RecentModel(
                            name = it.name,
                            titleId = it.id,
                            category = it.category,
                            url = it.imageUrl
                        )
                    )

                    navController.navigate(
                        Screen.Details.passId(
                            id = it.id,
                            category = it.category
                        )
                    )
                }) {
                AsyncImage(
                    modifier = Modifier
                        .padding(start = MaterialTheme.dimensions.paddingStart)
                        .height(height = 50.dp)
                        .width(width = 50.dp)
                        .clip(shape = MaterialTheme.shapes.small)
                        .align(alignment = Alignment.CenterVertically),
                    contentScale = ContentScale.FillBounds,
                    model = it.imageUrl,
                    contentDescription = null
                )

                Text(
                    modifier = Modifier
                        .padding(start = MaterialTheme.dimensions.paddingStart)
                        .align(alignment = Alignment.CenterVertically),
                    text = it.name,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}