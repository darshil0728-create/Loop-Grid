package com.example.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.example.ui.theme.LightEmerald
import com.example.ui.theme.LightEmeraldDark
import com.example.ui.theme.LightSurfaceCard
import com.example.ui.theme.LightTextBody
import com.example.ui.theme.LightTextHeadline
import com.example.ui.theme.LightTextMuted
import com.example.ui.theme.PlusJakartaSans
import com.example.ui.theme.Slate300
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate950

enum class MsmePillarTab(val title: String) {
    VALUE_CREATION("VALUE CREATION"),
    BEYOND_SUSTAINABILITY("BEYOND SUSTAINABILITY"),
    NATIONS_MISSION("NATION'S MISSION")
}

@Composable
fun MsmePillarsSection(
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    val darkSectionBgBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF060D09),
            Color(0xFF09140E),
            Color(0xFF070F0A)
        )
    )

    val lightSectionBgBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFF0FDF4),
            Color(0xFFF8FAFC)
        )
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(if (isDarkTheme) darkSectionBgBrush else lightSectionBgBrush)
            .padding(horizontal = 20.dp, vertical = 60.dp)
            .testTag("msme_pillars_section"),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 840.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. Top Pill Badge (matching reference image)
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .border(
                        width = 1.dp,
                        color = if (isDarkTheme) Color(0x5510B981) else Color(0x66059669),
                        shape = RoundedCornerShape(50.dp)
                    )
                    .background(
                        if (isDarkTheme) Color(0x33064E3B) else Color(0x1F10B981)
                    )
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "INDIA MSME CIRCULAR PROGRESSION",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = if (isDarkTheme) EmeraldLight else LightEmeraldDark,
                    letterSpacing = 1.2.sp
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // 2. Main Heading in the Centre: "MAKING SUSTAINABILITY PRACTICAL, PROFITABLE AND ACCESSIBLE FOR INDIA'S MSMES."
            Text(
                text = "MAKING SUSTAINABILITY PRACTICAL, PROFITABLE AND ACCESSIBLE FOR INDIA'S MSMES.",
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 28.sp,
                lineHeight = 36.sp,
                letterSpacing = (-0.7).sp,
                color = if (isDarkTheme) CrispWhite else LightTextHeadline,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("msme_pillars_heading")
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 3. Three distinct explanatory paragraphs
            Column(
                modifier = Modifier
                    .widthIn(max = 760.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = "India's MSMEs are the backbone of its industrial economy, yet many businesses face challenges in managing unused materials, rising resource costs, fragmented recycling networks, transportation inefficiencies, and the growing need for sustainable operations. Valuable materials often go underutilised simply because the right connections, information, and infrastructure are missing.",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.5.sp,
                    lineHeight = 24.sp,
                    color = if (isDarkTheme) CrispWhite else LightTextHeadline,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "LoopGrid bridges this gap by transforming how industrial resources are identified, exchanged, recovered, and reused. Our AI-enabled platform connects MSMEs with relevant industrial buyers, processing units, and mobility partners, helping turn underutilised materials into potential sources of revenue and productive value.",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.5.sp,
                    lineHeight = 24.sp,
                    color = if (isDarkTheme) CrispWhite else LightTextHeadline,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Through AI-assisted resource identification, digital resource inventories, intelligent matching, resource aggregation, coordinated logistics, and Digital Resource Passports, LoopGrid brings fragmented resource flows into a more organised and traceable circular system.",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.5.sp,
                    lineHeight = 24.sp,
                    color = if (isDarkTheme) CrispWhite else LightTextHeadline,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(36.dp))

            // 4. Box-like Card Area with 3 Segmented Control Buttons (strictly matching reference layout)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = if (isDarkTheme) 16.dp else 8.dp,
                        shape = RoundedCornerShape(24.dp),
                        ambientColor = if (isDarkTheme) Color(0x40000000) else Color(0x100F172A),
                        spotColor = if (isDarkTheme) Color(0x60000000) else Color(0x150F172A)
                    )
                    .clip(RoundedCornerShape(24.dp))
                    .border(
                        width = 1.dp,
                        color = if (isDarkTheme) Color(0x33334155) else LightBorder,
                        shape = RoundedCornerShape(24.dp)
                    )
                    .background(
                        if (isDarkTheme) Color(0xF80D151F) else LightSurfaceCard
                    )
                    .padding(20.dp)
                    .testTag("msme_pillars_card")
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    // Segmented 3-Button Control Header
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .background(if (isDarkTheme) Color(0xFF080E15) else Color(0xFFF1F5F9))
                            .border(1.dp, if (isDarkTheme) Color(0x1F334155) else Color(0xFFE2E8F0), RoundedCornerShape(14.dp))
                            .padding(4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            MsmePillarTab.values().forEachIndexed { index, tab ->
                                val isSelected = selectedTab == index
                                Box(
                                    modifier = Modifier
                                        .weight(1f, fill = false)
                                        .shadow(
                                            elevation = if (isSelected) 4.dp else 0.dp,
                                            shape = RoundedCornerShape(10.dp),
                                            ambientColor = Color(0x20000000),
                                            spotColor = Color(0x30000000)
                                        )
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(
                                            if (isSelected) {
                                                if (isDarkTheme) Color(0xFF1E293B) else Color.White
                                            } else {
                                                Color.Transparent
                                            }
                                        )
                                        .clickable { selectedTab = index }
                                        .padding(horizontal = 14.dp, vertical = 12.dp)
                                        .testTag("tab_button_$index"),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = tab.title,
                                        fontFamily = PlusJakartaSans,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        fontSize = 12.sp,
                                        color = if (isSelected) {
                                            if (isDarkTheme) CrispWhite else LightTextHeadline
                                        } else {
                                            if (isDarkTheme) Slate400 else LightTextMuted
                                        },
                                        letterSpacing = 0.5.sp,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Animated Tab Content with Image, Stage Indicator, Content, and Key Metric Box
                    AnimatedContent(
                        targetState = selectedTab,
                        transitionSpec = { fadeIn(tween(250)) togetherWith fadeOut(tween(200)) },
                        label = "pillar_tab_content"
                    ) { tabIndex ->
                        when (tabIndex) {
                            0 -> ValueCreationTabContent(isDarkTheme)
                            1 -> BeyondSustainabilityTabContent(isDarkTheme)
                            else -> NationsMissionTabContent(isDarkTheme)
                        }
                    }
                }
            }
        }
    }
}

