package com.example.ui.components

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.CurrencyRupee
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.DirectionsTransit
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Factory
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.PrecisionManufacturing
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.EnterpriseProfile
import com.example.data.EnterpriseRole
import com.example.ui.theme.CrispWhite
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.LightBorder
import com.example.ui.theme.LightEmerald
import com.example.ui.theme.LightEmeraldDark
import com.example.ui.theme.LightSurfaceCard
import com.example.ui.theme.LightTextHeadline
import com.example.ui.theme.PlusJakartaSans
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate950

/**
 * 1. HERO COMMAND CENTER SECTION (Inspired by reference screenshot)
 */
@Composable
fun CommandCenterHeroCard(
    profile: EnterpriseProfile,
    isDarkTheme: Boolean,
    onPrimaryActionClick: () -> Unit,
    onSecondaryActionClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val role = profile.role

    val cardBgBrush = Brush.verticalGradient(
        colors = if (isDarkTheme) {
            listOf(Color(0xFF0A2218), Color(0xFF06140F))
        } else {
            listOf(Color(0xFFDCFCE7), Color(0xFFF0FDF4))
        }
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .border(
                1.5.dp,
                if (isDarkTheme) Color(0x4410B981) else Color(0x66059669),
                RoundedCornerShape(22.dp)
            )
            .background(cardBgBrush)
            .padding(22.dp)
            .testTag("hero_command_center_card")
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Badges row
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(if (isDarkTheme) Color(0x40F59E0B) else Color(0x2BF59E0B))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "DEMO DATA",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 9.sp,
                        color = Color(0xFFF59E0B)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = when (role) {
                        EnterpriseRole.RESOURCE_PROVIDER -> "CIRCULAR RESOURCE COMMAND CENTER"
                        EnterpriseRole.TRANSIT_PARTNER -> "GREEN LOGISTICS & FLEET COMMAND"
                        EnterpriseRole.PROCESSING_ENTERPRISE -> "SMELTER & REFINERY RECOVERY COMMAND"
                        EnterpriseRole.FINANCIAL_PARTNER -> "WORKING CAPITAL CREDIT & SETTLEMENT HUB"
                    },
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    letterSpacing = 0.8.sp,
                    color = if (isDarkTheme) Slate400 else Color(0xFF475569)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Greeting
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Good day, ",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 28.sp,
                    color = if (isDarkTheme) CrispWhite else LightTextHeadline
                )
                Text(
                    text = profile.ownerName.ifBlank { profile.enterpriseName.take(16).ifBlank { "Partner" } },
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 28.sp,
                    color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Subtitle tailored to role
            Text(
                text = when (role) {
                    EnterpriseRole.RESOURCE_PROVIDER ->
                        "Let's recover another industrial resource today. Every reported feed and verified match protects enterprise profitability and community sustainability."
                    EnterpriseRole.TRANSIT_PARTNER ->
                        "Let's dispatch another green haulage today. Every verified weighbridge slip and fleet transit connects the circular industrial supply chain."
                    EnterpriseRole.PROCESSING_ENTERPRISE ->
                        "Let's refine another scrap batch today. Every smelted lot and recycled feed diverts critical metals and chemicals from landfills."
                    EnterpriseRole.FINANCIAL_PARTNER ->
                        "Let's fund another circular transaction today. Every working capital credit line enables an MSME to execute without working capital shortages."
                },
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp,
                lineHeight = 19.sp,
                color = if (isDarkTheme) Color(0xCCD1D5DB) else Color(0xFF334155)
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Two primary action buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = onPrimaryActionClick,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isDarkTheme) EmeraldPrimary else LightEmerald
                    ),
                    modifier = Modifier.height(44.dp)
                ) {
                    Text(
                        text = when (role) {
                            EnterpriseRole.RESOURCE_PROVIDER -> "Start a Recovery →"
                            EnterpriseRole.TRANSIT_PARTNER -> "Accept Transit Mission →"
                            EnterpriseRole.PROCESSING_ENTERPRISE -> "Log Inbound Scrap →"
                            EnterpriseRole.FINANCIAL_PARTNER -> "Extend Working Capital →"
                        },
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 12.sp,
                        color = if (isDarkTheme) Slate950 else CrispWhite
                    )
                }

                OutlinedButton(
                    onClick = onSecondaryActionClick,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.height(44.dp)
                ) {
                    Text(
                        text = when (role) {
                            EnterpriseRole.RESOURCE_PROVIDER -> "Explore Hotspots ☍"
                            EnterpriseRole.TRANSIT_PARTNER -> "Fleet Waybills ☍"
                            EnterpriseRole.PROCESSING_ENTERPRISE -> "Smelter Batches ☍"
                            EnterpriseRole.FINANCIAL_PARTNER -> "Settlement Vault ☍"
                        },
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Metric score card inside hero
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(if (isDarkTheme) Color(0x6007130D) else Color(0x40F0FDF4))
                    .border(1.dp, if (isDarkTheme) Color(0x2210B981) else Color(0x2E059669), RoundedCornerShape(14.dp))
                    .padding(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = when (role) {
                                EnterpriseRole.RESOURCE_PROVIDER -> "IMPACT SCORE"
                                EnterpriseRole.TRANSIT_PARTNER -> "LOGISTICS EFFICIENCY SCORE"
                                EnterpriseRole.PROCESSING_ENTERPRISE -> "RECOVERY CONVERSION SCORE"
                                EnterpriseRole.FINANCIAL_PARTNER -> "CAPITAL LIQUIDITY INDEX"
                            },
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            letterSpacing = 0.5.sp,
                            color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = when (role) {
                                    EnterpriseRole.RESOURCE_PROVIDER -> "742"
                                    EnterpriseRole.TRANSIT_PARTNER -> "885"
                                    EnterpriseRole.PROCESSING_ENTERPRISE -> "910"
                                    EnterpriseRole.FINANCIAL_PARTNER -> "790"
                                },
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 28.sp,
                                color = if (isDarkTheme) CrispWhite else LightTextHeadline
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = when (role) {
                                    EnterpriseRole.RESOURCE_PROVIDER -> "+12 this month"
                                    EnterpriseRole.TRANSIT_PARTNER -> "+24 this month"
                                    EnterpriseRole.PROCESSING_ENTERPRISE -> "+18 this month"
                                    EnterpriseRole.FINANCIAL_PARTNER -> "+35 this month"
                                },
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = if (isDarkTheme) EmeraldLight else LightEmeraldDark,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                        }

                        Text(
                            text = when (role) {
                                EnterpriseRole.RESOURCE_PROVIDER -> "Verified field impact points across Maharashtra industrial belts"
                                EnterpriseRole.TRANSIT_PARTNER -> "Verified green freight kilometers & weighbridge clearances"
                                EnterpriseRole.PROCESSING_ENTERPRISE -> "Certified secondary material conversion percentage"
                                EnterpriseRole.FINANCIAL_PARTNER -> "Low-risk collateralized working capital deployed to MSMEs"
                            },
                            fontFamily = PlusJakartaSans,
                            fontSize = 11.sp,
                            color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(if (isDarkTheme) Color(0x3310B981) else Color(0x2010B981)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = when (role) {
                                EnterpriseRole.RESOURCE_PROVIDER -> Icons.Default.Eco
                                EnterpriseRole.TRANSIT_PARTNER -> Icons.Default.LocalShipping
                                EnterpriseRole.PROCESSING_ENTERPRISE -> Icons.Default.Autorenew
                                EnterpriseRole.FINANCIAL_PARTNER -> Icons.Default.AccountBalance
                            },
                            contentDescription = null,
                            tint = if (isDarkTheme) EmeraldLight else LightEmerald,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

/**
 * 2. IMPACT OVERVIEW (Row of 4 KPI Cards matching the reference image)
 */
@Composable
fun ImpactOverviewRow(
    role: EnterpriseRole,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier
) {
    val items = when (role) {
        EnterpriseRole.RESOURCE_PROVIDER -> listOf(
            KpiItem("13", "Hotspots\nReported", "USER-REPORTED", Color(0xFFF59E0B)),
            KpiItem("6", "Cleanup\nMissions", "USER-REPORTED", Color(0xFFF59E0B)),
            KpiItem("340 kg", "Waste\nRemoved", "ESTIMATED", Color(0xFF38BDF8)),
            KpiItem("2", "Locations\nRecovered", "VERIFIED", Color(0xFF10B981))
        )
        EnterpriseRole.TRANSIT_PARTNER -> listOf(
            KpiItem("18", "Active Fleet\nWaybills", "USER-REPORTED", Color(0xFFF59E0B)),
            KpiItem("9", "Haulage\nDispatches", "USER-REPORTED", Color(0xFFF59E0B)),
            KpiItem("510 Tons", "Volume In\nTransit", "ESTIMATED", Color(0xFF38BDF8)),
            KpiItem("7", "Weighbridges\nCleared", "VERIFIED", Color(0xFF10B981))
        )
        EnterpriseRole.PROCESSING_ENTERPRISE -> listOf(
            KpiItem("24", "Inbound Scrap\nBatches", "USER-REPORTED", Color(0xFFF59E0B)),
            KpiItem("8", "Smelting\nRuns", "USER-REPORTED", Color(0xFFF59E0B)),
            KpiItem("420 Tons", "Secondary\nRefined", "ESTIMATED", Color(0xFF38BDF8)),
            KpiItem("6", "Assays Lab\nCertified", "VERIFIED", Color(0xFF10B981))
        )
        EnterpriseRole.FINANCIAL_PARTNER -> listOf(
            KpiItem("11", "Credit Line\nInquiries", "USER-REPORTED", Color(0xFFF59E0B)),
            KpiItem("5", "Active MSME\nFacilities", "USER-REPORTED", Color(0xFFF59E0B)),
            KpiItem("₹48.5 L", "Capital\nDeployed", "ESTIMATED", Color(0xFF38BDF8)),
            KpiItem("100%", "Settlement\nRate", "VERIFIED", Color(0xFF10B981))
        )
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.TrendingUp,
                    contentDescription = null,
                    tint = if (isDarkTheme) EmeraldLight else LightEmerald,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Impact Overview",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 17.sp,
                    color = if (isDarkTheme) CrispWhite else LightTextHeadline
                )
            }

            Text(
                text = "Central Hotspot Dataset",
                fontFamily = PlusJakartaSans,
                fontSize = 11.sp,
                color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items.forEach { kpi ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(14.dp))
                        .border(
                            1.dp,
                            if (isDarkTheme) Color(0x33334155) else Color(0xFFE2E8F0),
                            RoundedCornerShape(14.dp)
                        )
                        .background(if (isDarkTheme) Color(0xFF0D1714) else LightSurfaceCard)
                        .padding(10.dp)
                ) {
                    Column {
                        // Badge chip
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(kpi.badgeColor.copy(alpha = 0.15f))
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = kpi.badge,
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 8.sp,
                                color = kpi.badgeColor
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = kpi.value,
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp,
                            color = if (isDarkTheme) CrispWhite else LightTextHeadline
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = kpi.label,
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp,
                            lineHeight = 14.sp,
                            color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                        )
                    }
                }
            }
        }
    }
}

