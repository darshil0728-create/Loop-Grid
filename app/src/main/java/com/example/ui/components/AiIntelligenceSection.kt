package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CrispWhite
import com.example.ui.theme.LightBorder
import com.example.ui.theme.LightSurfaceCard
import com.example.ui.theme.LightTextBody
import com.example.ui.theme.LightTextHeadline
import com.example.ui.theme.LightTextMuted
import com.example.ui.theme.PlusJakartaSans

/**
 * AI THAT TURNS EVIDENCE INTO ACTION Section
 * Replicates the visual design, colors, cards, and typography from reference image IMG_20261008_193641.jpg:
 * - Centered "AI RECOVERY INTELLIGENCE" purple pill badge
 * - Main heading: "AI THAT TURNS EVIDENCE INTO ACTION"
 * - Subtitle: "TWO SPECIALIZED AI LAYERS WORKING TOGETHER TO ANALYSE THE RESOURCE IMAGE AND FORMULATE CLEAR RE-USAGE WAY"
 * - Left Card: "WHAT IS THE RESOURCE READY TO BE TRANSFORMED"
 * - Right Card: "HOW SHOULD THE SYSTEM ALLOT THE RESOURCES INTO A SUSTAINABLE BY-PRODUCT ?"
 */
@Composable
fun AiIntelligenceSection(
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier
) {
    val darkSectionBgBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF070B0E), // Deep dark slate/charcoal matching ref image
            Color(0xFF091015),
            Color(0xFF060A0D)
        )
    )

    val lightSectionBgBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFF8FAFC),
            Color(0xFFF1F5F9),
            Color(0xFFF8FAFC)
        )
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(if (isDarkTheme) darkSectionBgBrush else lightSectionBgBrush)
            .padding(horizontal = 20.dp, vertical = 60.dp)
            .testTag("ai_intelligence_section"),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 940.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. Top Pill Badge: "AI RECOVERY INTELLIGENCE"
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .border(
                        width = 1.dp,
                        color = if (isDarkTheme) Color(0x558B5CF6) else Color(0x667C3AED),
                        shape = RoundedCornerShape(50.dp)
                    )
                    .background(
                        if (isDarkTheme) Color(0x332E1065) else Color(0x1F8B5CF6)
                    )
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .testTag("ai_recovery_badge")
            ) {
                Text(
                    text = "AI RECOVERY INTELLIGENCE",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = if (isDarkTheme) Color(0xFFC084FC) else Color(0xFF7C3AED),
                    letterSpacing = 1.2.sp
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // 2. Main Heading: "AI THAT TURNS EVIDENCE INTO ACTION"
            Text(
                text = "AI That Turns Evidence Into Action",
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 34.sp,
                lineHeight = 42.sp,
                letterSpacing = (-0.8).sp,
                color = if (isDarkTheme) CrispWhite else LightTextHeadline,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("ai_main_heading")
            )

            Spacer(modifier = Modifier.height(14.dp))

            // 3. Subtitle: bold and readable
            Text(
                text = "Two specialized AI layers working together to analyse the resource image and formulate clear re-usage way.",
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                lineHeight = 24.sp,
                color = if (isDarkTheme) CrispWhite else LightTextHeadline,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .widthIn(max = 680.dp)
                    .fillMaxWidth()
                    .testTag("ai_subtitle")
            )

            Spacer(modifier = Modifier.height(44.dp))

            // 4. Two Specialized AI Cards (2-column layout on wide screens, stacked on mobile)
            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val isTablet = maxWidth >= 640.dp

                if (isTablet) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            AiLayerCard(
                                icon = Icons.Default.AutoAwesome,
                                iconTint = Color(0xFFC084FC),
                                iconBg = Color(0x33581C87),
                                categoryTag = "GEMINI VISION ANALYSIS",
                                categoryColor = Color(0xFFC084FC),
                                quoteHeading = "\"What is the resource ready to be transformed?\"",
                                description = "Scans uploaded photos to identify the resources categories , estimates the volume , and identifies the inventory that is related to with the guided information by the enterprise",
                                footerFeature = "Multimodal Vision Assessment • Instant Inventory Mapping",
                                isDarkTheme = isDarkTheme,
                                testTag = "ai_card_vision"
                            )
                        }

                        Box(modifier = Modifier.weight(1f)) {
                            AiLayerCard(
                                icon = Icons.Default.Psychology,
                                iconTint = Color(0xFF818CF8),
                                iconBg = Color(0x33312E81),
                                categoryTag = "SYSTEM ALLOTMENT ENGINE",
                                categoryColor = Color(0xFF818CF8),
                                quoteHeading = "\"How should the system allot the resources into a sustainable by-product?\"",
                                description = "The system generates a plan of action by setting up inventory in a systematic approach and aligning the same to a value chain to complete the cycle",
                                footerFeature = "Action Plan Generation • Value Chain Circular Alignment",
                                isDarkTheme = isDarkTheme,
                                testTag = "ai_card_planner"
                            )
                        }
                    }
                } else {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(18.dp)
                    ) {
                        AiLayerCard(
                            icon = Icons.Default.AutoAwesome,
                            iconTint = Color(0xFFC084FC),
                            iconBg = Color(0x33581C87),
                            categoryTag = "GEMINI VISION ANALYSIS",
                            categoryColor = Color(0xFFC084FC),
                            quoteHeading = "\"What is the resource ready to be transformed?\"",
                            description = "Scans uploaded photos to identify the resources categories , estimates the volume , and identifies the inventory that is related to with the guided information by the enterprise",
                            footerFeature = "Multimodal Vision Assessment • Instant Inventory Mapping",
                            isDarkTheme = isDarkTheme,
                            testTag = "ai_card_vision"
                        )

                        AiLayerCard(
                            icon = Icons.Default.Psychology,
                            iconTint = Color(0xFF818CF8),
                            iconBg = Color(0x33312E81),
                            categoryTag = "SYSTEM ALLOTMENT ENGINE",
                            categoryColor = Color(0xFF818CF8),
                            quoteHeading = "\"How should the system allot the resources into a sustainable by-product?\"",
                            description = "The system generates a plan of action by setting up inventory in a systematic approach and aligning the same to a value chain to complete the cycle",
                            footerFeature = "Action Plan Generation • Value Chain Circular Alignment",
                            isDarkTheme = isDarkTheme,
                            testTag = "ai_card_planner"
                        )
                    }
                }
            }
        }
    }
}