/**
 * Tab 1: VALUE CREATION
 */
@Composable
private fun ValueCreationTabContent(isDarkTheme: Boolean) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Lightweight image banner with tag overlay
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .border(1.dp, Color(0x3310B981), RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_pillar_value_creation),
                contentDescription = "Value Creation in MSME recovery",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            )

            // Ribbon tag
            Box(
                modifier = Modifier
                    .padding(12.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xD9064E3B))
                    .border(1.dp, Color(0x8034D399), RoundedCornerShape(6.dp))
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text(
                    text = "RECOVERED VALUE LEVERS",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    color = EmeraldLight
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "STAGE 1 OF 3",
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = if (isDarkTheme) EmeraldLight else LightEmeraldDark,
            letterSpacing = 1.0.sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "How LoopGrid creates value",
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            color = if (isDarkTheme) CrispWhite else LightTextHeadline
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 6 Value creation levers
        val valuePoints = listOf(
            "UNLOCK HIDDEN ECONOMIC VALUE" to "Connect unused and recoverable materials with potential buyers and downstream industries, creating new revenue opportunities.",
            "IMPROVE OPERATIONAL EFFICIENCY" to "Reduce the friction involved in identifying buyers, coordinating pickups, managing fragmented material streams, and tracking transactions.",
            "MAKE SUSTAINABILITY ACCESSIBLE" to "Help MSMEs participate in resource recovery and circular supply chains without requiring them to independently build an entire sustainability ecosystem.",
            "ENABLE SMARTER RESOURCE UTILISATION" to "Aggregate compatible material streams from multiple enterprises to create more commercially viable lots and improve resource recovery opportunities.",
            "BUILD TRANSPARENCY AND TRACEABILITY" to "Use Digital Resource Passports to document a resource's origin, movement, verification status, and destination throughout its journey.",
            "SUPPORT RESPONSIBLE INDUSTRIAL GROWTH" to "Encourage the productive reuse and recovery of materials, helping reduce avoidable disposal and reliance on virgin resources where suitable alternatives exist."
        )

        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            valuePoints.forEach { (heading, desc) ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = if (isDarkTheme) EmeraldLight else LightEmerald,
                        modifier = Modifier
                            .size(18.dp)
                            .padding(top = 2.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = heading,
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = if (isDarkTheme) CrispWhite else LightTextHeadline
                        )
                        Text(
                            text = desc,
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.5.sp,
                            lineHeight = 20.sp,
                            color = if (isDarkTheme) Color(0xFFE2E8F0) else LightTextHeadline
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Key Metric Box (matching reference image)
        KeyMetricCard(
            label = "KEY METRIC",
            value = "6 Circular Value Levers Active",
            isDarkTheme = isDarkTheme
        )
    }
}

/**
 * Tab 2: BEYOND SUSTAINABILITY
 */
@Composable
private fun BeyondSustainabilityTabContent(isDarkTheme: Boolean) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .border(1.dp, Color(0x3314B8A6), RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_pillar_beyond_sustainability),
                contentDescription = "Beyond sustainability ecosystem",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            )

            Box(
                modifier = Modifier
                    .padding(12.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xD9042F2E))
                    .border(1.dp, Color(0x802DD4BF), RoundedCornerShape(6.dp))
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text(
                    text = "RESILIENT INDUSTRIAL ECOSYSTEM",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    color = Color(0xFF2DD4BF)
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "STAGE 2 OF 3",
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = Color(0xFF2DD4BF),
            letterSpacing = 1.0.sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Beyond sustainability: Building a stronger industrial ecosystem",
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            color = if (isDarkTheme) CrispWhite else LightTextHeadline
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "LoopGrid is designed to make environmental responsibility work alongside business performance. By connecting fragmented MSME supply with industrial demand, we aim to create a network where one enterprise's underutilised material can become another enterprise's valuable input.",
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.5.sp,
            lineHeight = 23.sp,
            color = if (isDarkTheme) CrispWhite else LightTextHeadline
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "This approach supports a more resource-efficient, connected, and resilient industrial ecosystem — where sustainability is integrated into everyday operations rather than treated as a separate expense.",
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.5.sp,
            lineHeight = 23.sp,
            color = if (isDarkTheme) CrispWhite else LightTextHeadline
        )

        Spacer(modifier = Modifier.height(20.dp))

        KeyMetricCard(
            label = "KEY METRIC",
            value = "100% Integrated Industrial Demand",
            isDarkTheme = isDarkTheme
        )
    }
}

