package com.example.movopfy.features.favourite.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movopfy.R
import com.example.movopfy.database.models.favourite.Favourite
import com.example.movopfy.features.favourite.presentation.viewmodel.FavouriteViewModel
import com.example.movopfy.uiComponents.theme.dimensions

@Composable
fun FavouriteItem(
    modifier: Modifier = Modifier,
    item: Favourite,
    viewModel: FavouriteViewModel
) {
    Box(
        modifier = modifier
            .height(height = 52.dp)
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .padding(
                    paddingValues = PaddingValues(
                        start = MaterialTheme.dimensions.paddingStart
                    )
                )
                .width(250.dp),
            text = item.name,
            style = MaterialTheme.typography.labelSmall
        )

        Text(
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .padding(end = MaterialTheme.dimensions.paddingEnd)
                .clickable {
                    viewModel.removeFromFavorite(favourite = item)
                },
            text = stringResource(id = R.string.favourite_item_remove),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary
        )

        if (item.isWatched)
            Text(
                modifier = Modifier
                    .align(alignment = Alignment.BottomEnd)
                    .padding(end = MaterialTheme.dimensions.paddingEnd),
                text = stringResource(id = R.string.favourite_item_is_watched),
                style = MaterialTheme.typography.labelSmall
            )
    }
}