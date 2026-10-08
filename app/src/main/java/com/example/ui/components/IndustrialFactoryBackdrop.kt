package com.example.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.R
import com.example.ui.theme.DarkBgBase
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.LightBgBase

/**
 * Renders an authentic industrial/MSME factory background photo with:
 * - Real photographic factory shop floor (lathes, machines, structural bays)
 * - Desaturated and pushed far into the background
 * - Heavy dark vignette & high-opacity black/slate overlay (80%-95% opacity)
 * - Foreground coiled metal rings in lower-right as in the reference image
 * - High-contrast text legibility in both dark (#0B0F19) and enhanced light (#F8FAFC) modes.
 */
@Composable
fun IndustrialFactoryBackdrop(
    isDarkTheme: Boolean = true,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "ambient")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.06f,
        targetValue = 0.14f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseAlpha"
    )

    // Desaturation color matrix (grayscale)
    val grayscaleMatrix = ColorMatrix().apply {
        setToSaturation(0.05f) // Heavily desaturated
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(if (isDarkTheme) DarkBgBase else LightBgBase)
    ) {
        // 1. Industrial / MSME Factory Photograph Layer
        Image(
            painter = painterResource(id = R.drawable.img_msme_factory_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.colorMatrix(grayscaleMatrix),
            modifier = Modifier
                .fillMaxSize()
                .alpha(if (isDarkTheme) 0.38f else 0.16f)
        )

        // 2. High-Opacity Black/Slate Overlay + Heavy Vignette + MSME Coiled Rings (Canvas)
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            if (isDarkTheme) {
                // High-opacity black/slate overlay (80% - 92% opacity)
                drawRect(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xD90B0F19), // 85% opacity at top
                            Color(0xCC0B0F19), // 80% opacity in hero zone
                            Color(0xE60B0F19), // 90% opacity
                            Color(0xF80B0F19)  // 97% opacity at bottom
                        )
                    )
                )

                // Coiled rolled steel rings and MSME manufacturing details in lower-right
                val coilBaseX = w * 0.82f
                val coilBaseY = h * 0.68f

                for (i in 0..4) {
                    val coilY = coilBaseY + (i * 28f)
                    val coilWidth = w * 0.40f + (i * 14f)
                    val coilHeight = 46f

                    // Outer metallic rim
                    drawOval(
                        color = Color(0x35334155),
                        topLeft = Offset(coilBaseX - (coilWidth / 2f), coilY),
                        size = Size(coilWidth, coilHeight)
                    )
                    drawOval(
                        color = Color(0x4564748B),
                        topLeft = Offset(coilBaseX - (coilWidth / 2f), coilY),
                        size = Size(coilWidth, coilHeight),
                        style = Stroke(width = 3.5f)
                    )
                    // Inner hollow bore
                    drawOval(
                        color = Color(0x400B0F19),
                        topLeft = Offset(coilBaseX - (coilWidth * 0.34f), coilY + 6f),
                        size = Size(coilWidth * 0.68f, coilHeight * 0.65f)
                    )
                }

                // Heavy dark vignette: darkens aggressively around screen borders
                drawRect(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color(0x66020617),
                            Color(0xF0020617)
                        ),
                        center = Offset(w * 0.5f, h * 0.40f),
                        radius = w * 0.85f
                    )
                )

                // Ambient glowing emerald network node pulse
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            EmeraldPrimary.copy(alpha = pulseAlpha),
                            Color.Transparent
                        ),
                        center = Offset(w * 0.5f, h * 0.32f),
                        radius = w * 0.45f
                    ),
                    center = Offset(w * 0.5f, h * 0.32f),
                    radius = w * 0.45f
                )
            } else {
                // High-opacity Slate-50 overlay for clean, high-contrast light theme (~94% opacity)
                drawRect(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xF0F8FAFC), // 94% opacity
                            Color(0xEDF8FAFC), // 93% opacity
                            Color(0xF5F8FAFC), // 96% opacity
                            Color(0xFFF8FAFC)  // 100% opacity at bottom
                        )
                    )
                )

                // Very subtle soft ambient emerald glow at the top
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0x28A7F3D0), // Soft mint glow
                            Color.Transparent
                        ),
                        center = Offset(w * 0.5f, h * 0.22f),
                        radius = w * 0.55f
                    ),
                    center = Offset(w * 0.5f, h * 0.22f),
                    radius = w * 0.55f
                )

                // Subtle rolled coil lines in lower right for subtle texture
                val coilBaseX = w * 0.82f
                val coilBaseY = h * 0.68f
                for (i in 0..3) {
                    val coilY = coilBaseY + (i * 26f)
                    val coilWidth = w * 0.38f + (i * 12f)
                    val coilHeight = 42f
                    drawOval(
                        color = Color(0x1894A3B8),
                        topLeft = Offset(coilBaseX - (coilWidth / 2f), coilY),
                        size = Size(coilWidth, coilHeight),
                        style = Stroke(width = 2f)
                    )
                }
            }
        }
    }
}
