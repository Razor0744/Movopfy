package com.example.movopfy.features.details.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movopfy.common.constants.API_CATEGORY_ANILIBRIA
import com.example.movopfy.common.constants.API_CATEGORY_KINOPOISK
import com.example.movopfy.database.models.favourite.Favourite
import com.example.movopfy.features.details.presentation.viewmodel.DetailsViewModel
import com.example.movopfy.uiComponents.components.FavouriteIcon
import com.example.movopfy.uiComponents.components.ProgressBarLoading
import com.example.movopfy.uiComponents.navigation.Screen
import com.example.movopfy.uiComponents.theme.dimensions
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = koinViewModel(),
    id: Int,
    category: String,
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        when (category) {
            API_CATEGORY_ANILIBRIA -> viewModel.getTitleAnilibria(id = id)
            API_CATEGORY_KINOPOISK -> viewModel.getTitleKinopoisk(id = id)
        }
    }

    Surface {
        when (val state = uiState) {
            is DetailsViewModel.DetailsUiState.Loading -> {
                ProgressBarLoading()
            }

            is DetailsViewModel.DetailsUiState.Loaded -> {
                LazyColumn {
                    item {
                        AsyncImage(
                            model = state.detailsState?.detailsData?.url,
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .height(510.dp)
                                .fillMaxWidth()
                        )
                    }

                    item {
                        PlayerButton(navController = navController, id = id, episode = 0)
                    }

                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            TitleText(
                                modifier = Modifier
                                    .align(alignment = Alignment.BottomStart)
                                    .padding(
                                        start = MaterialTheme.dimensions.paddingStart,
                                        top = MaterialTheme.dimensions.paddingTop,
                                        end = 32.dp
                                    ),
                                text = state.detailsState?.detailsData?.name
                            )

                            FavouriteIcon(
                                modifier = Modifier
                                    .align(alignment = Alignment.BottomEnd)
                                    .padding(
                                        paddingValues = PaddingValues(
                                            end = MaterialTheme.dimensions.paddingEnd,
                                            bottom = 4.dp
                                        )
                                    ),
                                isFavorite = state.detailsState?.favourite != null,
                            ) {
                                if (state.detailsState?.favourite != null) {
                                    viewModel.removeFromFavourite(
                                        favourite = state.detailsState.favourite
                                    )

                                    when (category) {
                                        API_CATEGORY_ANILIBRIA -> viewModel.getTitleAnilibria(id = id)
                                        API_CATEGORY_KINOPOISK -> viewModel.getTitleKinopoisk(id = id)
                                    }
                                } else {
                                    viewModel.addToFavourite(
                                        favourite = Favourite(
                                            name = state.detailsState?.detailsData?.name ?: "",
                                            isWatched = true,
                                            titleId = id
                                        )
                                    )

                                    when (category) {
                                        API_CATEGORY_ANILIBRIA -> viewModel.getTitleAnilibria(id = id)
                                        API_CATEGORY_KINOPOISK -> viewModel.getTitleKinopoisk(id = id)
                                    }
                                }
                            }
                        }
                    }

                    item {
                        CustomDivider()
                    }

                    item {
                        DescriptionText(
                            description = state.detailsState?.detailsData?.description
                        )
                    }

                    item {
                        CustomDivider()
                    }

                    item {
                        Box(
                            modifier = Modifier
                                .height(height = MaterialTheme.dimensions.paddingTop)
                                .fillMaxWidth()
                        )
                    }

                    state.detailsState?.detailsData?.episodesList?.let {
                        itemsIndexed(it.reversed()) { index, item ->
                            Episode(
                                episode = item.episode,
                                name = item.name ?: "",
                                color = if (index % 2 == 0) MaterialTheme.colorScheme.onSecondary
                                else MaterialTheme.colorScheme.background
                            ) {
                                navController.navigate(
                                    Screen.Player.passId(
                                        id = id,
                                        episode = item.episode?.minus(1) ?: 0
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}