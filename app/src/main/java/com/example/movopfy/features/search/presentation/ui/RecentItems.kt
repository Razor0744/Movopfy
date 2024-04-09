package com.example.movopfy.features.search.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movopfy.R
import com.example.movopfy.features.search.domain.models.RecentModel
import com.example.movopfy.features.search.presentation.viewmodel.SearchViewModel
import com.example.movopfy.uiComponents.navigation.Screen
import com.example.movopfy.uiComponents.theme.dimensions

@Composable
fun RecentItems(
    navController: NavController,
    recentModels: List<RecentModel>,
    viewModel: SearchViewModel
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimensions.lazySpace),
        contentPadding = PaddingValues(top = MaterialTheme.dimensions.paddingTop)
    ) {
        items(recentModels) {
            Box(
                modifier = Modifier
                    .height(height = 50.dp)
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(
                            Screen.Details.passId(
                                id = it.titleId,
                                category = it.category
                            )
                        )
                    }
            ) {
                Row(
                    modifier = Modifier
                        .height(height = 50.dp)
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .padding(start = MaterialTheme.dimensions.paddingStart)
                            .height(height = 50.dp)
                            .width(width = 50.dp)
                            .clip(shape = MaterialTheme.shapes.small)
                            .align(alignment = Alignment.CenterVertically),
                        contentScale = ContentScale.FillBounds,
                        model = it.url,
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

                Icon(
                    modifier = Modifier
                        .padding(end = MaterialTheme.dimensions.paddingEnd)
                        .align(alignment = Alignment.CenterEnd)
                        .clickable {
                            viewModel.removeFromRecent(recentModel = it)
                        },
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}