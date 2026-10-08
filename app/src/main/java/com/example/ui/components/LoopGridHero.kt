package com.example.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CrispWhite
import com.example.ui.theme.DarkBorderHighlight
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.LightBorder
import com.example.ui.theme.LightBorderStrong
import com.example.ui.theme.LightEmerald
import com.example.ui.theme.LightEmeraldDark
import com.example.ui.theme.LightSurfaceCard
import com.example.ui.theme.LightTextBody
import com.example.ui.theme.LightTextHeadline
import com.example.ui.theme.PlusJakartaSans
import com.example.ui.theme.Slate100
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate300
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate950

@Composable
fun LoopGridHero(
    isDarkTheme: Boolean,
    onRegisterFeedClick: () -> Unit,
    onExploreWhatWeDoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val dotAlpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "dotAlpha"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 3. Badge: Pill-shaped badge with sharp borders
        Box(
            modifier = Modifier
                .shadow(
                    elevation = if (isDarkTheme) 0.dp else 4.dp,
                    shape = RoundedCornerShape(50.dp),
                    ambientColor = Color(0x100F172A),
                    spotColor = Color(0x150F172A)
                )
                .clip(RoundedCornerShape(50.dp))
                .border(
                    width = 1.dp,
                    color = if (isDarkTheme) DarkBorderHighlight else Color(0x55059669),
                    shape = RoundedCornerShape(50.dp)
                )
                .background(
                    if (isDarkTheme) Color(0xE60F172A) else Color(0xF2FFFFFF)
                )
                .padding(horizontal = 16.dp, vertical = 7.dp)
                .testTag("network_badge"),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // Pulsating indicator dot
                Box(
                    modifier = Modifier
                        .size(7.dp)
                        .clip(CircleShape)
                        .background(
                            (if (isDarkTheme) EmeraldLight else LightEmerald).copy(alpha = dotAlpha)
                        )
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "LOOPGRID CIRCULAR NETWORK",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = if (isDarkTheme) EmeraldLight else LightEmeraldDark,
                    letterSpacing = 0.8.sp
                )

                Spacer(modifier = Modifier.width(6.dp))

                Box(
                    modifier = Modifier
                        .size(3.dp)
                        .clip(CircleShape)
                        .background(if (isDarkTheme) Color(0x99FFFFFF) else Slate700)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "LIVE DATABASE CONNECTED",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = if (isDarkTheme) Color(0xE6FFFFFF) else LightTextHeadline,
                    letterSpacing = 0.8.sp
                )

                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .size(7.dp)
                        .clip(CircleShape)
                        .background(
                            (if (isDarkTheme) EmeraldLight else LightEmerald).copy(alpha = dotAlpha)
                        )
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // 4. Hero Typography:
        // Headline 1: Massive, bold font-extrabold. "TURN EVERY RESOURCE"
        Text(
            text = "TURN EVERY RESOURCE",
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 40.sp,
            lineHeight = 46.sp,
            letterSpacing = (-1.2).sp,
            textAlign = TextAlign.Center,
            color = if (isDarkTheme) CrispWhite else LightTextHeadline,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("hero_headline_1")
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Headline 2: "INTO VALUE" in brilliant, vibrant emerald-green
        Text(
            text = "INTO VALUE",
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 48.sp,
            lineHeight = 54.sp,
            letterSpacing = (-1.5).sp,
            textAlign = TextAlign.Center,
            style = TextStyle(
                color = if (isDarkTheme) EmeraldLight else LightEmerald,
                shadow = if (isDarkTheme) Shadow(
                    color = Color(0x8010B981),
                    offset = Offset(0f, 4f),
                    blurRadius = 18f
                ) else Shadow(
                    color = Color(0x33059669),
                    offset = Offset(0f, 2f),
                    blurRadius = 8f
                )
            ),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("hero_headline_2")
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Subtitle: "LoopGrid helps businesses turn their leftover materials..."
        Text(
            text = "LoopGrid helps businesses turn their leftover materials into value by connecting them with the right buyers, logistics, and reuse opportunities.",
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            lineHeight = 25.sp,
            textAlign = TextAlign.Center,
            color = if (isDarkTheme) Slate100 else LightTextBody,
            modifier = Modifier
                .widthIn(max = 620.dp)
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .testTag("hero_subtitle")
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Action Buttons:
        // Button 1: "REGISTER A FEED →"
        // Button 2: "EXPLORE WHAT WE DO"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("hero_cta_buttons"),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Button 1: REGISTER A FEED
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 12.dp,
                        shape = RoundedCornerShape(12.dp),
                        ambientColor = if (isDarkTheme) Color(0x4D10B981) else Color(0x33059669),
                        spotColor = if (isDarkTheme) Color(0x6610B981) else Color(0x40059669)
                    )
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isDarkTheme) EmeraldPrimary else LightEmerald)
                    .clickable { onRegisterFeedClick() }
                    .padding(horizontal = 22.dp, vertical = 14.dp)
                    .testTag("btn_register_feed"),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "REGISTER A FEED",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = if (isDarkTheme) Slate950 else CrispWhite,
                        letterSpacing = 0.5.sp
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Arrow Forward",
                        tint = if (isDarkTheme) Slate950 else CrispWhite,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(14.dp))

            // Button 2: EXPLORE WHAT WE DO
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = if (isDarkTheme) 0.dp else 4.dp,
                        shape = RoundedCornerShape(12.dp),
                        ambientColor = Color(0x100F172A),
                        spotColor = Color(0x150F172A)
                    )
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        width = 1.dp,
                        color = if (isDarkTheme) Slate700 else LightBorderStrong,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(
                        if (isDarkTheme) Color(0x990F172A) else LightSurfaceCard
                    )
                    .clickable { onExploreWhatWeDoClick() }
                    .padding(horizontal = 20.dp, vertical = 14.dp)
                    .testTag("btn_explore_what_we_do"),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "EXPLORE WHAT WE DO",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = if (isDarkTheme) CrispWhite else LightTextHeadline,
                    letterSpacing = 0.3.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(26.dp))

        // Trust Badge: Social proof directly underneath buttons
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .testTag("trust_badge_row")
                .padding(vertical = 4.dp)
        ) {
            // 3 Overlapping circular profile avatars
            Row(
                modifier = Modifier.padding(end = 10.dp)
            ) {
                AvatarCircle(
                    text = "AK",
                    bgBrush = Brush.linearGradient(listOf(Color(0xFF047857), Color(0xFF10B981))),
                    borderColor = if (isDarkTheme) Color(0xFF0B0F19) else Color.White,
                    modifier = Modifier.size(28.dp)
                )
                AvatarCircle(
                    text = "MR",
                    bgBrush = Brush.linearGradient(listOf(Color(0xFFEA580C), Color(0xFFFB923C))),
                    borderColor = if (isDarkTheme) Color(0xFF0B0F19) else Color.White,
                    modifier = Modifier
                        .offset(x = (-8).dp)
                        .size(28.dp)
                )
                AvatarCircle(
                    text = "LG",
                    bgBrush = Brush.linearGradient(listOf(Color(0xFF6D28D9), Color(0xFF8B5CF6))),
                    borderColor = if (isDarkTheme) Color(0xFF0B0F19) else Color.White,
                    modifier = Modifier
                        .offset(x = (-16).dp)
                        .size(28.dp)
                )
            }

            Text(
                text = "2,713+ enterprises have already upgraded their profits",
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp,
                color = if (isDarkTheme) Slate200 else LightTextBody,
                modifier = Modifier.offset(x = (-8).dp)
            )

            Spacer(modifier = Modifier.width(6.dp))

            // LIVE DATA Pill Badge
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(if (isDarkTheme) Slate800 else Color(0xFFE2E8F0))
                    .border(
                        width = 1.dp,
                        color = if (isDarkTheme) Color(0x33334155) else LightBorderStrong,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(horizontal = 7.dp, vertical = 3.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(if (isDarkTheme) EmeraldLight else LightEmerald)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "LIVE DATA",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        color = if (isDarkTheme) Slate300 else LightTextBody,
                        letterSpacing = 0.5.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun AvatarCircle(
    text: String,
    bgBrush: Brush,
    borderColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .border(2.dp, borderColor, CircleShape)
            .background(bgBrush),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            color = CrispWhite
        )
    }
}
