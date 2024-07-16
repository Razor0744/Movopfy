package com.example.uiComponents.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.uiComponents.R
import com.example.uiComponents.theme.Yellow

@Composable
fun FavouriteIcon(
    isFavorite: Boolean,
    onIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (!isFavorite) {
        Icon(
            modifier = modifier
                .clickable { onIconClick() },
            painter = painterResource(id = R.drawable.ic_star),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary
        )
    } else {
        Icon(
            modifier = modifier
                .clickable { onIconClick() },
            imageVector = Icons.Outlined.Star,
            contentDescription = null,
            tint = Yellow
        )
    }
}