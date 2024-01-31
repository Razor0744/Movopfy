package com.example.movopfy.features.details.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movopfy.R
import com.example.movopfy.uiComponents.theme.Dimensions
import com.example.movopfy.uiComponents.theme.TextMain

@Composable
fun DescriptionText(dimensions: Dimensions, description: String?) {
    Text(
        text = description.toString(),
        modifier = Modifier.padding(start = dimensions.paddingStart, top = 20.dp),
        fontFamily = FontFamily(Font(R.font.inter_bold)),
        fontSize = 15.sp,
        color = TextMain
    )
}