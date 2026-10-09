package com.example.ui.components

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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Factory
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate950

data class JourneyStep(
    val number: String,
    val title: String,
    val description: String,
    val icon: ImageVector,
    val iconTint: Color,
    val iconBg: Color
)

@Composable
fun HowItWorksSection(
    isDarkTheme: Boolean,
    onStartJourneyClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Light-dark background with subtle green tint (matching the uploaded reference image)
    val darkSectionBgBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF070F0A), // Very dark slate with green tint
            Color(0xFF09140D), // Center ambient forest tone
            Color(0xFF060D09)  // Bottom dark
        )
    )

    val lightSectionBgBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFF0FDF4), // Mint-tinted slate-50
            Color(0xFFF7FEE7),
            Color(0xFFF8FAFC)
        )
    )

    val steps = listOf(
        JourneyStep(
            number = "01",
            title = "REGISTER",
            description = "Register a resource that is no longer useful to your business. LoopGrid creates a Digital Resource Passport (DRP) to digitally track its journey.",
            icon = Icons.Default.LocationOn,
            iconTint = Color(0xFFF43F5E), // Coral / Reddish pink as in ref
            iconBg = Color(0x2EF43F5E)
        ),
        JourneyStep(
            number = "02",
            title = "ANALYSE",
            description = "LoopGrid’s AI analyses the resource and adds it to a centralised inventory, bringing together similar resources from multiple enterprises.",
            icon = Icons.Default.AutoAwesome, // Brain/AI spark as in ref
            iconTint = Color(0xFFA855F7), // Purple as in ref
            iconBg = Color(0x2EA855F7)
        ),
        JourneyStep(
            number = "03",
            title = "ASSIGN THE PRODUCER",
            description = "LoopGrid matches the aggregated resource with the right processing unit where it can be reused, recovered, and create greater value.",
            icon = Icons.Default.Factory, // Processing/document as in ref
            iconTint = Color(0xFF6366F1), // Indigo/Blue as in ref
            iconBg = Color(0x2E6366F1)
        ),
        JourneyStep(
            number = "04",
            title = "MOBILITY PARTNER",
            description = "LoopGrid identifies the best-fit mobility partner based on distance, availability, capacity, and transportation cost.",
            icon = Icons.Default.LocalShipping, // Mobility/Partner as in ref
            iconTint = Color(0xFFF59E0B), // Amber as in ref
            iconBg = Color(0x2EF59E0B)
        ),
        JourneyStep(
            number = "05",
            title = "TRANSIT",
            description = "The resource is prepared, packed, and transported to its destination. Every movement is recorded and updated on its Digital Resource Passport.",
            icon = Icons.Default.Navigation, // Transit movement / flame as in ref
            iconTint = Color(0xFF10B981), // Emerald green as in ref
            iconBg = Color(0x2E10B981)
        ),
        JourneyStep(
            number = "06",
            title = "SETTLEMENT",
            description = "Once the physical transaction is completed, LoopGrid facilitates the settlement between all participating parties, with payments processed within 5–6 working days.",
            icon = Icons.Default.VerifiedUser, // Shield / Settlement as in ref
            iconTint = Color(0xFF06B6D4), // Cyan / Teal as in ref
            iconBg = Color(0x2E06B6D4)
        )
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(if (isDarkTheme) darkSectionBgBrush else lightSectionBgBrush)
            .padding(horizontal = 20.dp, vertical = 56.dp)
            .testTag("how_it_works_section")
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. Tag in the middle: "HOW LOOPGRID WORKS" in green bold text
            Text(
                text = "HOW LOOPGRID WORKS",
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = if (isDarkTheme) EmeraldLight else LightEmeraldDark,
                letterSpacing = 1.6.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.testTag("how_it_works_tag")
            )

            Spacer(modifier = Modifier.height(14.dp))

            // 2. Big bold heading: "TURNING WHAT’S LEFT BEHIND INTO WHAT’S POSSIBLE"
            // "TURNING WHAT’S LEFT " is white, "BEHIND INTO WHAT’S POSSIBLE" is green
            val headingText = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = if (isDarkTheme) CrispWhite else LightTextHeadline
                    )
                ) {
                    append("TURNING WHAT’S LEFT ")
                }
                withStyle(
                    style = SpanStyle(
                        color = if (isDarkTheme) EmeraldLight else LightEmerald
                    )
                ) {
                    append("BEHIND INTO WHAT’S POSSIBLE")
                }
            }

            Text(
                text = headingText,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp,
                lineHeight = 40.sp,
                letterSpacing = (-0.8).sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .widthIn(max = 680.dp)
                    .fillMaxWidth()
                    .testTag("how_it_works_heading")
            )

            Spacer(modifier = Modifier.height(14.dp))

            // 3. Subtitle in bold text: "A SIMPLE 6 - STEP JOURNEY FROM RESOURCE TO RECOVERY"
            Text(
                text = "A SIMPLE 6 - STEP JOURNEY FROM RESOURCE TO RECOVERY",
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                lineHeight = 18.sp,
                letterSpacing = 0.8.sp,
                color = if (isDarkTheme) CrispWhite else LightTextHeadline,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("how_it_works_subtitle")
            )

            Spacer(modifier = Modifier.height(36.dp))

            // 4. The 6 Boxes (as shown in reference image IMG_20261008_180132.jpg)
            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val isTablet = maxWidth >= 640.dp

                if (isTablet) {
                    // 2-column grid layout matching reference image
                    Column(
                        modifier = Modifier
                            .widthIn(max = 840.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        for (i in steps.indices step 2) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Box(modifier = Modifier.weight(1f)) {
                                    JourneyStepCard(step = steps[i], isDarkTheme = isDarkTheme)
                                }
                                if (i + 1 < steps.size) {
                                    Box(modifier = Modifier.weight(1f)) {
                                        JourneyStepCard(step = steps[i + 1], isDarkTheme = isDarkTheme)
                                    }
                                } else {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                } else {
                    // Mobile: One box below another as requested
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        steps.forEach { step ->
                            JourneyStepCard(step = step, isDarkTheme = isDarkTheme)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(42.dp))

            // 5. Button: "START YOUR JOURNEY" in vibrant emerald green
            // Directly connected to register resource functionality
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Button(
                    onClick = onStartJourneyClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isDarkTheme) EmeraldPrimary else LightEmerald
                    ),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .shadow(
                            elevation = 16.dp,
                            shape = RoundedCornerShape(14.dp),
                            ambientColor = if (isDarkTheme) Color(0x6610B981) else Color(0x40059669),
                            spotColor = if (isDarkTheme) Color(0x8010B981) else Color(0x60059669)
                        )
                        .height(54.dp)
                        .testTag("btn_start_your_journey")
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(horizontal = 18.dp)
                    ) {
                        Text(
                            text = "START YOUR JOURNEY",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = if (isDarkTheme) Slate950 else CrispWhite,
                            letterSpacing = 0.5.sp
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Arrow Forward",
                            tint = if (isDarkTheme) Slate950 else CrispWhite,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Step 1 of 6 • Register your leftover resource & generate its Digital Resource Passport",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = if (isDarkTheme) Color(0x99CBD5E1) else LightTextMuted,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

/**
 * Individual Card strictly matching the structure in reference image IMG_20261008_180132.jpg:
 * - Top Row: Colored icon inside rounded pill/square (left) and step number (right)
 * - Heading: Bold title text
 * - Body: Description text
 */
@Composable
private fun JourneyStepCard(
    step: JourneyStep,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = if (isDarkTheme) 6.dp else 4.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = if (isDarkTheme) Color(0x33000000) else Color(0x100F172A),
                spotColor = if (isDarkTheme) Color(0x4D000000) else Color(0x150F172A)
            )
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = 1.dp,
                color = if (isDarkTheme) Color(0x26334155) else LightBorder,
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                if (isDarkTheme) Color(0xF50C1610) else LightSurfaceCard // Deep dark card with faint green-slate tint
            )
            .padding(20.dp)
            .testTag("step_card_${step.number}")
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Top Row: Icon on left, Step Number on right (as in reference image)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Colored icon in rounded box
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(step.iconBg),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = step.icon,
                        contentDescription = step.title,
                        tint = step.iconTint,
                        modifier = Modifier.size(22.dp)
                    )
                }

                // Step Number (e.g., "01", "02")
                Text(
                    text = step.number,
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = if (isDarkTheme) Color(0x5994A3B8) else Color(0x7364748B),
                    letterSpacing = (-0.5).sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Step Title
            Text(
                text = step.title,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                lineHeight = 22.sp,
                color = if (isDarkTheme) CrispWhite else LightTextHeadline
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Step Description: High contrast and bold
            Text(
                text = step.description,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
                lineHeight = 20.sp,
                color = if (isDarkTheme) Color(0xFFE2E8F0) else LightTextHeadline
            )
        }
    }
}