private data class KpiItem(val value: String, val label: String, val badge: String, val badgeColor: Color)

/**
 * 3. FIELD SURVEILLANCE PIPELINE (Circular Recovery Health - 5 Stages)
 */
@Composable
fun SurveillancePipelineHealth(
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .border(
                1.dp,
                if (isDarkTheme) Color(0x33334155) else Color(0xFFE2E8F0),
                RoundedCornerShape(18.dp)
            )
            .background(if (isDarkTheme) Color(0xFF0A120E) else LightSurfaceCard)
            .padding(18.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(if (isDarkTheme) Color(0x3310B981) else Color(0x2010B981))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "FIELD SURVEILLANCE PIPELINE",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.Bold,
                            fontSize = 9.sp,
                            color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Environmental Recovery Health",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp,
                        color = if (isDarkTheme) CrispWhite else LightTextHeadline
                    )
                }

                Text(
                    text = "13 Active Locations",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 5 Stages Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                PipelineStageBox(1, "REPORTED\n/ OPEN", "6", "Needs\nAttention", Color(0xFFF43F5E), isDarkTheme, Modifier.weight(1f))
                PipelineStageBox(2, "MISSION\nASSIGNED", "5", "In\nMobilization", Color(0xFFF59E0B), isDarkTheme, Modifier.weight(1f))
                PipelineStageBox(3, "IN\nPROGRESS", "0", "Active\nCleanup", Color(0xFFD97706), isDarkTheme, Modifier.weight(1f))
                PipelineStageBox(4, "CLEARED", "1", "Debris\nRemoved", Color(0xFF10B981), isDarkTheme, Modifier.weight(1f))
                PipelineStageBox(5, "TRANSFORMED", "1", "Protected\nHubs", Color(0xFF8B5CF6), isDarkTheme, Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun PipelineStageBox(
    step: Int,
    stageName: String,
    count: String,
    statusText: String,
    color: Color,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, color.copy(alpha = 0.35f), RoundedCornerShape(10.dp))
            .background(color.copy(alpha = if (isDarkTheme) 0.12f else 0.08f))
            .padding(8.dp)
    ) {
        Column {
            Text(
                text = "$step. $stageName",
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Bold,
                fontSize = 9.sp,
                lineHeight = 11.sp,
                color = color
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = count,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
                color = if (isDarkTheme) CrispWhite else LightTextHeadline
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = statusText,
                fontFamily = PlusJakartaSans,
                fontSize = 9.sp,
                lineHeight = 11.sp,
                color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
            )
        }
    }
}

