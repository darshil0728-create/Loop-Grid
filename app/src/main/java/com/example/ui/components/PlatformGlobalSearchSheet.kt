package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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

enum class SearchFilterScope(val title: String) {
    ALL("All Results"),
    DRP_NUMBER("DRP Passport ID"),
    RESOURCES("Resources & Materials"),
    ENTERPRISES("Enterprises")
}

/**
 * Global Search Bottom Sheet allowing users to search anything on the LoopGrid platform:
 * - Any DRP number (active or past)
 * - Any resource/material being dealt with
 * - Any enterprise registered or dealing on the platform
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlatformGlobalSearchSheet(
    passports: List<DigitalResourcePassport>,
    isDarkTheme: Boolean,
    onDismiss: () -> Unit,
    onSelectDrp: (DigitalResourcePassport) -> Unit = {}
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var searchQuery by remember { mutableStateOf("") }
    var selectedScope by remember { mutableStateOf(SearchFilterScope.ALL) }
    var selectedDrpDetail by remember { mutableStateOf<DigitalResourcePassport?>(null) }

    val filteredPassports = remember(searchQuery, selectedScope, passports) {
        val q = searchQuery.trim().lowercase()
        passports.filter { drp ->
            if (q.isBlank()) {
                true
            } else {
                when (selectedScope) {
                    SearchFilterScope.ALL -> {
                        drp.drpId.lowercase().contains(q) ||
                                drp.materialName.lowercase().contains(q) ||
                                drp.materialCategory.lowercase().contains(q) ||
                                drp.enterpriseName.lowercase().contains(q) ||
                                drp.originDistrict.lowercase().contains(q) ||
                                drp.originState.lowercase().contains(q) ||
                                (drp.buyerEnterprise?.lowercase()?.contains(q) ?: false)
                    }
                    SearchFilterScope.DRP_NUMBER -> drp.drpId.lowercase().contains(q)
                    SearchFilterScope.RESOURCES -> drp.materialName.lowercase().contains(q) || drp.materialCategory.lowercase().contains(q)
                    SearchFilterScope.ENTERPRISES -> drp.enterpriseName.lowercase().contains(q) || (drp.buyerEnterprise?.lowercase()?.contains(q) ?: false)
                }
            }
        }
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
                .testTag("platform_global_search_sheet")
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
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = if (isDarkTheme) EmeraldLight else LightEmeraldDark,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Platform Search",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp,
                            color = if (isDarkTheme) CrispWhite else LightTextHeadline
                        )
                        Text(
                            text = "Search any DRP number, resource, or enterprise",
                            fontFamily = PlusJakartaSans,
                            fontSize = 12.sp,
                            color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                        )
                    }
                }

                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.testTag("btn_close_search_sheet")
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Close",
                        tint = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Search Input Box
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("input_global_search"),
                placeholder = {
                    Text(
                        text = "e.g. DRP-MH-2026-9041, Copper, Balaji, Pune...",
                        fontFamily = PlusJakartaSans,
                        fontSize = 13.sp,
                        color = if (isDarkTheme) Color(0x9994A3B8) else Color(0xFF94A3B8)
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = if (isDarkTheme) EmeraldLight else LightEmerald,
                        modifier = Modifier.size(20.dp)
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotBlank()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear search",
                                tint = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                            )
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = if (isDarkTheme) EmeraldLight else LightEmerald,
                    unfocusedBorderColor = if (isDarkTheme) Color(0x33334155) else LightBorderStrong,
                    focusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
                    unfocusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
                    focusedContainerColor = if (isDarkTheme) Color(0x2E0F172A) else Color(0xFFF8FAFC),
                    unfocusedContainerColor = if (isDarkTheme) Color(0x2E0F172A) else Color(0xFFF8FAFC)
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Filter Scopes (Pills)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchFilterScope.entries.forEach { scope ->
                    val isSelected = selectedScope == scope
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                if (isSelected) {
                                    if (isDarkTheme) EmeraldPrimary else LightEmerald
                                } else {
                                    if (isDarkTheme) Color(0xFF13201B) else Color(0xFFE2E8F0)
                                }
                            )
                            .clickable { selectedScope = scope }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                            .testTag("filter_scope_${scope.name.lowercase()}")
                    ) {
                        Text(
                            text = scope.title,
                            fontFamily = PlusJakartaSans,
                            fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Medium,
                            fontSize = 11.sp,
                            color = if (isSelected) {
                                if (isDarkTheme) Slate950 else CrispWhite
                            } else {
                                if (isDarkTheme) Slate200 else Color(0xFF475569)
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Detailed Modal View if an item is tapped
            if (selectedDrpDetail != null) {
                DrpDetailCard(
                    drp = selectedDrpDetail!!,
                    isDarkTheme = isDarkTheme,
                    onBackToList = { selectedDrpDetail = null }
                )
            } else {
                // Results Count
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "SEARCH RESULTS (${filteredPassports.size})",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 11.sp,
                        letterSpacing = 0.8.sp,
                        color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                    )
                    Text(
                        text = "Tap any card for complete passport audit",
                        fontFamily = PlusJakartaSans,
                        fontSize = 10.sp,
                        color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                if (filteredPassports.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = if (isDarkTheme) Color(0x40FFFFFF) else Color(0x40000000),
                                modifier = Modifier.size(48.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "No platform records match \"$searchQuery\"",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Try searching by DRP ID (e.g. DRP-MH), metal name (e.g. Steel, Zinc), or company name.",
                                fontFamily = PlusJakartaSans,
                                fontSize = 12.sp,
                                color = if (isDarkTheme) Slate400 else Color(0xFF94A3B8)
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
                        items(filteredPassports, key = { it.drpId }) { item ->
                            SearchResultItemCard(
                                drp = item,
                                isDarkTheme = isDarkTheme,
                                onClick = {
                                    selectedDrpDetail = item
                                    onSelectDrp(item)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchResultItemCard(
    drp: DigitalResourcePassport,
    isDarkTheme: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .testTag("search_result_${drp.drpId.lowercase()}"),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isDarkTheme) Color(0xFF101B17) else LightSurfaceCard
        ),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(
                if (isDarkTheme) Color(0x33334155) else Color(0xFFE2E8F0)
            )
        )
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.QrCode2,
                        contentDescription = null,
                        tint = if (isDarkTheme) EmeraldLight else LightEmerald,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = drp.drpId,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = if (isDarkTheme) CrispWhite else LightTextHeadline
                    )
                }

                // Status Badge
                val isOutdated = drp.status.isOutdated
                val badgeColor = if (isOutdated) Color(0xFF94A3B8) else Color(0xFF10B981)
                val badgeBg = if (isOutdated) {
                    if (isDarkTheme) Color(0x3364748B) else Color(0x1F64748B)
                } else {
                    if (isDarkTheme) Color(0x3310B981) else Color(0x2010B981)
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(badgeBg)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = if (isOutdated) "HISTORY • TRANSACTED" else "CURRENT DRP",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 9.sp,
                        color = badgeColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = drp.materialName,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 14.sp,
                color = if (isDarkTheme) CrispWhite else LightTextHeadline
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Business,
                        contentDescription = null,
                        tint = if (isDarkTheme) Slate400 else Color(0xFF64748B),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = drp.enterpriseName,
                        fontFamily = PlusJakartaSans,
                        fontSize = 11.sp,
                        color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                    )
                }

                Text(
                    text = "${drp.quantityValue} ${drp.quantityUnit}",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                )
            }
        }
    }
}

@Composable
fun DrpDetailCard(
    drp: DigitalResourcePassport,
    isDarkTheme: Boolean,
    onBackToList: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(if (isDarkTheme) Color(0xFF0F1A16) else LightSurfaceCard)
            .border(1.dp, if (isDarkTheme) Color(0x4010B981) else Color(0x33059669), RoundedCornerShape(16.dp))
            .padding(18.dp)
            .testTag("drp_detail_view")
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { onBackToList() }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = if (isDarkTheme) EmeraldLight else LightEmeraldDark,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Back to Results",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(if (drp.status.isOutdated) Color(0x3364748B) else Color(0x3310B981))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = drp.status.label,
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    color = if (drp.status.isOutdated) Color(0xFF94A3B8) else Color(0xFF10B981)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = drp.drpId,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
        )

        Text(
            text = drp.materialName,
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 18.sp,
            color = if (isDarkTheme) CrispWhite else LightTextHeadline
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Key Specs
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            DrpInfoRow("Category", drp.materialCategory, isDarkTheme)
            DrpInfoRow("Batch Quantity", "${drp.quantityValue} ${drp.quantityUnit}", isDarkTheme)
            DrpInfoRow("Purity / Grade", drp.purityGrade, isDarkTheme)
            DrpInfoRow("Origin Facility", "${drp.enterpriseName} (${drp.originDistrict}, ${drp.originState})", isDarkTheme)
            DrpInfoRow("Issuance Date", drp.issuanceDate, isDarkTheme)

            if (drp.status.isOutdated) {
                Spacer(modifier = Modifier.height(4.dp))
                DrpInfoRow("Transaction Date", drp.transactedDate ?: "Fulfilled", isDarkTheme)
                DrpInfoRow("Offtake Buyer", drp.buyerEnterprise ?: "Processing Enterprise", isDarkTheme)
                DrpInfoRow("Settlement Value", drp.transactionAmount ?: "Settled", isDarkTheme)
                DrpInfoRow("Transit Partner", drp.transitPartnerAssigned ?: "Direct Dispatch", isDarkTheme)
            }

            if (drp.notes.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                DrpInfoRow("Audit Notes", drp.notes, isDarkTheme)
            }
        }
    }
}

@Composable
private fun DrpInfoRow(label: String, value: String, isDarkTheme: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = label,
            fontFamily = PlusJakartaSans,
            fontSize = 11.sp,
            color = if (isDarkTheme) Slate400 else Color(0xFF64748B),
            modifier = Modifier.weight(0.4f)
        )
        Text(
            text = value,
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.SemiBold,
            fontSize = 11.sp,
            color = if (isDarkTheme) CrispWhite else LightTextHeadline,
            modifier = Modifier.weight(0.6f)
        )
    }
}
