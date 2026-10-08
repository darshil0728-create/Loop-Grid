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
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Science
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
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate950

@Composable
fun ExploreWhatWeDoSheet(
    isDarkTheme: Boolean,
    onDismiss: () -> Unit,
    onRegisterFeedAction: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .widthIn(max = 520.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .border(
                    width = 1.dp,
                    color = if (isDarkTheme) Color(0x66334155) else Color(0xFFCBD5E1),
                    shape = RoundedCornerShape(20.dp)
                )
                .background(if (isDarkTheme) Color(0xF80B1120) else Color.White)
                .padding(24.dp)
                .testTag("explore_what_we_do_sheet")
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
                    Column {
                        Text(
                            text = "WHAT LOOPGRID DOES",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp,
                            color = if (isDarkTheme) CrispWhite else Slate950
                        )
                        Text(
                            text = "Industrial circular network in 4 synchronized steps",
                            fontFamily = PlusJakartaSans,
                            fontSize = 12.sp,
                            color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                        )
                    }

                    IconButton(onClick = onDismiss, modifier = Modifier.testTag("close_explore_sheet")) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = if (isDarkTheme) Slate400 else Slate700
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                StepRow(
                    stepNumber = "01",
                    title = "Feed Intake & Material Fingerprinting",
                    desc = "Factories upload chemical analysis, spectrometry, or batch photos of leftover manufacturing scraps and byproducts.",
                    icon = Icons.Default.Science,
                    accentColor = EmeraldLight,
                    isDarkTheme = isDarkTheme
                )

                Spacer(modifier = Modifier.height(14.dp))

                StepRow(
                    stepNumber = "02",
                    title = "AI Matchmaking Engine",
                    desc = "Real-time algorithmic matching connects surplus scrap with nearby MSMEs and secondary manufacturers needing raw feed.",
                    icon = Icons.Default.Hub,
                    accentColor = EmeraldLight,
                    isDarkTheme = isDarkTheme
                )

                Spacer(modifier = Modifier.height(14.dp))

                StepRow(
                    stepNumber = "03",
                    title = "Verified Reverse Logistics",
                    desc = "Automated fleet dispatch with weighbridge IoT verification, digital manifests, and GPS geo-fenced transport.",
                    icon = Icons.Default.LocalShipping,
                    accentColor = AmberOrange,
                    isDarkTheme = isDarkTheme
                )

                Spacer(modifier = Modifier.height(14.dp))

                StepRow(
                    stepNumber = "04",
                    title = "Instant Settlement & ESG Credits",
                    desc = "Automated escrow payout within 24 hours, alongside certified auditable Scope-3 CO₂ reduction credits.",
                    icon = Icons.Default.Payments,
                    accentColor = ElectricPurpleLight,
                    isDarkTheme = isDarkTheme
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        onDismiss()
                        onRegisterFeedAction()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("explore_register_cta"),
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "REGISTER YOUR FIRST FEED",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Slate950
                    )
                }
            }
        }
    }
}

@Composable
private fun StepRow(
    stepNumber: String,
    title: String,
    desc: String,
    icon: ImageVector,
    accentColor: Color,
    isDarkTheme: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(if (isDarkTheme) Color(0x600F172A) else Color(0xFFF8FAFC))
            .border(1.dp, if (isDarkTheme) Color(0x33334155) else Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
            .padding(14.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(accentColor.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = accentColor,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "$stepNumber • ",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = accentColor
                )
                Text(
                    text = title,
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = if (isDarkTheme) CrispWhite else Slate950
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = desc,
                fontFamily = PlusJakartaSans,
                fontSize = 12.sp,
                lineHeight = 17.sp,
                color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
            )
        }
    }
}
