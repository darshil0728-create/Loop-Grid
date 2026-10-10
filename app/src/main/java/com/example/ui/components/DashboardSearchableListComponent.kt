package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.Recycling
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.DigitalResourcePassport
import com.example.data.EnterpriseProfile
import com.example.data.EnterpriseRole
import com.example.ui.theme.CrispWhite
import com.example.ui.theme.DarkSurfaceCard
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
import com.example.ui.theme.Slate300
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate950

/**
 * Filter scopes for the real-time search component.
 */
enum class DashboardSearchFilter(val label: String) {
    ALL("All Matches"),
    RESOURCES("Available Resources"),
    ENTERPRISES("Connected Enterprises")
}

/**
 * Data model for a connected circular enterprise on the platform network.
 */
data class ConnectedEnterpriseInfo(
    val enterpriseName: String,
    val role: EnterpriseRole,
    val location: String,
    val distanceKm: Int,
    val primaryResources: List<String>,
    val verifiedImpactScore: Int,
    val activeListingsCount: Int,
    val phoneContact: String,
    val connectionStatus: String = "Connected Partner"
)

/**
 * Seed data for enterprises active in the industrial cluster.
 */
val SampleConnectedEnterprises = listOf(
    ConnectedEnterpriseInfo(
        enterpriseName = "Maharashtra Metal Recyclers & Smelters",
        role = EnterpriseRole.PROCESSING_ENTERPRISE,
        location = "Chakan MIDC Phase-II, Pune",
        distanceKm = 14,
        primaryResources = listOf("Mild Steel Turning Scrap", "Zinc Ash & Residues", "CRCA Scrap"),
        verifiedImpactScore = 910,
        activeListingsCount = 8,
        phoneContact = "+91 98220 11420"
    ),
    ConnectedEnterpriseInfo(
        enterpriseName = "Apex Green Logistics & Freight",
        role = EnterpriseRole.TRANSIT_PARTNER,
        location = "Bhosari Industrial Hub, Pune",
        distanceKm = 6,
        primaryResources = listOf("Heavy Freight Dispatches", "Weighbridge Slips", "Green Haulage"),
        verifiedImpactScore = 885,
        activeListingsCount = 12,
        phoneContact = "+91 94225 33881"
    ),
    ConnectedEnterpriseInfo(
        enterpriseName = "Gujarat Polymer Converters Pvt Ltd",
        role = EnterpriseRole.PROCESSING_ENTERPRISE,
        location = "Sachin Industrial Area, Surat, Gujarat",
        distanceKm = 320,
        primaryResources = listOf("Recycled Polypropylene Granules (rPP)", "HDPE Scrap"),
        verifiedImpactScore = 840,
        activeListingsCount = 4,
        phoneContact = "+91 98791 22004"
    ),
    ConnectedEnterpriseInfo(
        enterpriseName = "Bangalore Precision Valve Fabricators",
        role = EnterpriseRole.RESOURCE_PROVIDER,
        location = "Peenya Industrial Estate, Bengaluru",
        distanceKm = 840,
        primaryResources = listOf("Brass Honey Scrap", "Copper Wire Swarf", "Bronze Turnings"),
        verifiedImpactScore = 795,
        activeListingsCount = 5,
        phoneContact = "+91 98450 77112"
    ),
    ConnectedEnterpriseInfo(
        enterpriseName = "CircularCredit Financial Settlement Hub",
        role = EnterpriseRole.FINANCIAL_PARTNER,
        location = "Bandra-Kurla Complex (BKC), Mumbai",
        distanceKm = 148,
        primaryResources = listOf("Working Capital Lines", "DRP Escrow Settlement", "Factoring"),
        verifiedImpactScore = 790,
        activeListingsCount = 6,
        phoneContact = "+91 22 6677 8899"
    ),
    ConnectedEnterpriseInfo(
        enterpriseName = "Tata AutoComp Ancillary Cluster",
        role = EnterpriseRole.PROCESSING_ENTERPRISE,
        location = "Talegaon Industrial Corridor, Pune",
        distanceKm = 28,
        primaryResources = listOf("Cold Rolled Steel Trimmings (CRCA)", "Stamping Scrap"),
        verifiedImpactScore = 925,
        activeListingsCount = 9,
        phoneContact = "+91 99230 45670"
    )
)

/**
 * Searchable list component for the enterprise dashboard that enables users
 * to filter through available resources and connected enterprises in real-time.
 */