/**
 * 4. YOUR NEXT BEST ACTION CARD (Green container with action button from reference)
 */
@Composable
fun NextBestActionCard(
    role: EnterpriseRole,
    isDarkTheme: Boolean,
    onActionClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val (title, description, buttonLabel) = when (role) {
        EnterpriseRole.RESOURCE_PROVIDER -> Triple(
            "3 high-impact material matches are within 25 km of your plant",
            "Offtake lot #TRC-REC-2026-001 at Pune MIDC requires 2 more offtake confirmations to complete its recovery pipeline.",
            "Join Cleanup Mission →"
        )
        EnterpriseRole.TRANSIT_PARTNER -> Triple(
            "2 scheduled bulk pickups require immediate weighbridge dispatch",
            "Route #TRC-HAUL-2026-04 at Bhosari awaits a 25-ton trailer assignment to seal weighbridge clearance.",
            "Assign Fleet Vehicle →"
        )
        EnterpriseRole.PROCESSING_ENTERPRISE -> Triple(
            "Quality assay clearance approved for 35 Tons Mild Steel Scrap",
            "Batch #REC-SML-2026-19 is cleared and ready for induction furnace melting at Chakan Unit.",
            "Schedule Smelter Run →"
        )
        EnterpriseRole.FINANCIAL_PARTNER -> Triple(
            "MSME Working Capital Shortage: ₹3,50,000 credit line requested",
            "Shree Balaji Works requires 30-day working capital to execute their 35-ton Mild Steel transaction. Guaranteed by escrow & buyer DRP.",
            "Disburse Working Credit →"
        )
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .border(
                1.5.dp,
                if (isDarkTheme) Color(0x6610B981) else Color(0x99059669),
                RoundedCornerShape(18.dp)
            )
            .background(if (isDarkTheme) Color(0xF0071C14) else Color(0xFFF0FDF4))
            .padding(18.dp)
            .testTag("next_best_action_card")
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isDarkTheme) Color(0x3310B981) else Color(0x2010B981)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = if (isDarkTheme) EmeraldLight else LightEmerald,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "YOUR NEXT BEST ACTION",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 10.sp,
                        letterSpacing = 0.5.sp,
                        color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = title,
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = if (isDarkTheme) CrispWhite else LightTextHeadline
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = description,
                        fontFamily = PlusJakartaSans,
                        fontSize = 12.sp,
                        lineHeight = 17.sp,
                        color = if (isDarkTheme) Color(0xCCD1D5DB) else Color(0xFF475569)
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = onActionClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isDarkTheme) EmeraldPrimary else LightEmerald
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.height(42.dp)
            ) {
                Text(
                    text = buttonLabel,
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = if (isDarkTheme) Slate950 else CrispWhite
                )
            }
        }
    }
}

