package com.example.movopfy.uiComponents.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

data class AppTypography(
    val textMedium: TextStyle,
    val textNormal: TextStyle,
    val textLarge: TextStyle
)

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        textMedium = TextStyle.Default,
        textNormal = TextStyle.Default,
        textLarge = TextStyle.Default
    )
}