/**
 * Card component strictly matching reference image IMG_20261008_193641.jpg:
 * - Rounded container with dark slate background
 * - Top-left icon in colored rounded container
 * - Category uppercase tracking-wider tag
 * - Large quoted heading text
 * - Explanatory body text
 * - Dark bottom pill with checkmark and features
 */
@Composable
private fun AiLayerCard(
    icon: ImageVector,
    iconTint: Color,
    iconBg: Color,
    categoryTag: String,
    categoryColor: Color,
    quoteHeading: String,
    description: String,
    footerFeature: String,
    isDarkTheme: Boolean,
    testTag: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = if (isDarkTheme) 10.dp else 4.dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = if (isDarkTheme) Color(0x40000000) else Color(0x100F172A),
                spotColor = if (isDarkTheme) Color(0x60000000) else Color(0x150F172A)
            )
            .clip(RoundedCornerShape(20.dp))
            .border(
                width = 1.dp,
                color = if (isDarkTheme) Color(0x2E334155) else LightBorder,
                shape = RoundedCornerShape(20.dp)
            )
            .background(
                if (isDarkTheme) Color(0xF80B131A) else LightSurfaceCard // Deep dark card as in ref image
            )
            .padding(26.dp)
            .testTag(testTag)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Icon in rounded container
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Category tag (e.g., GEMINI VISION ANALYSIS)
            Text(
                text = categoryTag,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                color = categoryColor,
                letterSpacing = 1.2.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Quoted Heading (e.g., "What is the resource ready to be transformed?")
            Text(
                text = quoteHeading,
                fontFamily = FontFamily.Monospace, // Monospace font as styled in the reference image
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                lineHeight = 26.sp,
                color = if (isDarkTheme) CrispWhite else LightTextHeadline
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Description Body: bold and high contrast
            Text(
                text = description,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                lineHeight = 22.sp,
                color = if (isDarkTheme) Color(0xFFE2E8F0) else LightTextHeadline
            )

            Spacer(modifier = Modifier.height(22.dp))

            // Bottom pill container with checkmark (matching reference image)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (isDarkTheme) Color(0xFF0F1822) else Color(0xFFF1F5F9)
                    )
                    .border(
                        width = 1.dp,
                        color = if (isDarkTheme) Color(0x1F334155) else Color(0xFFE2E8F0),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 14.dp, vertical = 10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Check",
                        tint = if (isDarkTheme) Color(0xFF34D399) else Color(0xFF059669),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = footerFeature,
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = if (isDarkTheme) CrispWhite else LightTextHeadline
                    )
                }
            }
        }
    }
}
