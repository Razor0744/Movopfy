package com.example.movopfy.features.title.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.example.movopfy.common.extensions.getSmallImageUrl
import com.example.movopfy.features.title.presentation.viewmodel.TitleViewModel
import com.example.movopfy.uiComponents.components.ProgressBarLoading
import com.example.movopfy.uiComponents.theme.BackgroundMain
import com.example.movopfy.uiComponents.theme.LocalDim
import org.koin.androidx.compose.koinViewModel

@Composable
fun TitleScreen(viewModel: TitleViewModel = koinViewModel(), id: Int) {

    val uiState by viewModel.uiState.collectAsState()

    val dimensions = LocalDim.current

    LaunchedEffect(key1 = Unit) {
        viewModel.getTitleById(id = id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundMain)
    ) {
        when (val state = uiState) {
            is TitleViewModel.TitleUiState.Loading -> {
                ProgressBarLoading()
            }

            is TitleViewModel.TitleUiState.Loaded -> {
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
                        NameOfTitle(
                            dimensions = dimensions,
                            name = state.title?.anilibriaNames?.ru
                        )
                    }

                    item {
                        DescriptionOfTitle(
                            dimensions = dimensions,
                            description = state.title?.description
                        )
                    }
                }
            }
        }
    }
}