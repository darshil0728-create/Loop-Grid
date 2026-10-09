package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.DigitalResourcePassport
import com.example.data.DrpStatus
import com.example.ui.theme.CrispWhite
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.LightBorder
import com.example.ui.theme.LightBorderStrong
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
 * Enterprise DRP Section Bottom Sheet:
 * Shows:
 * 1. Current DRPs: Passports currently available and active for the enterprise.
 * 2. DRP History: Outdated DRPs that have completed transactions and settled,
 *    securely kept for full audit history.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterpriseDrpSectionSheet(
    passports: List<DigitalResourcePassport>,
    enterpriseName: String,
    isDarkTheme: Boolean,
    onDismiss: () -> Unit,
    onGenerateNewPassport: () -> Unit,
    initialTab: Int = 0 // 0 = Current DRPs, 1 = DRP History
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var selectedTabIndex by remember { mutableIntStateOf(initialTab) }
    var selectedPassportDetail by remember { mutableStateOf<DigitalResourcePassport?>(null) }

    // Filter to this enterprise
    val enterprisePassports = remember(passports, enterpriseName) {
        passports.filter {
            it.enterpriseName.equals(enterpriseName, ignoreCase = true) ||
                    enterpriseName.isBlank() ||
                    it.enterpriseName == "Shree Balaji Fabrication Works"
        }
    }

    val currentDrps = remember(enterprisePassports) {
        enterprisePassports.filter { !it.status.isOutdated }
    }

    val historyDrps = remember(enterprisePassports) {
        enterprisePassports.filter { it.status.isOutdated }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = if (isDarkTheme) Color(0xFF0A1411) else Color(0xFFFFFFFF),
        dragHandle = null,
        modifier = Modifier.fillMaxHeight(0.92f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .testTag("enterprise_drp_section_sheet")
        ) {
            // Header Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(if (isDarkTheme) Color(0x3310B981) else Color(0x2010B981)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.QrCode2,
                            contentDescription = null,
                            tint = if (isDarkTheme) EmeraldLight else LightEmeraldDark,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Digital Resource Passports",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp,
                            color = if (isDarkTheme) CrispWhite else LightTextHeadline
                        )
                        Text(
                            text = "Current available lots & transacted DRP history",
                            fontFamily = PlusJakartaSans,
                            fontSize = 12.sp,
                            color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.testTag("btn_close_drp_sheet")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Close",
                            tint = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Tabs: Current DRPs vs DRP History
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = Color.Transparent,
                contentColor = if (isDarkTheme) EmeraldLight else LightEmerald,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = if (isDarkTheme) EmeraldPrimary else LightEmerald,
                        height = 3.dp
                    )
                },
                divider = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(if (isDarkTheme) Color(0x33334155) else Color(0xFFE2E8F0))
                    )
                }
            ) {
                Tab(
                    selected = selectedTabIndex == 0,
                    onClick = {
                        selectedTabIndex = 0
                        selectedPassportDetail = null
                    },
                    modifier = Modifier.testTag("tab_current_drps")
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Inventory2,
                            contentDescription = null,
                            tint = if (selectedTabIndex == 0) {
                                if (isDarkTheme) EmeraldLight else LightEmerald
                            } else {
                                if (isDarkTheme) Slate400 else Color(0xFF64748B)
                            },
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Current DRPs (${currentDrps.size})",
                            fontFamily = PlusJakartaSans,
                            fontWeight = if (selectedTabIndex == 0) FontWeight.ExtraBold else FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = if (selectedTabIndex == 0) {
                                if (isDarkTheme) CrispWhite else LightTextHeadline
                            } else {
                                if (isDarkTheme) Slate400 else Color(0xFF64748B)
                            }
                        )
                    }
                }

                Tab(
                    selected = selectedTabIndex == 1,
                    onClick = {
                        selectedTabIndex = 1
                        selectedPassportDetail = null
                    },
                    modifier = Modifier.testTag("tab_drp_history")
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.History,
                            contentDescription = null,
                            tint = if (selectedTabIndex == 1) {
                                if (isDarkTheme) EmeraldLight else LightEmerald
                            } else {
                                if (isDarkTheme) Slate400 else Color(0xFF64748B)
                            },
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "DRP History (${historyDrps.size})",
                            fontFamily = PlusJakartaSans,
                            fontWeight = if (selectedTabIndex == 1) FontWeight.ExtraBold else FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = if (selectedTabIndex == 1) {
                                if (isDarkTheme) CrispWhite else LightTextHeadline
                            } else {
                                if (isDarkTheme) Slate400 else Color(0xFF64748B)
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Detail Card or List
            if (selectedPassportDetail != null) {
                DrpDetailCard(
                    drp = selectedPassportDetail!!,
                    isDarkTheme = isDarkTheme,
                    onBackToList = { selectedPassportDetail = null }
                )
            } else {
                when (selectedTabIndex) {
                    0 -> {
                        // CURRENT DRPS SECTION
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "AVAILABLE IN MARKETPLACE",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 11.sp,
                                letterSpacing = 0.8.sp,
                                color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                            )

                            Button(
                                onClick = onGenerateNewPassport,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isDarkTheme) EmeraldPrimary else LightEmerald
                                ),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .height(32.dp)
                                    .testTag("btn_sheet_generate_drp")
                            ) {
                                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "New Passport",
                                    fontFamily = PlusJakartaSans,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp,
                                    color = if (isDarkTheme) Slate950 else CrispWhite
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        if (currentDrps.isEmpty()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(
                                        imageVector = Icons.Default.QrCode2,
                                        contentDescription = null,
                                        tint = if (isDarkTheme) Color(0x40FFFFFF) else Color(0x40000000),
                                        modifier = Modifier.size(48.dp)
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "No current active DRPs registered",
                                        fontFamily = PlusJakartaSans,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = if (isDarkTheme) CrispWhite else LightTextHeadline
                                    )
                                    Text(
                                        text = "Register a resource lot to generate a new Digital Resource Passport.",
                                        fontFamily = PlusJakartaSans,
                                        fontSize = 12.sp,
                                        color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                                    )
                                }
                            }
                        } else {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                items(currentDrps, key = { it.drpId }) { drp ->
                                    CurrentDrpItemCard(
                                        drp = drp,
                                        isDarkTheme = isDarkTheme,
                                        onClick = { selectedPassportDetail = drp }
                                    )
                                }
                            }
                        }
                    }
                    1 -> {
                        // DRP HISTORY SECTION
                        Column(modifier = Modifier.fillMaxWidth().weight(1f)) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(if (isDarkTheme) Color(0x2238BDF8) else Color(0x150284C7))
                                    .border(1.dp, if (isDarkTheme) Color(0x4038BDF8) else Color(0x330284C7), RoundedCornerShape(10.dp))
                                    .padding(12.dp)
                            ) {
                                Text(
                                    text = "ℹ️ DRPs become outdated after circular transaction & payment settlement occurs. Past passports remain securely archived here in DRP History for traceability & audit.",
                                    fontFamily = PlusJakartaSans,
                                    fontSize = 11.sp,
                                    lineHeight = 16.sp,
                                    color = if (isDarkTheme) Color(0xFF7DD3FC) else Color(0xFF0369A1)
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "SETTLED & OUTDATED DRP ARCHIVE",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 11.sp,
                                letterSpacing = 0.8.sp,
                                color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            if (historyDrps.isEmpty()) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "No completed transactions in history yet.",
                                        fontFamily = PlusJakartaSans,
                                        fontSize = 13.sp,
                                        color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                                    )
                                }
                            } else {
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f),
                                    verticalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    items(historyDrps, key = { it.drpId }) { drp ->
                                        HistoryDrpItemCard(
                                            drp = drp,
                                            isDarkTheme = isDarkTheme,
                                            onClick = { selectedPassportDetail = drp }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CurrentDrpItemCard(
    drp: DigitalResourcePassport,
    isDarkTheme: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .testTag("current_drp_card_${drp.drpId.lowercase()}"),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isDarkTheme) Color(0xFF0F1B16) else LightSurfaceCard
        ),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(
                if (isDarkTheme) Color(0x4010B981) else Color(0x33059669)
            )
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = drp.drpId,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(if (isDarkTheme) Color(0x3310B981) else Color(0x2010B981))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "CURRENTLY AVAILABLE",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 9.sp,
                        color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = drp.materialName,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 15.sp,
                color = if (isDarkTheme) CrispWhite else LightTextHeadline
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Purity / Spec: ${drp.purityGrade}",
                fontFamily = PlusJakartaSans,
                fontSize = 11.sp,
                color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Lot Volume: ${drp.quantityValue} ${drp.quantityUnit}",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = if (isDarkTheme) CrispWhite else LightTextHeadline
                )

                Text(
                    text = "Issued: ${drp.issuanceDate}",
                    fontFamily = PlusJakartaSans,
                    fontSize = 11.sp,
                    color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                )
            }
        }
    }
}

