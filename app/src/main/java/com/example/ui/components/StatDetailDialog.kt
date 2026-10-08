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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Recycling
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.AmberOrange
import com.example.ui.theme.CrispWhite
import com.example.ui.theme.ElectricPurpleLight
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.PlusJakartaSans
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate900
import com.example.ui.theme.Slate950

@Composable
fun StatDetailDialog(
    statType: StatType,
    isDarkTheme: Boolean,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .widthIn(max = 480.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .border(
                    width = 1.dp,
                    color = if (isDarkTheme) Color(0x66334155) else Color(0xFFCBD5E1),
                    shape = RoundedCornerShape(20.dp)
                )
                .background(if (isDarkTheme) Color(0xF80B1120) else Color.White)
                .padding(24.dp)
                .testTag("stat_detail_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val (title, icon, accent) = when (statType) {
                        StatType.DISTRICTS -> Triple("48 ACTIVE DISTRICTS", Icons.Default.LocationOn, EmeraldLight)
                        StatType.WASTE_REMOVED -> Triple("3,420 TONS REMOVED", Icons.Default.Recycling, EmeraldLight)
                        StatType.MSME_PARTNERS -> Triple("128 ACTIVE MSMEs", Icons.Default.People, AmberOrange)
                        StatType.SAVINGS -> Triple("₹8.4 LAKHS SAVINGS", Icons.Default.MonetizationOn, ElectricPurpleLight)
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(accent.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = icon, contentDescription = null, tint = accent, modifier = Modifier.size(18.dp))
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = title,
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 17.sp,
                            color = if (isDarkTheme) CrispWhite else Slate950
                        )
                    }

                    IconButton(onClick = onDismiss) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = if (isDarkTheme) Slate400 else Slate700)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                when (statType) {
                    StatType.DISTRICTS -> {
                        Text(
                            text = "Top Verified Manufacturing Clusters",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        ClusterRow("Pune MIDC (Bhosari & Chakan)", "Auto components & metal stampings", "420 Tons/mo", isDarkTheme)
                        ClusterRow("Peenya Industrial Estate, Bengaluru", "Precision machining & swarf", "310 Tons/mo", isDarkTheme)
                        ClusterRow("Manesar IMT, Haryana", "Polymer & die-cast offcuts", "280 Tons/mo", isDarkTheme)
                        ClusterRow("Surat Textile Park, Gujarat", "Recycled cotton lint & dyes", "540 Tons/mo", isDarkTheme)
                        ClusterRow("Coimbatore Foundry Corridor", "Foundry sand & ferrous scrap", "620 Tons/mo", isDarkTheme)
                    }
                    StatType.WASTE_REMOVED -> {
                        Text(
                            text = "Cumulative Materials Diverted From Landfill",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        MaterialProgressRow("Cold-Rolled Steel Coils", "1,420 Tons", 0.72f, EmeraldLight, isDarkTheme)
                        MaterialProgressRow("Industrial Polymer Resins", "860 Tons", 0.55f, EmeraldPrimary, isDarkTheme)
                        MaterialProgressRow("Foundry Sand & Slag", "680 Tons", 0.42f, AmberOrange, isDarkTheme)
                        MaterialProgressRow("Textile & Fiber Waste", "460 Tons", 0.30f, ElectricPurpleLight, isDarkTheme)
                    }
                    StatType.MSME_PARTNERS -> {
                        Text(
                            text = "Recently Verified MSME Offtakers",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        PartnerRow("Apex Precision Forgings", "Tier-2 Automotive Offtaker", "Pune", isDarkTheme)
                        PartnerRow("EcoPolymer Solutions", "Extrusion Compounder", "Bengaluru", isDarkTheme)
                        PartnerRow("Shree Balaji Foundry Works", "Grey Iron Casting Unit", "Coimbatore", isDarkTheme)
                        PartnerRow("Kaveri Biofuels Pvt Ltd", "Briquetting Plant", "Hosur", isDarkTheme)
                    }
                    StatType.SAVINGS -> {
                        Text(
                            text = "Financial Value Generated for Enterprises",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        ClusterRow("Raw Material Substitution", "MSMEs saved on virgin input feedstock", "₹5.2 Lakhs", isDarkTheme)
                        ClusterRow("Landfill & Hazmat Penalty Avoidance", "Avoided state pollution compliance fines", "₹2.1 Lakhs", isDarkTheme)
                        ClusterRow("Logistics Route Optimization", "Shared reverse backhaul truckloads", "₹1.1 Lakhs", isDarkTheme)
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Close Explorer", color = Slate950, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun ClusterRow(name: String, desc: String, stat: String, isDarkTheme: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isDarkTheme) Color(0x500F172A) else Color(0xFFF8FAFC))
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = name, fontFamily = PlusJakartaSans, fontWeight = FontWeight.SemiBold, fontSize = 13.sp, color = if (isDarkTheme) CrispWhite else Slate950)
            Text(text = desc, fontFamily = PlusJakartaSans, fontSize = 11.sp, color = if (isDarkTheme) Slate400 else Color(0xFF64748B))
        }
        Text(text = stat, fontFamily = PlusJakartaSans, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = EmeraldLight)
    }
}

@Composable
private fun PartnerRow(company: String, role: String, location: String, isDarkTheme: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isDarkTheme) Color(0x500F172A) else Color(0xFFF8FAFC))
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = company, fontFamily = PlusJakartaSans, fontWeight = FontWeight.SemiBold, fontSize = 13.sp, color = if (isDarkTheme) CrispWhite else Slate950)
            Text(text = role, fontFamily = PlusJakartaSans, fontSize = 11.sp, color = if (isDarkTheme) Slate400 else Color(0xFF64748B))
        }
        Text(text = location, fontFamily = PlusJakartaSans, fontWeight = FontWeight.Medium, fontSize = 12.sp, color = AmberOrange)
    }
}

@Composable
private fun MaterialProgressRow(name: String, weight: String, fraction: Float, color: Color, isDarkTheme: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = name, fontFamily = PlusJakartaSans, fontWeight = FontWeight.Medium, fontSize = 12.sp, color = if (isDarkTheme) CrispWhite else Slate950)
            Text(text = weight, fontFamily = PlusJakartaSans, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = color)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp))
                .background(if (isDarkTheme) Color(0xFF1E293B) else Color(0xFFE2E8F0))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction)
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(color)
            )
        }
    }
}
