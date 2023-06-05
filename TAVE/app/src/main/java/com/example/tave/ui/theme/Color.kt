package com.example.tave.ui.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)
val LightBlue = Color(0xFF82CBFF)
val MidBlue = Color(0xFF1C73B4)
val DarkBlue = Color(0xFF0061A8)

val LightColorPalette = lightColorScheme(
    primary = DarkBlue,
    onPrimary = White,
    primaryContainer = MidBlue,
    onPrimaryContainer = White,
    secondary = LightBlue,
    onSecondary = White,
    secondaryContainer = White,
    onSecondaryContainer = Black
)

val DarkColorPalette = lightColorScheme(
    primary = DarkBlue,
    onPrimary = White,
    primaryContainer = MidBlue,
    onPrimaryContainer = White,
    secondary = LightBlue,
    onSecondary = White,
    secondaryContainer = Black,
    onSecondaryContainer = White
)