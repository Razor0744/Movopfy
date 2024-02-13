package com.example.movopfy.uiComponents.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Size(
    val heightComponent: Dp,
    val heightImage: Dp,
    val widthImage: Dp
)

val LocalAppSize = staticCompositionLocalOf {
    Size(
        heightComponent = 52.dp,
        heightImage = 180.dp,
        widthImage = 120.dp
    )
}