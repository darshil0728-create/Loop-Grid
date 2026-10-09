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
 *   - Centre: "DESIGNED AND DEVELOPED BY DARSHIL JAIN & SARVESH KULKARNI"
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
            Color(0xFF070B0E),
            Color(0xFF05080A),
            Color(0xFF030507)
        )
    )

    val lightSectionBgBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFF8FAFC),
            Color(0xFFF1F5F9),
            Color(0xFFE2E8F0)
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
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .shadow(
                                elevation = 14.dp,
                                shape = RoundedCornerShape(12.dp),
                                ambientColor = if (isDarkTheme) Color(0x6610B981) else Color(0x40059669),
                                spotColor = if (isDarkTheme) Color(0x8010B981) else Color(0x60059669)
                            )
                            .height(52.dp)
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
                                fontSize = 14.sp,
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
                            .testTag("cta_btn_explore_what_we_do"),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "EXPLORE WHAT WE DO",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = if (isDarkTheme) CrispWhite else LightTextHeadline,
                            letterSpacing = 0.3.sp
                        )
                    }
                }
            }

            // Horizontal Divider (separating CTA from Footer, matching reference image)
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = if (isDarkTheme) Color(0x2E334155) else Color(0xFFCBD5E1)
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
                        // Extreme Left: © LOOPGRID
                        FooterBranding(isDarkTheme)

                        // Centre: DESIGNED AND DEVELOPED BY DARSHIL JAIN & SARVESH KULKARNI
                        Text(
                            text = "DESIGNED AND DEVELOPED BY DARSHIL JAIN & SARVESH KULKARNI",
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            color = if (isDarkTheme) CrispWhite else LightTextHeadline,
                            letterSpacing = 0.5.sp,
                            textAlign = TextAlign.Center
                        )

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
                            // Left: © LOOPGRID
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

                        // Centre: DESIGNED AND DEVELOPED BY DARSHIL JAIN & SARVESH KULKARNI
                        Text(
                            text = "DESIGNED AND DEVELOPED BY DARSHIL JAIN & SARVESH KULKARNI",
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            color = if (isDarkTheme) CrispWhite else LightTextHeadline,
                            letterSpacing = 0.5.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FooterBranding(isDarkTheme: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.Eco,
            contentDescription = null,
            tint = if (isDarkTheme) EmeraldLight else LightEmerald,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "© 2026 LOOPGRID",
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = if (isDarkTheme) CrispWhite else LightTextHeadline,
            letterSpacing = 0.5.sp
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
            .clip(RoundedCornerShape(8.dp))
            .background(if (isDarkTheme) Color(0xFF063326) else Color(0xFFE6F4EA))
            .border(1.dp, if (isDarkTheme) Color(0x6610B981) else Color(0x99059669), RoundedCornerShape(8.dp))
    } else {
        Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isDarkTheme) Color(0xFF161B22) else Color(0xFFF1F5F9))
            .border(1.dp, if (isDarkTheme) Color(0x33475569) else Color(0xFFCBD5E1), RoundedCornerShape(8.dp))
    }

    Box(
        modifier = bgModifier
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 7.dp)
            .testTag(testTag),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                tint = if (isLinkedIn) {
                    if (isDarkTheme) EmeraldLight else LightEmerald
                } else {
                    if (isDarkTheme) CrispWhite else LightTextHeadline
                },
                modifier = Modifier.size(15.dp)
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = label,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = if (isLinkedIn) {
                    if (isDarkTheme) EmeraldLight else LightEmerald
                } else {
                    if (isDarkTheme) CrispWhite else LightTextHeadline
                }
            )
        }
    }
}