/**
 * 5. SPECIALIZED FINANCIAL PARTNER CREDIT & SETTLEMENT DESK
 * (Tailored specifically for the user's brief on Financial Partner)
 */
@Composable
fun FinancialPartnerCreditDesk(
    isDarkTheme: Boolean,
    onApproveCredit: (amount: String, enterprise: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var hasApprovedCredit by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .border(
                1.5.dp,
                if (isDarkTheme) Color(0x668B5CF6) else Color(0x807C3AED),
                RoundedCornerShape(20.dp)
            )
            .background(if (isDarkTheme) Color(0xFF0F111E) else Color(0xFFFAF5FF))
            .padding(20.dp)
            .testTag("financial_partner_credit_desk")
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(Color(0x268B5CF6)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountBalance,
                            contentDescription = null,
                            tint = Color(0xFF8B5CF6),
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = "MSME WORKING CAPITAL CREDIT DESK",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 11.sp,
                            letterSpacing = 0.8.sp,
                            color = Color(0xFF8B5CF6)
                        )
                        Text(
                            text = "Circular Liquidity & Escrow Settlement",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                            color = if (isDarkTheme) CrispWhite else LightTextHeadline
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0x3310B981))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "ESCROW PROTECTED",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 9.sp,
                        color = Color(0xFF10B981)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "As Financial Partner, you provide working capital credit directly to MSMEs experiencing capital shortages to undertake circular transactions. Funds are escrow-locked until material is verified and weighed.",
                fontFamily = PlusJakartaSans,
                fontSize = 12.sp,
                lineHeight = 17.sp,
                color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Pending Application Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .border(
                        1.dp,
                        if (hasApprovedCredit) Color(0x4410B981) else Color(0x448B5CF6),
                        RoundedCornerShape(14.dp)
                    )
                    .background(if (isDarkTheme) Color(0xFF16192E) else Color.White)
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Shree Balaji Fabrication Works (MSME)",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = if (isDarkTheme) CrispWhite else LightTextHeadline
                            )
                            Text(
                                text = "Resource Lot: 35 Tons Mild Steel Scrap • DRP-MH-2026-9041",
                                fontFamily = PlusJakartaSans,
                                fontSize = 11.sp,
                                color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (hasApprovedCredit) Color(0x3310B981) else Color(0x33F59E0B))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = if (hasApprovedCredit) "CREDIT DISBURSED" else "WORKING CAPITAL SHORTAGE",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 9.sp,
                                color = if (hasApprovedCredit) Color(0xFF10B981) else Color(0xFFF59E0B)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Credit Amount", fontSize = 10.sp, color = if (isDarkTheme) Slate400 else Color(0xFF64748B))
                            Text("₹3,50,000", fontFamily = PlusJakartaSans, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF10B981))
                        }
                        Column {
                            Text("Tenure", fontSize = 10.sp, color = if (isDarkTheme) Slate400 else Color(0xFF64748B))
                            Text("30 Days", fontFamily = PlusJakartaSans, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = if (isDarkTheme) CrispWhite else LightTextHeadline)
                        }
                        Column {
                            Text("Interest Yield", fontSize = 10.sp, color = if (isDarkTheme) Slate400 else Color(0xFF64748B))
                            Text("1.2% / month", fontFamily = PlusJakartaSans, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF8B5CF6))
                        }
                        Column {
                            Text("Collateral Lock", fontSize = 10.sp, color = if (isDarkTheme) Slate400 else Color(0xFF64748B))
                            Text("100% Escrow DRP", fontFamily = PlusJakartaSans, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF38BDF8))
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    if (!hasApprovedCredit) {
                        Button(
                            onClick = {
                                hasApprovedCredit = true
                                onApproveCredit("₹3,50,000", "Shree Balaji Fabrication Works")
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B5CF6)),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(42.dp)
                                .testTag("btn_approve_working_credit")
                        ) {
                            Icon(Icons.Default.Paid, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Disburse ₹3,50,000 Working Capital Credit Line",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = Color.White
                            )
                        }
                    } else {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0x2210B981))
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF10B981), modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Working capital credit active. Settlement scheduled upon buyer weighbridge confirmation.",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 11.sp,
                                color = Color(0xFF10B981)
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * 6. ACTION NEEDED NEARBY (Cards with categories, waste amount, status from reference)
 */
