package com.example.movopfy.uiComponents.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movopfy.R
import com.example.movopfy.uiComponents.theme.dimensions

@Composable
fun TopBar(arrow: Boolean, navController: NavController) {
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
        if (arrow) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = null,
                modifier = Modifier
                    .height(35.dp)
                    .width(35.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }) {
                        navController.popBackStack()
                    })
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_top_bar),
                contentDescription = null,
                modifier = Modifier
                    .shadow(
                        elevation = 30.dp,
                        shape = MaterialTheme.shapes.medium
                    )
            )
        }

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 52.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier
                    .height(45.dp)
                    .width(45.dp)
                    .padding(end = 12.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_accout),
                contentDescription = null,
                modifier = Modifier
                    .height(51.dp)
                    .width(51.dp)
                    .padding(end = MaterialTheme.dimensions.paddingEnd)
            )
        }
    }
}