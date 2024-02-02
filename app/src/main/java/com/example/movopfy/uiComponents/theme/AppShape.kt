package com.example.movopfy.uiComponents.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape

data class AppShape(
    val image: Shape,
    val button: Shape
)

val LocalAppShape = staticCompositionLocalOf {
    AppShape(
        image = RectangleShape,
        button = RectangleShape
    )
}