@Composable
fun ActionNeededNearbySection(
    isDarkTheme: Boolean,
    onViewAllClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Color(0xFFF59E0B),
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Action Needed Nearby",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 17.sp,
                    color = if (isDarkTheme) CrispWhite else LightTextHeadline
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(if (isDarkTheme) Color(0x33F59E0B) else Color(0x20F59E0B))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "Showing 2 of 6",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 9.sp,
                        color = Color(0xFFF59E0B)
                    )
                }
            }

            Text(
                text = "View All →",
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = if (isDarkTheme) EmeraldLight else LightEmeraldDark,
                modifier = Modifier.clickable { onViewAllClick() }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Card 1: Active Riverbank Rescue Operation
        NearbyActionCard(
            status = "ACTIVE",
            points = "+150 pts",
            title = "Riverbank Scrap & By-product Recovery",
            location = "East Riverbank, Sector 4 MIDC",
            category = "Industrial Polymer & Metals",
            estWaste = "200 kg / 35 Tons",
            partners = "3 / 20 Registered",
            isDarkTheme = isDarkTheme
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Card 2: Upcoming Recovery Mission
        NearbyActionCard(
            status = "UPCOMING",
            points = "+150 pts",
            title = "Recovery Mission: GPS Pin 20.280909, 85.802719",
            location = "Pune Chakan Cluster Waypoint",
            category = "Mild Steel & Zinc Residue",
            estWaste = "Approx. 45 Tons",
            partners = "1 / 15 Assigned",
            isDarkTheme = isDarkTheme
        )
    }
}

