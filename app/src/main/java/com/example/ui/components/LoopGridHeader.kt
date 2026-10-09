package com.example.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CrispWhite
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.LightBorder
import com.example.ui.theme.LightBorderStrong
import com.example.ui.theme.LightEmerald
import com.example.ui.theme.LightSurfaceCard
import com.example.ui.theme.LightTextBody
import com.example.ui.theme.LightTextHeadline
import com.example.ui.theme.PlusJakartaSans
import com.example.ui.theme.Slate300
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate950

@Composable
fun LoopGridHeader(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    onExploreDemoClick: () -> Unit,
    onLogInClick: () -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Far Left: LoopGrid Branding (Logo icon removed as explicitly requested)
        Text(
            text = "LoopGrid",
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp,
            color = if (isDarkTheme) CrispWhite else LightTextHeadline,
            letterSpacing = (-0.6).sp,
            modifier = Modifier
                .testTag("brand_title")
                .clickable { /* Reset or Home */ }
        )

        // Far Right: Navigation Actions & Theme Toggle
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .testTag("nav_items_row")
        ) {
            // Text "Explore Demo Account" button
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onExploreDemoClick() }
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .testTag("nav_explore_demo_account"),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Explore Demo Account",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = if (isDarkTheme) CrispWhite else LightTextHeadline
                )
            }

            // Separate "Login" button
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .border(
                        width = 1.dp,
                        color = if (isDarkTheme) Color(0x40334155) else LightBorderStrong,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(
                        if (isDarkTheme) Color(0x220F172A) else LightSurfaceCard
                    )
                    .clickable { onLogInClick() }
                    .padding(horizontal = 14.dp, vertical = 8.dp)
                    .testTag("nav_login_button"),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Login",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = if (isDarkTheme) CrispWhite else LightTextHeadline
                )
            }

            // Separate "Sign Up" button (prominent emerald style)
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 6.dp,
                        shape = RoundedCornerShape(8.dp),
                        ambientColor = if (isDarkTheme) Color(0x5510B981) else Color(0x35059669),
                        spotColor = if (isDarkTheme) Color(0x7710B981) else Color(0x50059669)
                    )
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (isDarkTheme) EmeraldPrimary else LightEmerald)
                    .clickable { onSignUpClick() }
                    .padding(horizontal = 14.dp, vertical = 8.dp)
                    .testTag("nav_signup_button"),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Sign Up",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 13.sp,
                    color = if (isDarkTheme) Slate950 else CrispWhite
                )
            }

            // Dark/Light Theme Switcher: Prominent sun/moon icon toggle
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(
                        width = 1.dp,
                        color = if (isDarkTheme) Slate700.copy(alpha = 0.6f) else LightBorder,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(if (isDarkTheme) Color(0x400F172A) else LightSurfaceCard)
                    .clickable { onToggleTheme() }
                    .testTag("theme_switcher_btn"),
                contentAlignment = Alignment.Center
            ) {
                AnimatedContent(
                    targetState = isDarkTheme,
                    transitionSpec = { fadeIn(tween(200)) togetherWith fadeOut(tween(200)) },
                    label = "theme_icon"
                ) { dark ->
                    if (dark) {
                        Icon(
                            imageVector = Icons.Default.LightMode,
                            contentDescription = "Switch to Light Theme",
                            tint = Color(0xFFFBBF24), // Amber Sun
                            modifier = Modifier.size(18.dp)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.DarkMode,
                            contentDescription = "Switch to Dark Theme",
                            tint = Color(0xFF7C3AED), // Violet Moon
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}