@Composable
fun DashboardSearchableListComponent(
    passports: List<DigitalResourcePassport>,
    profile: EnterpriseProfile,
    isDarkTheme: Boolean,
    onSelectDrp: (DigitalResourcePassport) -> Unit = {},
    onConnectEnterprise: (ConnectedEnterpriseInfo) -> Unit = {},
    onRegisterNewResource: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf(DashboardSearchFilter.ALL) }
    var selectedCategoryChip by remember { mutableStateOf("All Categories") }

    // Derive available resources from active (non-outdated) DRPs and dealing resources
    val availablePassports = remember(passports) {
        passports.filter { !it.status.isOutdated }
    }

    // Dynamic category list from real data
    val availableCategories = remember(availablePassports) {
        listOf("All Categories") + availablePassports.map { it.materialCategory }.distinct()
    }

    // Real-time filtered resources
    val filteredResources = remember(searchQuery, selectedFilter, selectedCategoryChip, availablePassports) {
        if (selectedFilter == DashboardSearchFilter.ENTERPRISES) {
            emptyList()
        } else {
            val q = searchQuery.trim().lowercase()
            availablePassports.filter { drp ->
                val matchesQuery = q.isBlank() ||
                        drp.materialName.lowercase().contains(q) ||
                        drp.materialCategory.lowercase().contains(q) ||
                        drp.drpId.lowercase().contains(q) ||
                        drp.purityGrade.lowercase().contains(q) ||
                        drp.originDistrict.lowercase().contains(q) ||
                        drp.enterpriseName.lowercase().contains(q)

                val matchesCategory = selectedCategoryChip == "All Categories" ||
                        drp.materialCategory.equals(selectedCategoryChip, ignoreCase = true)

                matchesQuery && matchesCategory
            }
        }
    }

    // Real-time filtered connected enterprises
    val filteredEnterprises = remember(searchQuery, selectedFilter) {
        if (selectedFilter == DashboardSearchFilter.RESOURCES) {
            emptyList()
        } else {
            val q = searchQuery.trim().lowercase()
            SampleConnectedEnterprises.filter { ent ->
                q.isBlank() ||
                        ent.enterpriseName.lowercase().contains(q) ||
                        ent.location.lowercase().contains(q) ||
                        ent.role.shortTitle.lowercase().contains(q) ||
                        ent.primaryResources.any { it.lowercase().contains(q) }
            }
        }
    }

    val totalMatches = filteredResources.size + filteredEnterprises.size

    Box(
        modifier = modifier
            .widthIn(max = 840.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .border(
                1.dp,
                if (isDarkTheme) Color(0xFF334155) else LightBorder,
                RoundedCornerShape(20.dp)
            )
            .background(if (isDarkTheme) DarkSurfaceCard else LightSurfaceCard)
            .padding(20.dp)
            .testTag("dashboard_searchable_list_container")
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Header with title and live count pill
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
                            tint = if (isDarkTheme) EmeraldLight else LightEmerald,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "REAL-TIME EXPLORER",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 11.sp,
                                letterSpacing = 1.0.sp,
                                color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                            )

                            Spacer(modifier = Modifier.width(6.dp))

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(if (isDarkTheme) Color(0x3310B981) else Color(0x1F059669))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "$totalMatches found",
                                    fontFamily = PlusJakartaSans,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 9.sp,
                                    color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                                )
                            }
                        }

                        Text(
                            text = "Available Resources & Connected Enterprises",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp,
                            color = if (isDarkTheme) CrispWhite else LightTextHeadline
                        )
                    }
                }

                Button(
                    onClick = onRegisterNewResource,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isDarkTheme) EmeraldPrimary else LightEmerald
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.testTag("btn_list_leftover_material_header")
                ) {
                    Icon(
                        imageVector = Icons.Default.Recycling,
                        contentDescription = null,
                        tint = if (isDarkTheme) Slate950 else CrispWhite,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "+ List Leftover",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = if (isDarkTheme) Slate950 else CrispWhite
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Real-time Search Input Box
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("input_dashboard_search"),
                placeholder = {
                    Text(
                        text = "Search by resource name, metal, category, enterprise or location...",
                        fontFamily = PlusJakartaSans,
                        fontSize = 13.sp,
                        color = if (isDarkTheme) Slate400 else Color(0xFF94A3B8)
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
                    unfocusedBorderColor = if (isDarkTheme) Color(0xFF334155) else Color(0xFFCBD5E1),
                    focusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
                    unfocusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
                    focusedContainerColor = if (isDarkTheme) Color(0xFF0F172A) else Color(0xFFF8FAFC),
                    unfocusedContainerColor = if (isDarkTheme) Color(0xFF0F172A) else Color(0xFFF8FAFC)
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Segmented Scope Filter Tabs (All / Resources / Enterprises)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                DashboardSearchFilter.entries.forEach { filter ->
                    val isSelected = selectedFilter == filter
                    val count = when (filter) {
                        DashboardSearchFilter.ALL -> availablePassports.size + SampleConnectedEnterprises.size
                        DashboardSearchFilter.RESOURCES -> availablePassports.size
                        DashboardSearchFilter.ENTERPRISES -> SampleConnectedEnterprises.size
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                if (isSelected) {
                                    if (isDarkTheme) EmeraldPrimary else LightEmerald
                                } else {
                                    if (isDarkTheme) Color(0xFF0F172A) else Color(0xFFE2E8F0)
                                }
                            )
                            .border(
                                1.dp,
                                if (isSelected) Color.Transparent else if (isDarkTheme) Color(0xFF334155) else Color(0xFFCBD5E1),
                                RoundedCornerShape(8.dp)
                            )
                            .clickable { selectedFilter = filter }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                            .testTag("filter_tab_${filter.name.lowercase()}")
                    ) {
                        Text(
                            text = "${filter.label} ($count)",
                            fontFamily = PlusJakartaSans,
                            fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.SemiBold,
                            fontSize = 11.sp,
                            color = if (isSelected) {
                                if (isDarkTheme) Slate950 else CrispWhite
                            } else {
                                if (isDarkTheme) Slate300 else Color(0xFF475569)
                            }
                        )
                    }
                }
            }

            // Category Chips if searching resources or all
            if (selectedFilter != DashboardSearchFilter.ENTERPRISES && availableCategories.size > 2) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    availableCategories.forEach { category ->
                        val isCatSelected = selectedCategoryChip == category
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(
                                    if (isCatSelected) {
                                        if (isDarkTheme) Color(0x3310B981) else Color(0x20059669)
                                    } else {
                                        Color.Transparent
                                    }
                                )
                                .border(
                                    1.dp,
                                    if (isCatSelected) {
                                        if (isDarkTheme) EmeraldLight else LightEmerald
                                    } else {
                                        if (isDarkTheme) Color(0x33334155) else Color(0xFFE2E8F0)
                                    },
                                    RoundedCornerShape(6.dp)
                                )
                                .clickable { selectedCategoryChip = category }
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = category,
                                fontFamily = PlusJakartaSans,
                                fontWeight = if (isCatSelected) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 10.sp,
                                color = if (isCatSelected) {
                                    if (isDarkTheme) EmeraldLight else LightEmeraldDark
                                } else {
                                    if (isDarkTheme) Slate400 else Color(0xFF64748B)
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Results Section
            if (totalMatches == 0) {
                // Empty state
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isDarkTheme) Color(0xFF0F172A) else Color(0xFFF8FAFC))
                        .border(1.dp, if (isDarkTheme) Color(0xFF334155) else Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = if (isDarkTheme) Slate400 else Color(0xFF94A3B8),
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "No matching resources or enterprises found",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = if (isDarkTheme) CrispWhite else LightTextHeadline
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Try adjusting your search terms or clear the filter to see all active listings.",
                            fontFamily = PlusJakartaSans,
                            fontSize = 12.sp,
                            color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        OutlinedButton(
                            onClick = {
                                searchQuery = ""
                                selectedFilter = DashboardSearchFilter.ALL
                                selectedCategoryChip = "All Categories"
                            },
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Reset Search Filters", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    // 1. Available Resources Section
                    if (filteredResources.isNotEmpty()) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Recycling,
                                        contentDescription = null,
                                        tint = if (isDarkTheme) EmeraldLight else LightEmerald,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "AVAILABLE RESOURCES & DRPs (${filteredResources.size})",
                                        fontFamily = PlusJakartaSans,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 11.sp,
                                        letterSpacing = 0.8.sp,
                                        color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                                    )
                                }
                            }

                            filteredResources.forEach { drp ->
                                ResourceItemCard(
                                    drp = drp,
                                    isDarkTheme = isDarkTheme,
                                    onClick = { onSelectDrp(drp) }
                                )
                            }
                        }
                    }

                    // 2. Connected Enterprises Section
                    if (filteredEnterprises.isNotEmpty()) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Business,
                                        contentDescription = null,
                                        tint = if (isDarkTheme) Color(0xFF60A5FA) else Color(0xFF2563EB),
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "CONNECTED ENTERPRISES (${filteredEnterprises.size})",
                                        fontFamily = PlusJakartaSans,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 11.sp,
                                        letterSpacing = 0.8.sp,
                                        color = if (isDarkTheme) Color(0xFF60A5FA) else Color(0xFF2563EB)
                                    )
                                }
                            }

                            filteredEnterprises.forEach { enterprise ->
                                ConnectedEnterpriseCard(
                                    enterprise = enterprise,
                                    isDarkTheme = isDarkTheme,
                                    onConnect = { onConnectEnterprise(enterprise) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Individual card displaying a filtered available resource with DRP metadata.
 */
@Composable
private fun ResourceItemCard(
    drp: DigitalResourcePassport,
    isDarkTheme: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(
                1.dp,
                if (isDarkTheme) Color(0xFF334155) else Color(0xFFE2E8F0),
                RoundedCornerShape(12.dp)
            )
            .background(if (isDarkTheme) Color(0xFF0F172A) else Color(0xFFF8FAFC))
            .clickable { onClick() }
            .padding(14.dp)
            .testTag("resource_card_${drp.drpId.lowercase()}")
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(if (isDarkTheme) Color(0x3310B981) else Color(0x1F059669))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = drp.drpId,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(if (isDarkTheme) Color(0x22334155) else Color(0xFFE2E8F0))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = drp.materialCategory,
                                fontFamily = PlusJakartaSans,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = if (isDarkTheme) Slate300 else Color(0xFF475569)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = drp.materialName,
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = if (isDarkTheme) CrispWhite else LightTextHeadline
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Business,
                            contentDescription = null,
                            tint = if (isDarkTheme) Slate400 else Color(0xFF64748B),
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = drp.enterpriseName,
                            fontFamily = PlusJakartaSans,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = if (isDarkTheme) Slate300 else Color(0xFF475569)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = if (isDarkTheme) Slate400 else Color(0xFF64748B),
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${drp.originDistrict}, ${drp.originState}",
                            fontFamily = PlusJakartaSans,
                            fontSize = 11.sp,
                            color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                        )
                    }
                }

                // Volume & Status Chip
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "${drp.quantityValue} ${drp.quantityUnit}",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 14.sp,
                        color = if (isDarkTheme) EmeraldLight else LightEmerald
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0x2210B981))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "AVAILABLE",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.Bold,
                            fontSize = 9.sp,
                            color = Color(0xFF10B981)
                        )
                    }
                }
            }
        }
    }
}

