package com.example.movopfy.features.title.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movopfy.R
import com.example.movopfy.main.ui.theme.BackgroundMain
import com.example.movopfy.main.ui.theme.Dimensions
import com.example.movopfy.main.ui.theme.LocalDim
import com.example.movopfy.main.ui.theme.TextMain
import org.koin.androidx.compose.koinViewModel

@Composable
fun TitleScreen(viewModel: TitleViewModel = koinViewModel(), id: Int) {

    val uiState by viewModel.uiState.collectAsState()

    viewModel.getTitleById(id = id)

    val dimensions = LocalDim.current

    Box(
        modifier = Modifier
            .background(color = BackgroundMain)
            .fillMaxSize()
    )

    when (val state = uiState) {
        is TitleViewModel.TitleUiState.Loading -> println("load")
        is TitleViewModel.TitleUiState.Loaded -> {

            LazyColumn {
                item {
                    AsyncImage(
                        model = "https://www.anilibria.tv${state.title?.anilibriaPosters?.original?.url}",
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

                item {
                    NameOfTitle(dimensions = dimensions, name = state.title?.anilibriaNames?.ru)
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

@Composable
fun NameOfTitle(dimensions: Dimensions, name: String?) {
    Text(
        modifier = Modifier.padding(start = dimensions.paddingStart),
        text = name.toString(),
        fontFamily = FontFamily(Font(R.font.inter_extrabold)),
        fontSize = dimensions.textSizeMain,
        color = TextMain
    )
}

@Composable
fun DescriptionOfTitle(dimensions: Dimensions, description: String?) {
    Text(
        text = description.toString(),
        modifier = Modifier.padding(start = dimensions.paddingStart, top = 20.dp),
        fontFamily = FontFamily(Font(R.font.inter_bold)),
        fontSize = 15.sp,
        color = TextMain
    )
}