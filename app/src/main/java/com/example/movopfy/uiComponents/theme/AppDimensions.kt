package com.example.movopfy.uiComponents.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp

data class AppDimensions(
    val paddingStart: Dp,
    val paddingEnd: Dp,
    val lazySpace: Dp,
    val height: Dp
)

val LocalAppDimensions = staticCompositionLocalOf {
    AppDimensions(
        paddingStart = Dp.Unspecified,
        paddingEnd = Dp.Unspecified,
        lazySpace = Dp.Unspecified,
        height = Dp.Unspecified
    )
}