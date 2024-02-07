package com.example.movopfy.features.details.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.example.movopfy.common.extensions.getSmallImageUrl
import com.example.movopfy.features.details.presentation.viewmodel.DetailsViewModel
import com.example.movopfy.uiComponents.components.ProgressBarLoading
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(viewModel: DetailsViewModel = koinViewModel(), id: Int) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getTitleById(id = id)
    }

    Scaffold {
        Column(
            modifier = Modifier.padding(it)
        ) {
            when (val state = uiState) {
                is DetailsViewModel.DetailsUiState.Loading -> {
                    ProgressBarLoading()
                }

                is DetailsViewModel.DetailsUiState.Loaded -> {
                    LazyColumn {
                        item {
                            AsyncImage(
                                model = state.title?.getSmallImageUrl(),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }

                        item {
                            TitleText(
                                name = state.title?.anilibriaNames?.ru
                            )
                        }

                        item {
                            DescriptionText(
                                description = state.title?.description
                            )
                        }
                    }
                }
            }
        }
    }
}