package com.example.movopfy.features.home.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movopfy.features.home.domain.models.WaitingListToday
import com.example.movopfy.uiComponents.navigation.Screen
import com.example.movopfy.uiComponents.theme.AppTheme

@Composable
fun SchedulesList(
    list: List<WaitingListToday>,
    navController: NavController
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(space = AppTheme.dimensions.lazySpace),
        contentPadding = PaddingValues(
            start = AppTheme.dimensions.paddingStart,
            end = AppTheme.dimensions.paddingEnd
        )
    ) {
        items(count = list.size) {
            AsyncImage(
                model = list[it].pictureUrl,
                contentDescription = null,
                modifier = Modifier
                    .clip(shape = AppTheme.shape.image)
                    .clickable {
                        navController.navigate(route = Screen.Details.passId(id = list[it].id ?: 0))
                    }
            )
        }
    }
}