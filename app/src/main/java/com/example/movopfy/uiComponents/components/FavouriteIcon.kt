package com.example.movopfy.uiComponents.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.movopfy.R
import com.example.movopfy.uiComponents.theme.Yellow

@Composable
fun FavouriteIcon(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    onIconClick: () -> Unit
) {
    if (!isFavorite) {
        Icon(
            modifier = modifier
                .clickable { onIconClick() },
            painter = painterResource(id = R.drawable.ic_star),
            contentDescription = "is_favorite_false",
            tint = MaterialTheme.colorScheme.onPrimary
        )
    } else {
        Icon(
            modifier = modifier
                .clickable { onIconClick() },
            imageVector = Icons.Outlined.Star,
            contentDescription = "is_favorite_true",
            tint = Yellow
        )
    }
}