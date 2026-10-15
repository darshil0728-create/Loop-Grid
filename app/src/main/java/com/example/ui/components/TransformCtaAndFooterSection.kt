package com.example.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.CrispWhite
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.LightBorder
import com.example.ui.theme.LightBorderStrong
import com.example.ui.theme.LightEmerald
import com.example.ui.theme.LightEmeraldDark
import com.example.ui.theme.LightSurfaceCard
import com.example.ui.theme.LightTextBody
import com.example.ui.theme.LightTextHeadline
import com.example.ui.theme.LightTextMuted
import com.example.ui.theme.PlusJakartaSans
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate950

private const val GITHUB_URL = "https://github.com/darshil0728-create/Loop-Grid"
private const val LINKEDIN_URL = "https://www.linkedin.com/in/darshil-jain-593754427?utm_source=share_via&utm_content=profile&utm_medium=member_android"

/**
 * Final Section matching IMG_20261009_074034.jpg:
 * - Center headline 1 (white): "Don't just scrap it ,"
 * - Center headline 2 (green): "Transform it ."
 * - Subtitle: "Register your resources . Start the transformation . Enjoy the better future"
 * - Button 1: "REGISTER A FEED -->" (Green)
 * - Button 2: "EXPLORE WHAT WE DO"
 * - Footer:
 *   - Extreme left: "© LOOPGRID"
 *   - Centre: "DESIGNED AND DEVELOPED BY DARSHIL JAIN"
 *   - Extreme right: "LinkedIn" and "GitHub" buttons
 */
