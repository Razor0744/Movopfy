package com.example.movopfy.features.home.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movopfy.features.home.domain.models.WaitingListToday
import com.example.movopfy.uiComponents.navigation.Screen
import com.example.movopfy.uiComponents.theme.Dimensions

@Composable
fun SchedulesList(
    list: List<WaitingListToday>,
    dimensions: Dimensions,
    navController: NavController
) {
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
                    .clip(shape = RoundedCornerShape(size = dimensions.radius))
                    .clickable {
                        navController.navigate(route = Screen.Details.passId(id = list[it].id ?: 0))
                    }
            )
        }
    }
}