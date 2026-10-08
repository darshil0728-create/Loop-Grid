package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

data class DemoTrade(
    val id: String,
    val material: String,
    val quantity: String,
    val seller: String,
    val buyer: String,
    val distance: String,
    val value: String,
    val status: String
)

@Composable
fun ExploreDemoTour(
    isDarkTheme: Boolean,
    onDismiss: () -> Unit,
    onSimulateTransaction: () -> Unit
) {
    var trades by remember {
        mutableStateOf(
            listOf(
                DemoTrade("#TR-9821", "Cold-Rolled Steel Coils", "18.5 Tons", "Tata AutoComp", "Shree Ganesh Forgings", "14 km (Pune)", "₹7,77,000", "MATCHED"),
                DemoTrade("#TR-9820", "HDPE Polymer Regrind", "8.0 Tons", "PolyTech Blowmolding", "EcoContainers Ltd", "22 km (Bengaluru)", "₹4,16,000", "DISPATCHED"),
                DemoTrade("#TR-9819", "Foundry Slag & Sand", "45.0 Tons", "Bhilai Substation", "Ambuja Road Infra", "38 km (Durg)", "₹1,80,000", "SETTLED")
            )
        )
    }

    var addedTradeNotice by remember { mutableStateOf(false) }

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
                .testTag("demo_tour_dialog")
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
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(EmeraldPrimary.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = Icons.Default.Bolt, contentDescription = null, tint = EmeraldLight, modifier = Modifier.size(20.dp))
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "LIVE FEED MATCHMAKING DEMO",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 16.sp,
                                color = if (isDarkTheme) CrispWhite else Slate950
                            )
                            Text(
                                text = "Real-time circular network telemetry",
                                fontFamily = PlusJakartaSans,
                                fontSize = 12.sp,
                                color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                            )
                        }
                    }

                    IconButton(onClick = onDismiss, modifier = Modifier.testTag("close_demo_tour")) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = if (isDarkTheme) Slate400 else Slate700)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                AnimatedVisibility(visible = addedTradeNotice) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0x3310B981))
                            .border(1.dp, EmeraldPrimary, RoundedCornerShape(8.dp))
                            .padding(10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = EmeraldLight, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "New 12 Ton Copper Swarf feed matched in 3.4 seconds! Live database updated.",
                                fontFamily = PlusJakartaSans,
                                fontSize = 12.sp,
                                color = EmeraldLight
                            )
                        }
                    }
                }

                Text(
                    text = "ACTIVE TRANSACTIONS STREAM",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = EmeraldLight,
                    letterSpacing = 0.5.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                trades.forEach { trade ->
                    TradeCard(trade, isDarkTheme)
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val newTrade = DemoTrade(
                            id = "#TR-${(9822..9999).random()}",
                            material = "Industrial Copper Swarf",
                            quantity = "12.0 Tons",
                            seller = "Bharat Electric Components",
                            buyer = "Deccan Smelters & Alloys",
                            distance = "18 km (Pune MIDC)",
                            value = "₹8,40,000",
                            status = "MATCHED"
                        )
                        trades = listOf(newTrade) + trades
                        addedTradeNotice = true
                        onSimulateTransaction()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("simulate_match_btn"),
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "SIMULATE REAL-TIME MATCH EVENT",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = Slate950
                    )
                }
            }
        }
    }
}

@Composable
private fun TradeCard(trade: DemoTrade, isDarkTheme: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(if (isDarkTheme) Color(0x600F172A) else Color(0xFFF8FAFC))
            .border(1.dp, if (isDarkTheme) Color(0x33334155) else Color(0xFFE2E8F0), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${trade.id} • ${trade.material}",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = if (isDarkTheme) CrispWhite else Slate950
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(
                            when (trade.status) {
                                "MATCHED" -> EmeraldPrimary.copy(alpha = 0.2f)
                                "DISPATCHED" -> AmberOrange.copy(alpha = 0.2f)
                                else -> ElectricPurpleLight.copy(alpha = 0.2f)
                            }
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = trade.status,
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        color = when (trade.status) {
                            "MATCHED" -> EmeraldLight
                            "DISPATCHED" -> AmberOrange
                            else -> ElectricPurpleLight
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${trade.quantity} | ${trade.seller} → ${trade.buyer}",
                fontFamily = PlusJakartaSans,
                fontSize = 12.sp,
                color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Haul: ${trade.distance}",
                    fontFamily = PlusJakartaSans,
                    fontSize = 11.sp,
                    color = if (isDarkTheme) Slate400 else Color(0xFF94A3B8)
                )
                Text(
                    text = trade.value,
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = EmeraldLight
                )
            }
        }
    }
}
