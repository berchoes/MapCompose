package com.example.mapcompose.util.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ColorPalette = darkColors(
    primary = Color.Blue,
    primaryVariant = Color.Blue,
    secondary = Teal200,
    background = Color.White
)

@Composable
fun MapComposeTheme(content: @Composable() () -> Unit) {
    val colors = ColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}