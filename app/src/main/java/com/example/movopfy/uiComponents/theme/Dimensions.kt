package com.example.movopfy.uiComponents.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val LocalDim = compositionLocalOf { Dimensions() }

data class Dimensions(
    val textSizeSecondary: TextUnit = 20.sp,
    val textSizeMain: TextUnit = 28.sp,

    val paddingStart: Dp = 10.dp,
    val paddingEnd: Dp = 10.dp,
    val paddingTopMain: Dp = 100.dp,

    val radius: Dp = 15.dp
)