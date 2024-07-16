package com.example.uiComponents.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.uiComponents.R
import com.example.uiComponents.theme.dimensions

@Composable
fun TopBar(isBackEnabled: Boolean, navController: NavController) {
    Row(
        modifier = Modifier
            .height(height = 52.dp)
            .fillMaxWidth()
            .padding(
                start = MaterialTheme.dimensions.paddingStart,
                top = MaterialTheme.dimensions.paddingTop
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isBackEnabled) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = null,
                modifier = Modifier
                    .height(35.dp)
                    .width(35.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }) {
                        navController.popBackStack()
                    },
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 52.dp)
        ) {
            Icon(
                modifier = Modifier
                    .height(45.dp)
                    .width(45.dp)
                    .padding(end = 12.dp)
                    .clickable {
                        navController.navigate(Screen.Search.route)
                    },
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}