/**
 * Individual card displaying a connected enterprise in the cluster network.
 */
@Composable
private fun ConnectedEnterpriseCard(
    enterprise: ConnectedEnterpriseInfo,
    isDarkTheme: Boolean,
    onConnect: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(
                1.dp,
                if (isDarkTheme) Color(0xFF334155) else Color(0xFFE2E8F0),
                RoundedCornerShape(12.dp)
            )
            .background(if (isDarkTheme) Color(0xFF0F172A) else Color(0xFFF8FAFC))
            .padding(14.dp)
            .testTag("enterprise_card_${enterprise.enterpriseName.lowercase().take(15)}")
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(if (isDarkTheme) Color(0x2E3B82F6) else Color(0x1F2563EB))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = enterprise.role.shortTitle,
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = if (isDarkTheme) Color(0xFF60A5FA) else Color(0xFF2563EB)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(if (isDarkTheme) Color(0x3310B981) else Color(0x1F059669))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Verified,
                                    contentDescription = null,
                                    tint = if (isDarkTheme) EmeraldLight else LightEmeraldDark,
                                    modifier = Modifier.size(11.dp)
                                )
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = "Score ${enterprise.verifiedImpactScore}",
                                    fontFamily = PlusJakartaSans,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 9.sp,
                                    color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = enterprise.enterpriseName,
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = if (isDarkTheme) CrispWhite else LightTextHeadline
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = if (isDarkTheme) Slate400 else Color(0xFF64748B),
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${enterprise.location} (${enterprise.distanceKm} km away)",
                            fontFamily = PlusJakartaSans,
                            fontSize = 11.sp,
                            color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Primary resources dealt with
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        enterprise.primaryResources.forEach { res ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(if (isDarkTheme) Color(0x22334155) else Color(0xFFE2E8F0))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = res,
                                    fontFamily = PlusJakartaSans,
                                    fontSize = 9.sp,
                                    color = if (isDarkTheme) Slate300 else Color(0xFF475569)
                                )
                            }
                        }
                    }
                }

                // Connect Action Button
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    OutlinedButton(
                        onClick = onConnect,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.testTag("btn_connect_${enterprise.enterpriseName.take(8).lowercase()}")
                    ) {
                        Text(
                            text = "Connect",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "${enterprise.activeListingsCount} listings",
                        fontFamily = PlusJakartaSans,
                        fontSize = 10.sp,
                        color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                    )
                }
            }
        }
    }
}