@Composable
private fun HistoryDrpItemCard(
    drp: DigitalResourcePassport,
    isDarkTheme: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .testTag("history_drp_card_${drp.drpId.lowercase()}"),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isDarkTheme) Color(0xFF0F1517) else Color(0xFFF8FAFC)
        ),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(
                if (isDarkTheme) Color(0x33334155) else Color(0xFFCBD5E1)
            )
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = drp.drpId,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(if (isDarkTheme) Color(0x3364748B) else Color(0x1F64748B))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "OUTDATED • TRANSACTED",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 9.sp,
                        color = Color(0xFF94A3B8)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = drp.materialName,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = if (isDarkTheme) CrispWhite else LightTextHeadline
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Offtaker: ${drp.buyerEnterprise ?: "Processing Smelter"}",
                fontFamily = PlusJakartaSans,
                fontSize = 12.sp,
                color = if (isDarkTheme) Color(0xFF60A5FA) else Color(0xFF2563EB)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Transacted: ${drp.quantityValue} ${drp.quantityUnit} (${drp.transactionAmount ?: "Settled"})",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 11.sp,
                    color = if (isDarkTheme) Slate200 else Color(0xFF334155)
                )

                Text(
                    text = "Closed: ${drp.transactedDate ?: "Sep 2026"}",
                    fontFamily = PlusJakartaSans,
                    fontSize = 11.sp,
                    color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                )
            }
        }
    }
}
