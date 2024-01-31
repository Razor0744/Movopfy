package com.example.movopfy.features.title.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.movopfy.R
import com.example.movopfy.uiComponents.theme.Dimensions
import com.example.movopfy.uiComponents.theme.TextMain

@Composable
fun NameOfTitle(dimensions: Dimensions, name: String?) {
    Text(
        modifier = Modifier.padding(start = dimensions.paddingStart),
        text = name.toString(),
        fontFamily = FontFamily(Font(R.font.inter_extrabold)),
        fontSize = dimensions.textSizeMain,
        color = TextMain
    )
}