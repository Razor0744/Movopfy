package com.example.movopfy.features.details.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movopfy.common.constants.API_CATEGORY_ANILIBRIA
import com.example.movopfy.common.constants.API_CATEGORY_KINOPOISK
import com.example.movopfy.common.extensions.getSmallImageUrl
import com.example.movopfy.common.mappers.anilibria.mapToAnilibriaEpisodesList
import com.example.movopfy.features.details.presentation.viewmodel.DetailsViewModel
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
                        val url = when (category) {
                            API_CATEGORY_ANILIBRIA -> state.anilibriaTitle?.getSmallImageUrl()
                            API_CATEGORY_KINOPOISK -> state.kinopoiskTitle?.poster?.url
                            else -> ""
                        }

                        AsyncImage(
                            model = url,
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
                        val title = when (category) {
                            API_CATEGORY_ANILIBRIA -> state.anilibriaTitle?.anilibriaNames?.ru
                            API_CATEGORY_KINOPOISK -> state.kinopoiskTitle?.name
                            else -> ""
                        }

                        TitleText(
                            text = title
                        )
                    }

                    item {
                        CustomDivider()
                    }

                    item {
                        val description = when (category) {
                            API_CATEGORY_ANILIBRIA -> state.anilibriaTitle?.description
                            API_CATEGORY_KINOPOISK -> state.kinopoiskTitle?.description
                            else -> ""
                        }

                        DescriptionText(
                            description = description
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

                    itemsIndexed(mapToAnilibriaEpisodesList(state.anilibriaTitle?.player?.list).reversed()) { index, item ->
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