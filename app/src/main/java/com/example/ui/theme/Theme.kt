package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = EmeraldPrimary,
    onPrimary = Slate950,
    primaryContainer = EmeraldDark,
    onPrimaryContainer = CrispWhite,
    secondary = ElectricPurple,
    onSecondary = CrispWhite,
    secondaryContainer = Color(0xFF4C1D95),
    onSecondaryContainer = ElectricPurpleLight,
    tertiary = AmberOrange,
    background = DarkBgBase,
    onBackground = CrispWhite,
    surface = DarkSurfaceCard,
    onSurface = CrispWhite,
    surfaceVariant = Slate800,
    onSurfaceVariant = Slate300,
    outline = Slate700
)

private val LightColorScheme = lightColorScheme(
    primary = LightEmerald,
    onPrimary = CrispWhite,
    primaryContainer = Color(0xFFA7F3D0),
    onPrimaryContainer = Color(0xFF064E3B),
    secondary = LightPurple,
    onSecondary = CrispWhite,
    secondaryContainer = Color(0xFFDDD6FE),
    onSecondaryContainer = Color(0xFF4C1D95),
    tertiary = LightOrange,
    background = LightBgBase,
    onBackground = LightTextHeadline,
    surface = LightSurfaceCard,
    onSurface = LightTextHeadline,
    surfaceVariant = Color(0xFFF1F5F9),
    onSurfaceVariant = LightTextBody,
    outline = LightBorder
)

@Composable
fun LoopGridTheme(
    darkTheme: Boolean = true, // Default to dark theme as explicitly requested
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
