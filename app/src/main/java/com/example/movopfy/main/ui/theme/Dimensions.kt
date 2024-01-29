package com.example.movopfy.main.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val LocalDim = compositionLocalOf { Dimensions() }

data class Dimensions(
    val textSizeMain: TextUnit = 20.sp,

    val paddingStart: Dp = 20.dp
)
