package com.example.movopfy.features.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movopfy.R
import com.example.movopfy.features.home.domain.models.WaitingListToday
import com.example.movopfy.main.ui.theme.BackgroundMain
import com.example.movopfy.main.ui.theme.Dimensions
import com.example.movopfy.main.ui.theme.LocalDim
import com.example.movopfy.main.ui.theme.TextMain
import com.example.movopfy.uiComponents.ProgressBarLoading
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel(), ) {

    val uiState by viewModel.uiState.collectAsState()

    val dimensions = LocalDim.current

    Column(
        modifier = Modifier
            .background(color = BackgroundMain)
            .fillMaxSize()
    ) {
        TextWaitingListToday(dimensions = dimensions)

        when (val state = uiState) {
            is HomeViewModel.HomeUiState.Loading -> {
                ProgressBarLoading()
            }

            is HomeViewModel.HomeUiState.Loaded -> {
                LazyRowWaitingListToday(list = state.schedules, dimensions = dimensions)
            }
        }
    }
}

@Composable
fun TextWaitingListToday(dimensions: Dimensions) {
    Text(
        text = stringResource(R.string.text_waiting_today_home_screen),
        color = TextMain,
        fontSize = dimensions.textSizeMain,
        fontFamily = FontFamily(Font(R.font.inter_bold)),
        modifier = Modifier.padding(
            start = dimensions.paddingStart,
            top = 100.dp
        )
    )
}

@Composable
fun LazyRowWaitingListToday(list: List<WaitingListToday>, dimensions: Dimensions) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 20.dp),
        contentPadding = PaddingValues(horizontal = dimensions.paddingStart)
    ) {
        items(count = list.size) {
            AsyncImage(
                model = list[it].pictureUrl,
                contentDescription = null,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(size = 15.dp))
            )
        }
    }
}