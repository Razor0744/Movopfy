package com.example.movopfy.uiComponents.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp

data class AppSize(
   val height: Dp
)

val LocalAppSize = staticCompositionLocalOf {
    AppSize(
        height = Dp.Unspecified
    )
}