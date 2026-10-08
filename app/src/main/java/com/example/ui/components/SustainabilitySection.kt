package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CrispWhite
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.LightEmerald
import com.example.ui.theme.LightEmeraldDark
import com.example.ui.theme.LightSurfaceCard
import com.example.ui.theme.LightTextBody
import com.example.ui.theme.LightTextHeadline
import com.example.ui.theme.PlusJakartaSans
import com.example.ui.theme.Slate300
import com.example.ui.theme.Slate950

/**
 * Sustainability Section strictly matching the reference image layout:
 * - Top pill badge
 * - Main heading 1 in white: "MAKING SUSTAINABILITY WORK FOR MSMEs"
 * - Smaller heading 2 in green: "Making Every Resource Work Harder"
 * - Detailed description paragraph
 * - 4 bullet points with circular green checkmark icons
 * - Emerald green button: "Explore How LoopGrid Works →"
 */
@Composable
fun SustainabilitySection(
    isDarkTheme: Boolean,
    onExploreHowLoopGridWorksClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val darkSectionBgBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF060F0B), // Very dark slate with green tint
            Color(0xFF09140E),
            Color(0xFF050C08)
        )
    )

    val lightSectionBgBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFF0FDF4),
            Color(0xFFF8FAFC)
        )
    )

    val bulletPoints = listOf(
        "Turn unused resources into revenue opportunities",
        "Aggregate fragmented resources for better value",
        "Find the right buyers, processors and mobility partners",
        "Track every resource through a Digital Resource Passport"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(if (isDarkTheme) darkSectionBgBrush else lightSectionBgBrush)
            .padding(horizontal = 24.dp, vertical = 56.dp)
            .testTag("sustainability_section"),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 760.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            // Pill badge (matching reference image top pill)
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .border(
                        width = 1.dp,
                        color = if (isDarkTheme) Color(0x5510B981) else Color(0x66059669),
                        shape = RoundedCornerShape(50.dp)
                    )
                    .background(
                        if (isDarkTheme) Color(0x33064E3B) else Color(0x2610B981)
                    )
                    .padding(horizontal = 14.dp, vertical = 6.dp)
                    .testTag("sustainability_pill")
            ) {
                Text(
                    text = "MAKING SUSTAINABILITY WORK FOR MSMEs",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = if (isDarkTheme) EmeraldLight else LightEmeraldDark,
                    letterSpacing = 1.0.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Main Heading in white: "MAKING SUSTAINABILITY WORK FOR MSMEs"
            Text(
                text = "MAKING SUSTAINABILITY WORK FOR MSMEs",
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp,
                lineHeight = 38.sp,
                letterSpacing = (-0.8).sp,
                color = if (isDarkTheme) CrispWhite else LightTextHeadline,
                modifier = Modifier.testTag("sustainability_main_heading")
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Smaller Heading in green: "Making Every Resource Work Harder"
            Text(
                text = "Making Every Resource\nWork Harder",
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                lineHeight = 34.sp,
                letterSpacing = (-0.6).sp,
                color = if (isDarkTheme) EmeraldLight else LightEmerald,
                modifier = Modifier.testTag("sustainability_sub_heading")
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Detailed description paragraph
            Text(
                text = "LoopGrid helps MSMEs turn unused resources into new value by identifying, aggregating and connecting them to the right industrial opportunities — making sustainability practical, profitable and traceable.",
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                lineHeight = 24.sp,
                color = if (isDarkTheme) Color(0xCCF1F5F9) else LightTextBody,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("sustainability_description")
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Bullet points with circular green checkmark icons
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                bulletPoints.forEachIndexed { index, point ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("sustainability_point_$index"),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Check",
                            tint = if (isDarkTheme) EmeraldLight else LightEmerald,
                            modifier = Modifier.size(20.dp)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = point,
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            color = if (isDarkTheme) CrispWhite else LightTextHeadline
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(34.dp))

            // Green Button: "Explore How LoopGrid Works →"
            Button(
                onClick = onExploreHowLoopGridWorksClick,
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
                    .testTag("btn_explore_how_loopgrid_works")
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(horizontal = 14.dp)
                ) {
                    Text(
                        text = "Explore How LoopGrid Works",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = if (isDarkTheme) Slate950 else CrispWhite,
                        letterSpacing = 0.3.sp
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
        }
    }
}
