package com.example.movopfy.features.details.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.movopfy.features.details.presentation.viewmodel.DetailsViewModel
import com.example.movopfy.uiComponents.components.ProgressBarLoading
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
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

    Surface(modifier = modifier) {
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
                }
            }
        }
    }
}