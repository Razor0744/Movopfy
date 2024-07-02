package com.example.uiComponents.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val paddingStart: Dp,
    val paddingEnd: Dp,
    val paddingTop: Dp,
    val lazySpace: Dp
)

val LocalAppDimensions = staticCompositionLocalOf {
    Dimensions(
        paddingStart = 16.dp,
        paddingEnd = 16.dp,
        paddingTop = 8.dp,
        lazySpace = 10.dp
    )
}