/**
 * Tab 3: NATION'S MISSION
 */
@Composable
private fun NationsMissionTabContent(isDarkTheme: Boolean) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .border(1.dp, Color(0x33F59E0B), RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_pillar_nations_mission),
                contentDescription = "Viksit Bharat 2047 contribution",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            )

            Box(
                modifier = Modifier
                    .padding(12.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xD978350F))
                    .border(1.dp, Color(0x80FBBF24), RoundedCornerShape(6.dp))
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text(
                    text = "VIKSIT BHARAT 2047 ALIGNED",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    color = Color(0xFFFBBF24)
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "STAGE 3 OF 3",
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = Color(0xFFF59E0B),
            letterSpacing = 1.0.sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Our contribution to Viksit Bharat 2047",
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            color = if (isDarkTheme) CrispWhite else LightTextHeadline
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Aligned with the broader vision of Viksit Bharat 2047, LoopGrid seeks to support India's transition towards more competitive MSMEs, digitally enabled industrial operations, resource-efficient manufacturing, and a stronger circular economy.",
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.5.sp,
            lineHeight = 23.sp,
            color = if (isDarkTheme) CrispWhite else LightTextHeadline
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Our ambition is to make the benefits of resource recovery more accessible to businesses of every scale, helping them create economic value while contributing to India's environmental and sustainable development goals.",
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.5.sp,
            lineHeight = 23.sp,
            color = if (isDarkTheme) CrispWhite else LightTextHeadline
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Our belief is simple: India's industrial future should not depend on producing more waste, but on creating more value from the resources we already have.",
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            lineHeight = 22.sp,
            color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "LoopGrid — Turning what's left behind into what's possible tomorrow.",
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = if (isDarkTheme) CrispWhite else LightTextHeadline
        )

        Spacer(modifier = Modifier.height(20.dp))

        KeyMetricCard(
            label = "KEY METRIC",
            value = "Viksit Bharat 2047 Circular Target",
            isDarkTheme = isDarkTheme
        )
    }
}

/**
 * Key Metric box styled strictly matching the reference image bottom card
 */
@Composable
private fun KeyMetricCard(
    label: String,
    value: String,
    isDarkTheme: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(
                if (isDarkTheme) Color(0xFF091017) else Color(0xFFF1F5F9)
            )
            .border(
                1.dp,
                if (isDarkTheme) Color(0x1F334155) else Color(0xFFE2E8F0),
                RoundedCornerShape(14.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = label,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                color = if (isDarkTheme) Slate400 else LightTextMuted,
                letterSpacing = 1.0.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
                color = if (isDarkTheme) CrispWhite else LightTextHeadline
            )
        }
    }
}
