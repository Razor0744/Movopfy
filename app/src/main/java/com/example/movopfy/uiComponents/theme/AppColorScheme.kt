package com.example.movopfy.uiComponents.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class AppColorScheme(
    val brandPrimary: Color,
    val brandPrimaryHover: Color,
    val inputBackground: Color,
    val inputActive: Color,
    val textWhite: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val background: Color,
    val click: Color,
    val substrate: Color,
    val brandBorder: Color,
    val backgroundNavBar: Color
)

val LocalAppColorScheme = staticCompositionLocalOf {
    AppColorScheme(
        brandPrimary = Color.Unspecified,
        brandPrimaryHover = Color.Unspecified,
        inputBackground = Color.Unspecified,
        inputActive = Color.Unspecified,
        textWhite = Color.Unspecified,
        textPrimary = Color.Unspecified,
        textSecondary = Color.Unspecified,
        background = Color.Unspecified,
        click = Color.Unspecified,
        substrate = Color.Unspecified,
        brandBorder = Color.Unspecified,
        backgroundNavBar = Color.Unspecified
    )
}