package com.example.movopfy.features.home.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.movopfy.R
import com.example.movopfy.uiComponents.theme.Dimensions
import com.example.movopfy.uiComponents.theme.TextMain

@Composable
fun SchedulesHeader(dimensions: Dimensions) {
    Text(
        text = stringResource(R.string.schedule_for_today_text),
        color = TextMain,
        fontSize = dimensions.textSizeMain,
        fontFamily = FontFamily(Font(R.font.inter_bold)),
        modifier = Modifier.padding(
            start = dimensions.paddingStart,
            top = 100.dp
        )
    )
}