@Composable
fun TransformCtaAndFooterSection(
    isDarkTheme: Boolean,
    onRegisterFeedClick: () -> Unit,
    onExploreWhatWeDoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current

    fun openExternalLink(url: String) {
        try {
            uriHandler.openUri(url)
        } catch (_: Exception) {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            } catch (_: Exception) {
                // Graceful fallback
            }
        }
    }

    val darkSectionBgBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0F1A15),
            Color(0xFF0B1410),
            Color(0xFF070E0A)
        )
    )

    val lightSectionBgBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFF7FAF7),
            Color(0xFFF0F6F1),
            Color(0xFFE6EFE8)
        )
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(if (isDarkTheme) darkSectionBgBrush else lightSectionBgBrush)
            .testTag("transform_cta_section")
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Main CTA Content Area
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 72.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Line 1 in white: "Don't just scrap it ,"
                Text(
                    text = "Don't just scrap it ,",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 44.sp,
                    lineHeight = 52.sp,
                    letterSpacing = (-1.4).sp,
                    color = if (isDarkTheme) CrispWhite else LightTextHeadline,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.testTag("cta_headline_1")
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Line 2 in green: "Transform it ."
                Text(
                    text = "Transform it .",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 48.sp,
                    lineHeight = 56.sp,
                    letterSpacing = (-1.5).sp,
                    color = if (isDarkTheme) EmeraldLight else LightEmerald,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.testTag("cta_headline_2")
                )

                Spacer(modifier = Modifier.height(18.dp))

                // Subtitle: bold and readable
                Text(
                    text = "Register your resources . Start the transformation . Enjoy the better future",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    lineHeight = 25.sp,
                    color = if (isDarkTheme) CrispWhite else LightTextHeadline,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .widthIn(max = 620.dp)
                        .testTag("cta_subtitle")
                )

                Spacer(modifier = Modifier.height(34.dp))

                // Action Buttons: REGISTER A FEED --> and EXPLORE WHAT WE DO
                Row(
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Button 1: REGISTER A FEED -->
                    Button(
                        onClick = onRegisterFeedClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isDarkTheme) EmeraldPrimary else LightEmerald
                        ),
                        shape = RoundedCornerShape(999.dp),
                        modifier = Modifier
                            .shadow(
                                elevation = if (isDarkTheme) 10.dp else 4.dp,
                                shape = RoundedCornerShape(999.dp),
                                ambientColor = if (isDarkTheme) Color(0x4010B981) else Color(0x30059669),
                                spotColor = if (isDarkTheme) Color(0x6010B981) else Color(0x40059669)
                            )
                            .height(50.dp)
                            .testTag("cta_btn_register_feed")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(horizontal = 14.dp)
                        ) {
                            Text(
                                text = "REGISTER A FEED",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.5.sp,
                                color = if (isDarkTheme) Slate950 else CrispWhite,
                                letterSpacing = 0.4.sp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = "Arrow Forward",
                                tint = if (isDarkTheme) Slate950 else CrispWhite,
                                modifier = Modifier.size(17.dp)
                            )
                        }
                    }

                    // Button 2: EXPLORE WHAT WE DO
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(999.dp))
                            .border(
                                width = 1.dp,
                                color = if (isDarkTheme) Color(0x33FFFFFF) else Color(0x26000000),
                                shape = RoundedCornerShape(999.dp)
                            )
                            .background(
                                if (isDarkTheme) Color(0x1AFFFFFF) else LightSurfaceCard
                            )
                            .clickable { onExploreWhatWeDoClick() }
                            .padding(horizontal = 22.dp, vertical = 14.dp)
                            .testTag("cta_btn_explore_what_we_do"),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "EXPLORE WHAT WE DO",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = if (isDarkTheme) CrispWhite else LightTextHeadline,
                            letterSpacing = 0.3.sp
                        )
                    }
                }
            }

            // Horizontal Divider (separating CTA from Footer, subtle hairline)
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 0.75.dp,
                color = if (isDarkTheme) Color(0x1FFFFFFF) else Color(0x14000000)
            )

            // Bottom Footer
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 22.dp)
                    .testTag("loopgrid_footer")
            ) {
                val isWide = maxWidth >= 760.dp

                if (isWide) {
                    // Desktop / Wide layout: left, center, right
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Extreme Left: © LoopGrid
                        FooterBranding(isDarkTheme)

                        // Centre: Designed & Developed by Darshil Jain
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.testTag("footer_designed_by")
                        ) {
                            Text(
                                text = "Designed & Developed by ",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                                color = if (isDarkTheme) Color(0xFF94A3B8) else Color(0xFF64748B),
                                letterSpacing = 0.2.sp
                            )
                            Text(
                                text = "Darshil Jain",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp,
                                color = if (isDarkTheme) CrispWhite else LightTextHeadline,
                                letterSpacing = 0.2.sp
                            )
                        }

                        // Extreme Right: LinkedIn and GitHub buttons
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            SocialButton(
                                label = "LinkedIn",
                                iconRes = R.drawable.ic_linkedin,
                                isDarkTheme = isDarkTheme,
                                isLinkedIn = true,
                                onClick = { openExternalLink(LINKEDIN_URL) },
                                testTag = "footer_btn_linkedin"
                            )

                            SocialButton(
                                label = "GitHub",
                                iconRes = R.drawable.ic_github,
                                isDarkTheme = isDarkTheme,
                                isLinkedIn = false,
                                onClick = { openExternalLink(GITHUB_URL) },
                                testTag = "footer_btn_github"
                            )
                        }
                    }
                } else {
                    // Mobile Layout: Stacks cleanly
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Left: © LoopGrid
                            FooterBranding(isDarkTheme)

                            // Right: Social buttons
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                SocialButton(
                                    label = "LinkedIn",
                                    iconRes = R.drawable.ic_linkedin,
                                    isDarkTheme = isDarkTheme,
                                    isLinkedIn = true,
                                    onClick = { openExternalLink(LINKEDIN_URL) },
                                    testTag = "footer_btn_linkedin"
                                )

                                SocialButton(
                                    label = "GitHub",
                                    iconRes = R.drawable.ic_github,
                                    isDarkTheme = isDarkTheme,
                                    isLinkedIn = false,
                                    onClick = { openExternalLink(GITHUB_URL) },
                                    testTag = "footer_btn_github"
                                )
                            }
                        }

                        // Centre: Designed & Developed by Darshil Jain
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.testTag("footer_designed_by_mobile")
                        ) {
                            Text(
                                text = "Designed & Developed by ",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Normal,
                                fontSize = 11.sp,
                                color = if (isDarkTheme) Color(0xFF94A3B8) else Color(0xFF64748B),
                                letterSpacing = 0.2.sp
                            )
                            Text(
                                text = "Darshil Jain",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 11.sp,
                                color = if (isDarkTheme) CrispWhite else LightTextHeadline,
                                letterSpacing = 0.2.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FooterBranding(isDarkTheme: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(if (isDarkTheme) Color(0x1F10B981) else Color(0x14059669)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Eco,
                contentDescription = null,
                tint = if (isDarkTheme) EmeraldLight else LightEmerald,
                modifier = Modifier.size(12.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "© 2026 LoopGrid",
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            color = if (isDarkTheme) Color(0xFFCBD5E1) else LightTextHeadline,
            letterSpacing = 0.2.sp
        )
    }
}

@Composable
private fun SocialButton(
    label: String,
    iconRes: Int,
    isDarkTheme: Boolean,
    isLinkedIn: Boolean,
    onClick: () -> Unit,
    testTag: String
) {
    val bgModifier = if (isLinkedIn) {
        Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(if (isDarkTheme) Color(0x1A10B981) else Color(0x14059669))
            .border(0.75.dp, if (isDarkTheme) Color(0x4D10B981) else Color(0x33059669), RoundedCornerShape(999.dp))
    } else {
        Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(if (isDarkTheme) Color(0x1AFFFFFF) else Color(0x0A000000))
            .border(0.75.dp, if (isDarkTheme) Color(0x26FFFFFF) else Color(0x1A000000), RoundedCornerShape(999.dp))
    }

    Box(
        modifier = bgModifier
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 7.dp)
            .testTag(testTag),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                tint = if (isLinkedIn) {
                    if (isDarkTheme) EmeraldLight else LightEmeraldDark
                } else {
                    if (isDarkTheme) CrispWhite else LightTextHeadline
                },
                modifier = Modifier.size(13.dp)
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = label,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Medium,
                fontSize = 11.5.sp,
                color = if (isLinkedIn) {
                    if (isDarkTheme) EmeraldLight else LightEmeraldDark
                } else {
                    if (isDarkTheme) CrispWhite else LightTextHeadline
                },
                letterSpacing = 0.2.sp
            )
        }
    }
}