@Composable
private fun NearbyActionCard(
    status: String,
    points: String,
    title: String,
    location: String,
    category: String,
    estWaste: String,
    partners: String,
    isDarkTheme: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(
                1.dp,
                if (isDarkTheme) Color(0x33334155) else Color(0xFFE2E8F0),
                RoundedCornerShape(16.dp)
            )
            .background(if (isDarkTheme) Color(0xFF0C1612) else LightSurfaceCard)
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(if (status == "ACTIVE") Color(0x33F59E0B) else Color(0x2238BDF8))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = status,
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 9.sp,
                        color = if (status == "ACTIVE") Color(0xFFF59E0B) else Color(0xFF38BDF8)
                    )
                }

                Text(
                    text = points,
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = Color(0xFF10B981)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = if (isDarkTheme) CrispWhite else LightTextHeadline
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = Slate400, modifier = Modifier.size(12.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = location,
                    fontFamily = PlusJakartaSans,
                    fontSize = 11.sp,
                    color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Details row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (isDarkTheme) Color(0x33050C09) else Color(0xFFF8FAFC))
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("CATEGORY", fontSize = 8.sp, color = Slate400)
                    Text(category, fontFamily = PlusJakartaSans, fontWeight = FontWeight.Bold, fontSize = 11.sp, color = if (isDarkTheme) CrispWhite else LightTextHeadline)
                }
                Column {
                    Text("EST. RESOURCE", fontSize = 8.sp, color = Slate400)
                    Text(estWaste, fontFamily = PlusJakartaSans, fontWeight = FontWeight.Bold, fontSize = 11.sp, color = if (isDarkTheme) CrispWhite else LightTextHeadline)
                }
                Column {
                    Text("PARTNERS", fontSize = 8.sp, color = Slate400)
                    Text(partners, fontFamily = PlusJakartaSans, fontWeight = FontWeight.Bold, fontSize = 11.sp, color = if (isDarkTheme) CrispWhite else LightTextHeadline)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isDarkTheme) EmeraldPrimary else LightEmerald
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(38.dp)
            ) {
                Text(
                    text = "View Mission Details →",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = if (isDarkTheme) Slate950 else CrispWhite
                )
            }
        }
    }
}

/**
 * 7. RECENT RECOVERIES (Before / After from screenshot)
 */
@Composable
fun RecentRecoveriesCard(
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .border(1.dp, if (isDarkTheme) Color(0x33334155) else Color(0xFFE2E8F0), RoundedCornerShape(18.dp))
            .background(if (isDarkTheme) Color(0xFF0B1410) else LightSurfaceCard)
            .padding(18.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Verified, contentDescription = null, tint = Color(0xFF10B981), modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Recent Recoveries",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 17.sp,
                        color = if (isDarkTheme) CrispWhite else LightTextHeadline
                    )
                }
                Text("View Timeline →", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = if (isDarkTheme) EmeraldLight else LightEmeraldDark)
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Before / After simulation block
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                // Before half
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFF262626))
                        .padding(8.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    Text("BEFORE: SCRAP BINS", fontFamily = PlusJakartaSans, fontWeight = FontWeight.Bold, fontSize = 9.sp, color = Color(0xFFE5E5E5))
                }
                // After half
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFF064E3B))
                        .padding(8.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    Text("AFTER: CIRCULAR RECOVERY", fontFamily = PlusJakartaSans, fontWeight = FontWeight.Bold, fontSize = 9.sp, color = Color(0xFF6EE7B7))
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Pine Street Industrial Lot",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = if (isDarkTheme) CrispWhite else LightTextHeadline
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0x3310B981))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text("VERIFIED", fontFamily = PlusJakartaSans, fontWeight = FontWeight.Bold, fontSize = 9.sp, color = Color(0xFF10B981))
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("WASTE REMOVED", fontSize = 8.sp, color = Slate400)
                    Text("400 kg", fontFamily = PlusJakartaSans, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = if (isDarkTheme) CrispWhite else LightTextHeadline)
                }
                Column {
                    Text("SCORE IMPROVEMENT", fontSize = 8.sp, color = Slate400)
                    Text("+75 pts", fontFamily = PlusJakartaSans, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color(0xFF10B981))
                }
                Column {
                    Text("DATE", fontSize = 8.sp, color = Slate400)
                    Text("July 2026", fontFamily = PlusJakartaSans, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = if (isDarkTheme) CrispWhite else LightTextHeadline)
                }
            }
        }
    }
}
