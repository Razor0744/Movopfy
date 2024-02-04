package com.example.movopfy.uiComponents.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val darkColorScheme = AppColorScheme(
    brandPrimary = Blue,
    brandPrimaryHover = Blue_300,
    inputBackground = Neutral_300,
    inputActive = Neutral_400,
    textWhite = Neutral_0,
    textPrimary = Neutral_900,
    textSecondary = Neutral_700,
    background = Neutral_100,
    click = Neutral_200,
    substrate = Neutral_0,
    brandBorder = Neutral_350,
    backgroundNavBar = Neutral_350
)

private val lightColorScheme = AppColorScheme(
    brandPrimary = Blue,
    brandPrimaryHover = Blue_300,
    inputBackground = Neutral_300,
    inputActive = Neutral_400,
    textWhite = Neutral_0,
    textPrimary = Neutral_900,
    textSecondary = Neutral_700,
    background = Neutral_100,
    click = Neutral_200,
    substrate = Neutral_0,
    brandBorder = Neutral_350,
    backgroundNavBar = Neutral_350
)

private val typography = AppTypography(
    textMedium = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ), textLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 26.sp
    ),
    textNormal = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)

private val shape = AppShape(
    image = RoundedCornerShape(15.dp),
    button = RoundedCornerShape(30.dp)
)

private val size = AppSize(
    large = 26.dp,
    medium = 16.dp,
    normal = 14.dp,
    small = 8.dp
)

private val dimension = AppDimensions(
    paddingStart = 16.dp,
    paddingEnd = 16.dp,
    lazySpace = 20.dp,
    height = 52.dp
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) darkColorScheme else lightColorScheme
    CompositionLocalProvider(
        LocalAppColorScheme provides colorScheme,
        LocalAppShape provides shape,
        LocalAppSize provides size,
        LocalAppTypography provides typography,
        LocalAppDimensions provides dimension,
        content = content
    )
}

object AppTheme {

    val colorScheme: AppColorScheme
        @Composable get() = LocalAppColorScheme.current

    val typography: AppTypography
        @Composable get() = LocalAppTypography.current

    val shape: AppShape
        @Composable get() = LocalAppShape.current

    val size: AppSize
        @Composable get() = LocalAppSize.current

    val dimensions: AppDimensions
        @Composable get() = LocalAppDimensions.current
}