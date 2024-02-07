package com.example.movopfy.uiComponents.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Size(
    val height: Dp
)

val LocalAppSize = staticCompositionLocalOf {
    Size(
        height = 52.dp
    )
}