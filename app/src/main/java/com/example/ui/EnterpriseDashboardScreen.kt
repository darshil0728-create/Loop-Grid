package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddBusiness
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Factory
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.data.DashboardNotification
import com.example.data.DigitalResourcePassport
import com.example.data.DrpStatus
import com.example.data.EnterpriseProfile
import com.example.data.EnterpriseRole
import com.example.data.NotificationCategory
import com.example.data.PopularIndustrialResources
import com.example.data.getInitialDashboardNotifications
import com.example.data.getInitialEnterpriseDrps
import com.example.ui.components.ActionNeededNearbySection
import com.example.ui.components.CommandCenterHeroCard
import com.example.ui.components.DashboardLeftNavDrawer
import com.example.ui.components.DashboardNavTab
import com.example.ui.components.DashboardNotificationsSheet
import com.example.ui.components.DashboardSearchableListComponent
import com.example.ui.components.EnterpriseDrpSectionSheet
import com.example.ui.components.FinancialPartnerCreditDesk
import com.example.ui.components.ImpactOverviewRow
import com.example.ui.components.NextBestActionCard
import com.example.ui.components.PlatformGlobalSearchSheet
import com.example.ui.components.RecentRecoveriesCard
import com.example.ui.components.RegisterFeedDialog
import com.example.ui.components.SurveillancePipelineHealth
import java.util.UUID
import com.example.ui.theme.CrispWhite
import com.example.ui.theme.DarkBgBase
import com.example.ui.theme.DarkSurfaceCard
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.LightBgBase
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
import kotlinx.coroutines.launch

/**
 * Enterprise Dashboard:
 * 1. Overview of the enterprise profile
 * 2. "Complete Your Profile" section with:
 *    - Resources dealing in
 *    - Address
 *    - Registered Office
 *    - Branches (if any)
 *    - Other related information (GSTIN, Udyam, annual volume, SPCB consent, sustainability goals)
 *    - "Save Draft" option
 *    - "Edit whenever they want" capability
 * 3. Quick action to register by-product resources & Digital Resource Passports
 */
@Composable
private fun DashboardIdentityCard(
    profile: EnterpriseProfile,
    isDarkTheme: Boolean,
    completionPercent: Int,
    onRegisterFeedClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .widthIn(max = 840.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, if (isDarkTheme) Color(0x33334155) else Color(0xFFE2E8F0), RoundedCornerShape(20.dp))
            .background(if (isDarkTheme) DarkSurfaceCard else LightSurfaceCard)
            .padding(22.dp)
            .testTag("dashboard_identity_card")
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = profile.enterpriseName.ifBlank { "Registered Enterprise" },
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 22.sp,
                        color = if (isDarkTheme) CrispWhite else LightTextHeadline
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Role pill and category badges neatly placed below the Enterprise Name
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (isDarkTheme) Color(0x2E10B981) else Color(0x1F059669))
                                .border(1.dp, if (isDarkTheme) Color(0x5510B981) else Color(0x4D059669), RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = profile.role.shortTitle,
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (isDarkTheme) Color(0x22334155) else Color(0xFFF1F5F9))
                                .border(1.dp, if (isDarkTheme) Color(0x33475569) else Color(0xFFCBD5E1), RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = profile.orgType,
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 11.sp,
                                color = if (isDarkTheme) Slate300 else Color(0xFF475569)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (isDarkTheme) Color(0x263B82F6) else Color(0x1F2563EB))
                                .border(1.dp, if (isDarkTheme) Color(0x4D3B82F6) else Color(0x4D2563EB), RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = profile.orgNature,
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                color = if (isDarkTheme) Color(0xFF60A5FA) else Color(0xFF2563EB)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = if (isDarkTheme) EmeraldLight else LightEmerald,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Owner: ${profile.ownerName.ifBlank { "Not Specified" }}",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = if (isDarkTheme) CrispWhite else LightTextHeadline
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = null,
                            tint = if (isDarkTheme) EmeraldLight else LightEmerald,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = profile.phone.ifBlank { "+91..." },
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = if (isDarkTheme) Color(0xFFE2E8F0) else Color(0xFF475569)
                        )
                    }
                }

                // Register Feed Action
                Button(
                    onClick = onRegisterFeedClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isDarkTheme) EmeraldPrimary else LightEmerald
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.testTag("btn_dashboard_register_feed")
                ) {
                    Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Register Feed",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = if (isDarkTheme) Slate950 else CrispWhite
                    )
                }
            }
        }
    }
}

@Composable
private fun DashboardFeedsSummarySection(
    passports: List<DigitalResourcePassport>,
    profile: EnterpriseProfile,
    isDarkTheme: Boolean,
    onRegisterNewFeed: () -> Unit,
    onOpenDrpSection: (initialTab: Int) -> Unit
) {
    val currentDrps = passports.filter { !it.status.isOutdated }
    val historyDrps = passports.filter { it.status.isOutdated }

    Column(
        modifier = Modifier
            .widthIn(max = 840.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "ACTIVE RESOURCE FEEDS & PASSPORTS",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    letterSpacing = 1.0.sp,
                    color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                )
                Text(
                    text = "Tracked Circular Streams",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    color = if (isDarkTheme) CrispWhite else LightTextHeadline
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(
                    onClick = { onOpenDrpSection(1) },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.testTag("btn_view_drp_history")
                ) {
                    Text("History (${historyDrps.size})", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = onRegisterNewFeed,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isDarkTheme) EmeraldPrimary else LightEmerald
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.testTag("btn_dashboard_generate_drp")
                ) {
                    Icon(Icons.Default.QrCode2, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "New Passport",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isDarkTheme) Slate950 else CrispWhite
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Display up to 2 active passport cards with click to open full DRP sheet
        val displayDrps = currentDrps.take(2)
        if (displayDrps.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .border(1.dp, if (isDarkTheme) Color(0x33334155) else Color(0xFFE2E8F0), RoundedCornerShape(14.dp))
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No current DRPs. Register a resource feed to generate your first passport.",
                    fontFamily = PlusJakartaSans,
                    fontSize = 13.sp,
                    color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                )
            }
        } else {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                displayDrps.forEach { item ->
                    FeedPassportCard(
                        drpId = item.drpId,
                        material = item.materialName,
                        volume = "${item.quantityValue} ${item.quantityUnit}",
                        status = item.status.label,
                        statusColor = Color(0xFF10B981),
                        isDarkTheme = isDarkTheme,
                        onClick = { onOpenDrpSection(0) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun FeedPassportCard(
    drpId: String,
    material: String,
    volume: String,
    status: String,
    statusColor: Color,
    isDarkTheme: Boolean,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .border(1.dp, if (isDarkTheme) Color(0x33334155) else Color(0xFFE2E8F0), RoundedCornerShape(14.dp))
            .background(if (isDarkTheme) DarkSurfaceCard else Color.White)
            .clickable { onClick() }
            .padding(16.dp)
            .testTag("feed_card_${drpId.lowercase()}")
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = drpId,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(statusColor.copy(alpha = 0.15f))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = status,
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        color = statusColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = material,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = if (isDarkTheme) CrispWhite else LightTextHeadline
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Volume: $volume",
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
            )
        }
    }
}

@Composable
private fun SectionSubhead(title: String, subtitle: String, isDarkTheme: Boolean) {
    Text(
        text = title,
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        color = if (isDarkTheme) CrispWhite else LightTextHeadline
    )
    Spacer(modifier = Modifier.height(2.dp))
    Text(
        text = subtitle,
        fontFamily = PlusJakartaSans,
        fontSize = 12.sp,
        color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
    )
}

@Composable
private fun FormLabel(label: String, isDarkTheme: Boolean) {
    Text(
        text = label,
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        color = if (isDarkTheme) CrispWhite else LightTextHeadline
    )
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
private fun dashboardTextFieldColors(isDarkTheme: Boolean) = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = if (isDarkTheme) EmeraldLight else LightEmerald,
    unfocusedBorderColor = if (isDarkTheme) Color(0xFF334155) else Color(0xFFCBD5E1),
    focusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
    unfocusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
    disabledTextColor = if (isDarkTheme) Color(0xD9CBD5E1) else LightTextHeadline,
    focusedContainerColor = if (isDarkTheme) Color(0xFF1E293B) else Color(0xFFF8FAFC),
    unfocusedContainerColor = if (isDarkTheme) Color(0xFF162032) else Color(0xFFF8FAFC),
    disabledContainerColor = if (isDarkTheme) Color(0xFF0F172A) else Color(0xFFF1F5F9)
)

private data class AlertVisuals(
    val accent: Color,
    val bg: Color,
    val icon: ImageVector,
    val tag: String
)

@Composable
private fun DashboardLiveAlertBanner(
    latestAlert: DashboardNotification?,
    totalUnreadCount: Int,
    isDarkTheme: Boolean,
    onOpenAllNotifications: () -> Unit,
    onActionClick: (DashboardNotification) -> Unit,
    onDismiss: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (latestAlert == null) {
        // Clean status strip when all alerts are caught up
        Box(
            modifier = modifier
                .widthIn(max = 840.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(if (isDarkTheme) Color(0x2210B981) else Color(0x15059669))
                .border(1.dp, if (isDarkTheme) Color(0x3310B981) else Color(0x2E059669), RoundedCornerShape(12.dp))
                .clickable { onOpenAllNotifications() }
                .padding(horizontal = 14.dp, vertical = 10.dp)
                .testTag("dashboard_all_clear_status")
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = if (isDarkTheme) EmeraldLight else LightEmerald,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Matching engine active • Passports & records up-to-date",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                    )
                }

                Text(
                    text = "View Alerts →",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = if (isDarkTheme) CrispWhite else LightTextHeadline
                )
            }
        }
        return
    }

    val visuals = when (latestAlert.category) {
        NotificationCategory.MATERIAL_MATCH -> AlertVisuals(
            accent = Color(0xFF10B981),
            bg = if (isDarkTheme) Color(0xFF132A24) else Color(0xFFF0FDF4),
            icon = Icons.Default.Handshake,
            tag = "MATERIAL MATCH FOUND"
        )
        NotificationCategory.DRP_ACTION -> AlertVisuals(
            accent = Color(0xFFF59E0B),
            bg = if (isDarkTheme) Color(0xFF292212) else Color(0xFFFFFBEB),
            icon = Icons.Default.QrCode2,
            tag = "DRP ACTION REQUIRED"
        )
        NotificationCategory.PROFILE_RESOURCE -> AlertVisuals(
            accent = Color(0xFF8B5CF6),
            bg = if (isDarkTheme) Color(0xFF241C38) else Color(0xFFFAF5FF),
            icon = Icons.Default.Settings,
            tag = "PROFILE & RESOURCE UPDATE"
        )
        NotificationCategory.ALL -> AlertVisuals(
            accent = Color(0xFF3B82F6),
            bg = if (isDarkTheme) Color(0xFF19263D) else Color(0xFFEFF6FF),
            icon = Icons.Default.Notifications,
            tag = "ALERT"
        )
    }

    Box(
        modifier = modifier
            .widthIn(max = 840.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(
                1.dp,
                visuals.accent.copy(alpha = if (isDarkTheme) 0.6f else 0.8f),
                RoundedCornerShape(16.dp)
            )
            .background(visuals.bg)
            .padding(16.dp)
            .testTag("dashboard_live_alert_banner")
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(visuals.accent.copy(alpha = 0.2f))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = visuals.icon,
                                contentDescription = null,
                                tint = visuals.accent,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = visuals.tag,
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 10.sp,
                                color = visuals.accent,
                                letterSpacing = 0.5.sp
                            )
                        }
                    }

                    if (latestAlert.isUrgent) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color(0x33EF4444))
                                .padding(horizontal = 6.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "HIGH PRIORITY",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 9.sp,
                                color = Color(0xFFEF4444)
                            )
                        }
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = latestAlert.timestamp,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(
                        onClick = { onDismiss(latestAlert.id) },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Dismiss",
                            tint = if (isDarkTheme) Slate400 else Color(0xFF94A3B8),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = latestAlert.title,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 15.sp,
                color = if (isDarkTheme) CrispWhite else LightTextHeadline
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = latestAlert.description,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                lineHeight = 17.sp,
                color = if (isDarkTheme) Color(0xCCF1F5F9) else Color(0xFF334155)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Secondary action: Open all notifications
                Text(
                    text = "View All Alerts ($totalUnreadCount unread) →",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = visuals.accent,
                    modifier = Modifier
                        .clickable { onOpenAllNotifications() }
                        .padding(vertical = 4.dp)
                )

                if (latestAlert.actionLabel != null) {
                    Button(
                        onClick = { onActionClick(latestAlert) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = visuals.accent,
                            contentColor = if (latestAlert.category == NotificationCategory.MATERIAL_MATCH) Slate950 else CrispWhite
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.height(34.dp)
                    ) {
                        Text(
                            text = latestAlert.actionLabel,
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EnterpriseDashboardScreen(
    initialProfile: EnterpriseProfile,
    isDarkTheme: Boolean,
    onSaveProfile: (profile: EnterpriseProfile, isDraft: Boolean) -> Unit,
    onLogout: () -> Unit,
    onBackToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onBackToHome() }

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var currentNavTab by remember { mutableStateOf(DashboardNavTab.HOME) }

    // Editable form state initialized from repository profile
    var profileState by remember { mutableStateOf(initialProfile) }
    var isEditingProfile by remember { mutableStateOf(initialProfile.isDraft || initialProfile.calculateCompletionPercent() < 80) }

    // Draft / Extended fields state
    var selectedResources by remember { mutableStateOf(initialProfile.dealingResources.toSet()) }
    var customResourceInput by remember { mutableStateOf("") }
    var addressInput by remember { mutableStateOf(initialProfile.address) }
    var registeredOfficeInput by remember { mutableStateOf(initialProfile.registeredOffice) }
    var branchesList by remember { mutableStateOf(initialProfile.branches) }
    var newBranchInput by remember { mutableStateOf("") }
    var gstinInput by remember { mutableStateOf(initialProfile.gstin) }
    var udyamInput by remember { mutableStateOf(initialProfile.udyamNumber) }
    var annualVolumeInput by remember { mutableStateOf(initialProfile.annualVolumeTons) }
    var pollutionStatusInput by remember { mutableStateOf(initialProfile.pollutionClearanceStatus) }
    var sustainabilityGoalsInput by remember { mutableStateOf(initialProfile.sustainabilityGoals) }

    // Register feed dialog state
    var showRegisterDialog by remember { mutableStateOf(false) }

    // Notification System State
    var notificationsList by remember { mutableStateOf(getInitialDashboardNotifications()) }
    var showNotificationsSheet by remember { mutableStateOf(false) }

    // Platform DRP Passports & Search System State
    var enterpriseDrpsList by remember { mutableStateOf(getInitialEnterpriseDrps()) }
    var showGlobalSearchSheet by remember { mutableStateOf(false) }
    var showDrpSectionSheet by remember { mutableStateOf(false) }
    var drpSectionInitialTab by remember { mutableIntStateOf(0) } // 0 = Current DRPs, 1 = DRP History

    fun addNotification(
        title: String,
        description: String,
        category: NotificationCategory,
        isUrgent: Boolean = false,
        actionLabel: String? = null
    ) {
        val newNotif = DashboardNotification(
            id = UUID.randomUUID().toString(),
            title = title,
            description = description,
            category = category,
            timestamp = "Just now",
            isRead = false,
            actionLabel = actionLabel,
            isUrgent = isUrgent
        )
        notificationsList = listOf(newNotif) + notificationsList
    }

    // Helper to build profile from current inputs
    fun buildCurrentProfile(isDraft: Boolean): EnterpriseProfile {
        return profileState.copy(
            dealingResources = selectedResources.toList(),
            address = addressInput.trim(),
            registeredOffice = registeredOfficeInput.trim(),
            branches = branchesList,
            gstin = gstinInput.trim(),
            udyamNumber = udyamInput.trim(),
            annualVolumeTons = annualVolumeInput.trim(),
            pollutionClearanceStatus = pollutionStatusInput.trim(),
            sustainabilityGoals = sustainabilityGoalsInput.trim(),
            isDraft = isDraft
        )
    }

    val completionPercent = remember(selectedResources, addressInput, registeredOfficeInput, branchesList, gstinInput, annualVolumeInput, sustainabilityGoalsInput) {
        buildCurrentProfile(profileState.isDraft).calculateCompletionPercent()
    }

    val bgBrush = Brush.verticalGradient(
        colors = if (isDarkTheme) {
            listOf(Color(0xFF0F172A), Color(0xFF131D31), Color(0xFF0B1324))
        } else {
            listOf(Color(0xFFF8FAFC), Color(0xFFF1F5F9), Color(0xFFF0FDF4))
        }
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DashboardLeftNavDrawer(
                currentTab = currentNavTab,
                onTabSelected = { tab ->
                    currentNavTab = tab
                    coroutineScope.launch { drawerState.close() }
                    when (tab) {
                        DashboardNavTab.PROFILE -> {
                            isEditingProfile = true
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Opening Enterprise Profile: edit details, branches & resources anytime.")
                            }
                        }
                        DashboardNavTab.LOGOUT -> {
                            onLogout()
                        }
                        DashboardNavTab.NOTIFICATIONS -> {
                            showNotificationsSheet = true
                        }
                        DashboardNavTab.SEARCH -> {
                            showGlobalSearchSheet = true
                        }
                        DashboardNavTab.CURRENT_DRP -> {
                            drpSectionInitialTab = 0
                            showDrpSectionSheet = true
                        }
                        DashboardNavTab.DRP_HISTORY -> {
                            drpSectionInitialTab = 1
                            showDrpSectionSheet = true
                        }
                        DashboardNavTab.REPORT -> {
                            showRegisterDialog = true
                        }
                        DashboardNavTab.EXPLORE -> {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Scanning active circular offtakers and MSME buyer clusters.")
                            }
                        }
                        DashboardNavTab.MISSIONS -> {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Viewing nearby cleanup and transit missions.")
                            }
                        }
                        DashboardNavTab.MONITORING -> {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("SPCB CTO & ESG compliance tracking healthy.")
                            }
                        }
                        DashboardNavTab.LEADERBOARD -> {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Cluster Leaderboard: Rank #4 in Maharashtra industrial cluster.")
                            }
                        }
                        DashboardNavTab.ABOUT -> {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("LoopGrid: Making sustainability practical and profitable for India's MSMEs.")
                            }
                        }
                        DashboardNavTab.RECOVERY -> {
                            if (profileState.role == EnterpriseRole.FINANCIAL_PARTNER) {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Working Capital Credit & Escrow Settlement Desk.")
                                }
                            } else {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Scrap recovery and smelter conversion active.")
                                }
                            }
                        }
                        DashboardNavTab.HOME -> { /* Main Command Center */ }
                    }
                },
                profile = profileState,
                unreadNotificationCount = notificationsList.count { !it.isRead },
                isDarkTheme = isDarkTheme,
                onToggleTheme = { /* Toggle theme handled */ },
                onCloseDrawer = { coroutineScope.launch { drawerState.close() } }
            )
        }
    ) {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            containerColor = if (isDarkTheme) DarkBgBase else LightBgBase,
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(bgBrush)
                    .statusBarsPadding(),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 1. Dashboard Top Bar (Inspired by Environmental Command Center from Screenshot)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            // Left Nav Center / Drawer Menu Button
                            IconButton(
                                onClick = { coroutineScope.launch { drawerState.open() } },
                                modifier = Modifier.testTag("btn_dashboard_menu")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Open Navigation Menu",
                                    tint = if (isDarkTheme) CrispWhite else LightTextHeadline
                                )
                            }

                            IconButton(
                                onClick = onBackToHome,
                                modifier = Modifier.testTag("btn_dashboard_back")
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back to Home",
                                    tint = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                                )
                            }

                            Spacer(modifier = Modifier.width(4.dp))

                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "COMMAND CENTER",
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
                                            .background(if (isDarkTheme) Color(0x33F59E0B) else Color(0x22F59E0B))
                                            .padding(horizontal = 5.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = "DEMO DATA",
                                            fontFamily = FontFamily.Monospace,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 8.sp,
                                            color = Color(0xFFF59E0B)
                                        )
                                    }
                                }

                                Text(
                                    text = profileState.enterpriseName.ifBlank { "My Enterprise" },
                                    fontFamily = PlusJakartaSans,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 15.sp,
                                    color = if (isDarkTheme) CrispWhite else LightTextHeadline
                                )

                                Text(
                                    text = profileState.role.shortTitle,
                                    fontFamily = PlusJakartaSans,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp,
                                    color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                                )
                            }
                        }

                        // Top Bar Action Buttons: Notifications Bell & Logout
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Notifications button with badge
                            Box(contentAlignment = Alignment.TopEnd) {
                                IconButton(
                                    onClick = { showNotificationsSheet = true },
                                    modifier = Modifier.testTag("btn_dashboard_notifications")
                                ) {
                                    Icon(
                                        imageVector = if (notificationsList.any { !it.isRead }) Icons.Default.NotificationsActive else Icons.Default.Notifications,
                                        contentDescription = "Notifications & Alerts",
                                        tint = if (notificationsList.any { !it.isRead }) {
                                            if (isDarkTheme) EmeraldLight else LightEmerald
                                        } else {
                                            if (isDarkTheme) Slate400 else Color(0xFF64748B)
                                        }
                                    )
                                }

                                val unreadCount = notificationsList.count { !it.isRead }
                                if (unreadCount > 0) {
                                    Box(
                                        modifier = Modifier
                                            .padding(top = 4.dp, end = 4.dp)
                                            .clip(CircleShape)
                                            .background(Color(0xFFEF4444))
                                            .padding(horizontal = 5.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = "$unreadCount",
                                            fontFamily = PlusJakartaSans,
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 10.sp,
                                            color = Color.White
                                        )
                                    }
                                }
                            }

                            IconButton(
                                onClick = onLogout,
                                modifier = Modifier.testTag("btn_dashboard_logout")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Logout,
                                    contentDescription = "Log Out",
                                    tint = if (isDarkTheme) Color(0xFFEF4444) else Color(0xFFDC2626)
                                )
                            }
                        }
                    }

                    // 2. Interactive Role Switcher Bar (lets user test each role's customized dashboard)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 14.dp)
                            .horizontalScroll(rememberScrollState()),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "ROLE VIEW:",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 10.sp,
                            letterSpacing = 0.5.sp,
                            color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                        )

                        EnterpriseRole.entries.forEach { role ->
                            val isCurrent = profileState.role == role
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(
                                        if (isCurrent) {
                                            if (isDarkTheme) EmeraldPrimary else LightEmerald
                                        } else {
                                            if (isDarkTheme) Color(0xFF1E293B) else Color(0xFFE2E8F0)
                                        }
                                    )
                                    .border(
                                        1.dp,
                                        if (isCurrent) Color.Transparent else if (isDarkTheme) Color(0x33334155) else Color(0xFFCBD5E1),
                                        RoundedCornerShape(8.dp)
                                    )
                                    .clickable {
                                        profileState = profileState.copy(role = role)
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar("Switched dashboard to: ${role.title}")
                                        }
                                    }
                                    .padding(horizontal = 10.dp, vertical = 6.dp)
                                    .testTag("btn_role_tab_${role.name.lowercase()}")
                            ) {
                                Text(
                                    text = role.shortTitle,
                                    fontFamily = PlusJakartaSans,
                                    fontWeight = if (isCurrent) FontWeight.ExtraBold else FontWeight.Medium,
                                    fontSize = 11.sp,
                                    color = if (isCurrent) {
                                        if (isDarkTheme) Slate950 else CrispWhite
                                    } else {
                                        if (isDarkTheme) Slate200 else Color(0xFF475569)
                                    }
                                )
                            }
                        }
                    }

                    // Live Notification & Alerts Banner (Material Matches, DRP Checkpoints, Updates)
                    DashboardLiveAlertBanner(
                        latestAlert = notificationsList.firstOrNull { !it.isRead && (it.isUrgent || it.category == NotificationCategory.MATERIAL_MATCH || it.category == NotificationCategory.DRP_ACTION) }
                            ?: notificationsList.firstOrNull { !it.isRead },
                        totalUnreadCount = notificationsList.count { !it.isRead },
                        isDarkTheme = isDarkTheme,
                        onOpenAllNotifications = { showNotificationsSheet = true },
                        onActionClick = { notif ->
                            notificationsList = notificationsList.map {
                                if (it.id == notif.id) it.copy(isRead = true) else it
                            }
                            coroutineScope.launch {
                                when (notif.category) {
                                    NotificationCategory.MATERIAL_MATCH -> {
                                        snackbarHostState.showSnackbar("Opening match offer: ${notif.title}")
                                    }
                                    NotificationCategory.DRP_ACTION -> {
                                        snackbarHostState.showSnackbar("Opening Passport action: ${notif.title}")
                                    }
                                    NotificationCategory.PROFILE_RESOURCE -> {
                                        isEditingProfile = true
                                        snackbarHostState.showSnackbar("Opening details: ${notif.title}")
                                    }
                                    else -> {
                                        snackbarHostState.showSnackbar(notif.title)
                                    }
                                }
                            }
                        },
                        onDismiss = { notifId ->
                            notificationsList = notificationsList.map {
                                if (it.id == notifId) it.copy(isRead = true) else it
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // HERO COMMAND CENTER SECTION (From Reference Image)
                    CommandCenterHeroCard(
                        profile = profileState,
                        isDarkTheme = isDarkTheme,
                        onPrimaryActionClick = {
                            if (profileState.role == EnterpriseRole.FINANCIAL_PARTNER) {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Opening MSME working capital credit facility.")
                                }
                            } else {
                                showRegisterDialog = true
                            }
                        },
                        onSecondaryActionClick = {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Scanning cluster hotspots and active demand.")
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // IMPACT OVERVIEW ROW (4 KPI Cards from Reference Image)
                    ImpactOverviewRow(
                        role = profileState.role,
                        isDarkTheme = isDarkTheme
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // FIELD SURVEILLANCE PIPELINE (5 Stages from Reference Image)
                    SurveillancePipelineHealth(
                        isDarkTheme = isDarkTheme
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // YOUR NEXT BEST ACTION CARD (From Reference Image)
                    NextBestActionCard(
                        role = profileState.role,
                        isDarkTheme = isDarkTheme,
                        onActionClick = {
                            if (profileState.role == EnterpriseRole.FINANCIAL_PARTNER) {
                                addNotification(
                                    title = "Working Capital Line Disbursed",
                                    description = "Disbursed ₹3,50,000 credit line to Shree Balaji Works. Collateral locked in escrow DRP.",
                                    category = NotificationCategory.PROFILE_RESOURCE,
                                    isUrgent = false,
                                    actionLabel = "View Escrow Slip"
                                )
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Approved & disbursed ₹3,50,000 working capital line.")
                                }
                            } else {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Proceeding with next circular action.")
                                }
                            }
                        }
                    )

                    // SPECIALIZED FINANCIAL PARTNER MODULE (when Financial Partner is selected)
                    if (profileState.role == EnterpriseRole.FINANCIAL_PARTNER) {
                        Spacer(modifier = Modifier.height(20.dp))
                        FinancialPartnerCreditDesk(
                            isDarkTheme = isDarkTheme,
                            onApproveCredit = { amount, enterprise ->
                                addNotification(
                                    title = "Working Capital Credit Line Activated",
                                    description = "Disbursed $amount credit line to $enterprise. 30-day term, 1.2% monthly rate, 100% escrow DRP collateral.",
                                    category = NotificationCategory.MATERIAL_MATCH,
                                    isUrgent = false,
                                    actionLabel = "View Escrow Vault"
                                )
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Credit line $amount successfully disbursed to $enterprise!")
                                }
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(22.dp))

                    // ACTION NEEDED NEARBY SECTION (From Reference Image)
                    ActionNeededNearbySection(
                        isDarkTheme = isDarkTheme,
                        onViewAllClick = {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Opening all nearby circular recovery missions.")
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // RECENT RECOVERIES (Before / After from Reference Image)
                    RecentRecoveriesCard(
                        isDarkTheme = isDarkTheme
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // ENTERPRISE IDENTITY OVERVIEW CARD
                    DashboardIdentityCard(
                        profile = profileState,
                        isDarkTheme = isDarkTheme,
                        completionPercent = completionPercent,
                        onRegisterFeedClick = { showRegisterDialog = true }
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                // 3. COMPLETE YOUR PROFILE Section (Form with Save Draft & Edit Anytime)
                Box(
                    modifier = Modifier
                        .widthIn(max = 840.dp)
                        .fillMaxWidth()
                        .shadow(
                            elevation = if (isDarkTheme) 14.dp else 6.dp,
                            shape = RoundedCornerShape(20.dp),
                            ambientColor = if (isDarkTheme) Color(0x50000000) else Color(0x100F172A),
                            spotColor = if (isDarkTheme) Color(0x70000000) else Color(0x150F172A)
                        )
                        .clip(RoundedCornerShape(20.dp))
                        .border(
                            1.dp,
                            if (isDarkTheme) Color(0xFF334155) else LightBorder,
                            RoundedCornerShape(20.dp)
                        )
                        .background(if (isDarkTheme) DarkSurfaceCard else LightSurfaceCard)
                        .padding(24.dp)
                        .testTag("complete_profile_card")
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        // Section Header
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Badge,
                                        contentDescription = null,
                                        tint = if (isDarkTheme) EmeraldLight else LightEmerald,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "Complete Your Profile",
                                        fontFamily = PlusJakartaSans,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 20.sp,
                                        color = if (isDarkTheme) CrispWhite else LightTextHeadline
                                    )
                                }

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "Fill more details about your business to establish verified industrial credentials, branches, and resources.",
                                    fontFamily = PlusJakartaSans,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 13.sp,
                                    lineHeight = 18.sp,
                                    color = if (isDarkTheme) Color(0xBF94A3B8) else Color(0xFF64748B)
                                )
                            }

                            // Toggle Edit / View Button
                            OutlinedButton(
                                onClick = { isEditingProfile = !isEditingProfile },
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.testTag("btn_toggle_edit_profile")
                            ) {
                                Icon(
                                    imageVector = if (isEditingProfile) Icons.Default.Check else Icons.Default.Edit,
                                    contentDescription = null,
                                    tint = if (isDarkTheme) EmeraldLight else LightEmerald,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (isEditingProfile) "Editing" else "Edit Profile",
                                    fontFamily = PlusJakartaSans,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    color = if (isDarkTheme) CrispWhite else LightTextHeadline
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Completion Progress Bar
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Profile Completeness: $completionPercent%",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = if (completionPercent >= 80) {
                                    if (isDarkTheme) EmeraldLight else LightEmeraldDark
                                } else {
                                    Color(0xFFF59E0B)
                                }
                            )

                            Text(
                                text = if (profileState.isDraft) "Status: Draft Saved" else "Status: Verified Profile",
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                color = if (profileState.isDraft) Color(0xFFF59E0B) else Color(0xFF10B981)
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        LinearProgressIndicator(
                            progress = { completionPercent / 100f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp)),
                            color = if (isDarkTheme) EmeraldLight else LightEmerald,
                            trackColor = if (isDarkTheme) Color(0x33334155) else Color(0xFFE2E8F0)
                        )

                        Spacer(modifier = Modifier.height(24.dp))
                        HorizontalDivider(color = if (isDarkTheme) Color(0x1F334155) else Color(0xFFE2E8F0))
                        Spacer(modifier = Modifier.height(20.dp))

                        // A. WHAT RESOURCES ARE THEY DEALING IN?
                        SectionSubhead("A. What resources are you dealing in?", "Select your regular industrial by-products, scrap streams, or feed materials.", isDarkTheme)
                        Spacer(modifier = Modifier.height(10.dp))

                        // Quick selection chips
                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            PopularIndustrialResources.forEach { resource ->
                                val isSelected = selectedResources.contains(resource)
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(
                                            if (isSelected) {
                                                if (isDarkTheme) Color(0x4010B981) else Color(0x3310B981)
                                            } else {
                                                if (isDarkTheme) Color(0x22334155) else Color(0xFFF1F5F9)
                                            }
                                        )
                                        .border(
                                            1.dp,
                                            if (isSelected) {
                                                if (isDarkTheme) EmeraldLight else LightEmerald
                                            } else {
                                                if (isDarkTheme) Color(0x33475569) else Color(0xFFCBD5E1)
                                            },
                                            RoundedCornerShape(8.dp)
                                        )
                                        .clickable(enabled = isEditingProfile) {
                                            selectedResources = if (isSelected) {
                                                selectedResources - resource
                                            } else {
                                                selectedResources + resource
                                            }
                                        }
                                        .padding(horizontal = 12.dp, vertical = 7.dp)
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        if (isSelected) {
                                            Icon(
                                                imageVector = Icons.Default.Check,
                                                contentDescription = null,
                                                tint = if (isDarkTheme) EmeraldLight else LightEmerald,
                                                modifier = Modifier.size(14.dp)
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                        }
                                        Text(
                                            text = resource,
                                            fontFamily = PlusJakartaSans,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                            fontSize = 12.sp,
                                            color = if (isSelected) {
                                                if (isDarkTheme) CrispWhite else LightTextHeadline
                                            } else {
                                                if (isDarkTheme) Slate200 else Color(0xFF475569)
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        // Add custom resource field
                        if (isEditingProfile) {
                            Spacer(modifier = Modifier.height(12.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                OutlinedTextField(
                                    value = customResourceInput,
                                    onValueChange = { customResourceInput = it },
                                    placeholder = { Text("Add custom resource / material...", fontSize = 12.sp) },
                                    singleLine = true,
                                    shape = RoundedCornerShape(10.dp),
                                    colors = dashboardTextFieldColors(isDarkTheme),
                                    modifier = Modifier.weight(1f)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = {
                                        if (customResourceInput.isNotBlank()) {
                                            selectedResources = selectedResources + customResourceInput.trim()
                                            customResourceInput = ""
                                        }
                                    },
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (isDarkTheme) EmeraldPrimary else LightEmerald
                                    )
                                ) {
                                    Icon(Icons.Default.Add, contentDescription = "Add", modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Add", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // B. ADDRESS & REGISTERED OFFICE
                        SectionSubhead("B. Address & Registered Office", "Physical plant location and legally registered headquarters.", isDarkTheme)
                        Spacer(modifier = Modifier.height(10.dp))

                        FormLabel("Plant / Manufacturing Unit Address", isDarkTheme)
                        OutlinedTextField(
                            value = addressInput,
                            onValueChange = { addressInput = it },
                            placeholder = { Text("e.g., Plot D-42, MIDC Industrial Estate, Bhosari, Pune - 411026") },
                            enabled = isEditingProfile,
                            shape = RoundedCornerShape(10.dp),
                            colors = dashboardTextFieldColors(isDarkTheme),
                            modifier = Modifier.fillMaxWidth().testTag("input_plant_address")
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        FormLabel("Registered Head Office Address", isDarkTheme)
                        OutlinedTextField(
                            value = registeredOfficeInput,
                            onValueChange = { registeredOfficeInput = it },
                            placeholder = { Text("e.g., Office 301, Trade Tower, Shivajinagar, Pune - 411005") },
                            enabled = isEditingProfile,
                            shape = RoundedCornerShape(10.dp),
                            colors = dashboardTextFieldColors(isDarkTheme),
                            modifier = Modifier.fillMaxWidth().testTag("input_registered_office")
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // C. BRANCHES / ADDITIONAL UNITS (IF ANY)
                        SectionSubhead("C. Branches / Staging Units (If you have any)", "Add regional sorting yards, warehouses, or secondary branch units.", isDarkTheme)
                        Spacer(modifier = Modifier.height(10.dp))

                        if (branchesList.isEmpty()) {
                            Text(
                                text = "No additional branches added yet.",
                                fontFamily = PlusJakartaSans,
                                fontSize = 12.sp,
                                color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                            )
                        } else {
                            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                branchesList.forEachIndexed { index, branch ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(if (isDarkTheme) Color(0xFF1E293B) else Color(0xFFF8FAFC))
                                            .border(1.dp, if (isDarkTheme) Color(0xFF334155) else Color(0xFFE2E8F0), RoundedCornerShape(8.dp))
                                            .padding(horizontal = 12.dp, vertical = 8.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                imageVector = Icons.Default.LocationOn,
                                                contentDescription = null,
                                                tint = if (isDarkTheme) EmeraldLight else LightEmerald,
                                                modifier = Modifier.size(16.dp)
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(
                                                text = branch,
                                                fontFamily = PlusJakartaSans,
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 13.sp,
                                                color = if (isDarkTheme) CrispWhite else LightTextHeadline
                                            )
                                        }

                                        if (isEditingProfile) {
                                            IconButton(
                                                onClick = {
                                                    branchesList = branchesList.toMutableList().also { it.removeAt(index) }
                                                },
                                                modifier = Modifier.size(24.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Close,
                                                    contentDescription = "Remove branch",
                                                    tint = Color(0xFFEF4444),
                                                    modifier = Modifier.size(16.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        if (isEditingProfile) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                OutlinedTextField(
                                    value = newBranchInput,
                                    onValueChange = { newBranchInput = it },
                                    placeholder = { Text("e.g., Chakan Auto Cluster Yard Unit-2", fontSize = 12.sp) },
                                    singleLine = true,
                                    shape = RoundedCornerShape(10.dp),
                                    colors = dashboardTextFieldColors(isDarkTheme),
                                    modifier = Modifier.weight(1f)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = {
                                        if (newBranchInput.isNotBlank()) {
                                            branchesList = branchesList + newBranchInput.trim()
                                            newBranchInput = ""
                                        }
                                    },
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (isDarkTheme) EmeraldPrimary else LightEmerald
                                    )
                                ) {
                                    Icon(Icons.Default.AddBusiness, contentDescription = "Add Branch", modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Add Branch", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // D. OTHER RELATED INFORMATION
                        SectionSubhead("D. Other Related Business Information", "Regulatory certifications, registration numbers, and scrap capacities.", isDarkTheme)
                        Spacer(modifier = Modifier.height(10.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Column(modifier = Modifier.weight(1f)) {
                                FormLabel("GSTIN", isDarkTheme)
                                OutlinedTextField(
                                    value = gstinInput,
                                    onValueChange = { gstinInput = it },
                                    placeholder = { Text("27AAACB1234F1Z5") },
                                    enabled = isEditingProfile,
                                    singleLine = true,
                                    shape = RoundedCornerShape(10.dp),
                                    colors = dashboardTextFieldColors(isDarkTheme),
                                    modifier = Modifier.fillMaxWidth().testTag("input_gstin")
                                )
                            }

                            Column(modifier = Modifier.weight(1f)) {
                                FormLabel("Udyam Registration No.", isDarkTheme)
                                OutlinedTextField(
                                    value = udyamInput,
                                    onValueChange = { udyamInput = it },
                                    placeholder = { Text("UDYAM-MH-26-0034567") },
                                    enabled = isEditingProfile,
                                    singleLine = true,
                                    shape = RoundedCornerShape(10.dp),
                                    colors = dashboardTextFieldColors(isDarkTheme),
                                    modifier = Modifier.fillMaxWidth().testTag("input_udyam")
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Column(modifier = Modifier.weight(1f)) {
                                FormLabel("Annual Material Volume (Tons)", isDarkTheme)
                                OutlinedTextField(
                                    value = annualVolumeInput,
                                    onValueChange = { annualVolumeInput = it },
                                    placeholder = { Text("e.g., 450 Tons / Year") },
                                    enabled = isEditingProfile,
                                    singleLine = true,
                                    shape = RoundedCornerShape(10.dp),
                                    colors = dashboardTextFieldColors(isDarkTheme),
                                    modifier = Modifier.fillMaxWidth().testTag("input_annual_volume")
                                )
                            }

                            Column(modifier = Modifier.weight(1f)) {
                                FormLabel("Pollution Consent Status", isDarkTheme)
                                OutlinedTextField(
                                    value = pollutionStatusInput,
                                    onValueChange = { pollutionStatusInput = it },
                                    placeholder = { Text("CTO Valid up to 2028") },
                                    enabled = isEditingProfile,
                                    singleLine = true,
                                    shape = RoundedCornerShape(10.dp),
                                    colors = dashboardTextFieldColors(isDarkTheme),
                                    modifier = Modifier.fillMaxWidth().testTag("input_pollution_status")
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        FormLabel("Sustainability & Circular Economy Goals", isDarkTheme)
                        OutlinedTextField(
                            value = sustainabilityGoalsInput,
                            onValueChange = { sustainabilityGoalsInput = it },
                            placeholder = { Text("e.g., Divert 100% metal scrap from landfills into verified cluster processing.") },
                            enabled = isEditingProfile,
                            shape = RoundedCornerShape(10.dp),
                            colors = dashboardTextFieldColors(isDarkTheme),
                            modifier = Modifier.fillMaxWidth().testTag("input_sustainability_goals")
                        )

                        Spacer(modifier = Modifier.height(28.dp))

                        // ACTION BUTTONS: SAVE DRAFT vs SAVE & UPDATE PROFILE
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Button 1: Save Draft (Allowed anytime!)
                            OutlinedButton(
                                onClick = {
                                    val updated = buildCurrentProfile(isDraft = true)
                                    profileState = updated
                                    onSaveProfile(updated, true)
                                    addNotification(
                                        title = "Profile Draft Saved",
                                        description = "Your enterprise profile draft was updated. Registered office: ${registeredOfficeInput.ifBlank { "Not specified" }}, Dealing in ${selectedResources.size} resource stream(s).",
                                        category = NotificationCategory.PROFILE_RESOURCE,
                                        isUrgent = false,
                                        actionLabel = "Resume Editing"
                                    )
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar("Draft saved successfully! You can resume and edit anytime.")
                                    }
                                },
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(50.dp)
                                    .testTag("btn_save_draft")
                            ) {
                                Icon(Icons.Default.Save, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Save Draft",
                                    fontFamily = PlusJakartaSans,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                            }

                            // Button 2: Save & Update Profile
                            Button(
                                onClick = {
                                    val updated = buildCurrentProfile(isDraft = false)
                                    profileState = updated
                                    isEditingProfile = false
                                    onSaveProfile(updated, false)
                                    addNotification(
                                        title = "Profile Verified & Published",
                                        description = "Enterprise credentials for ${profileState.enterpriseName} updated with ${selectedResources.size} resource(s) and plant records.",
                                        category = NotificationCategory.PROFILE_RESOURCE,
                                        isUrgent = false,
                                        actionLabel = "View Verified Card"
                                    )
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar("Profile updated and verified successfully!")
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isDarkTheme) EmeraldPrimary else LightEmerald
                                ),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .weight(1.2f)
                                    .height(50.dp)
                                    .shadow(8.dp, RoundedCornerShape(12.dp))
                                    .testTag("btn_save_complete_profile")
                            ) {
                                Icon(Icons.Default.Verified, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Save & Update Profile",
                                    fontFamily = PlusJakartaSans,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 13.sp,
                                    color = if (isDarkTheme) Slate950 else CrispWhite
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                // 4. Real-time Searchable List for Resources & Connected Enterprises
                DashboardSearchableListComponent(
                    passports = enterpriseDrpsList,
                    profile = profileState,
                    isDarkTheme = isDarkTheme,
                    onSelectDrp = { drp ->
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Opening Digital Passport ${drp.drpId} (${drp.materialName})")
                        }
                        drpSectionInitialTab = if (drp.status.isOutdated) 1 else 0
                        showDrpSectionSheet = true
                    },
                    onConnectEnterprise = { enterprise ->
                        addNotification(
                            title = "Connection Request Sent: ${enterprise.enterpriseName}",
                            description = "Circular connection request dispatched to ${enterprise.enterpriseName} in ${enterprise.location}.",
                            category = NotificationCategory.MATERIAL_MATCH,
                            isUrgent = false,
                            actionLabel = "View Partner Slip"
                        )
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Connected with ${enterprise.enterpriseName} (${enterprise.location})")
                        }
                    },
                    onRegisterNewResource = { showRegisterDialog = true }
                )

                Spacer(modifier = Modifier.height(28.dp))

                // 5. Active Feeds & Digital Resource Passports Section
                DashboardFeedsSummarySection(
                    passports = enterpriseDrpsList,
                    profile = profileState,
                    isDarkTheme = isDarkTheme,
                    onRegisterNewFeed = { showRegisterDialog = true },
                    onOpenDrpSection = { initialTab ->
                        drpSectionInitialTab = initialTab
                        showDrpSectionSheet = true
                    }
                )

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }

    // Modal to register resource directly from dashboard
    if (showRegisterDialog) {
        RegisterFeedDialog(
            isDarkTheme = isDarkTheme,
            onDismiss = { showRegisterDialog = false },
            onSubmitSuccess = { material, tons, district ->
                val drpCode = "DRP-${district.uppercase().take(3)}-2026-${(1000..9999).random()}"
                val newPassport = DigitalResourcePassport(
                    drpId = drpCode,
                    materialName = material,
                    materialCategory = "Industrial By-Product",
                    quantityValue = tons,
                    quantityUnit = "Tons",
                    enterpriseName = profileState.enterpriseName.ifBlank { "Registered Enterprise" },
                    originDistrict = district,
                    originState = "Maharashtra",
                    purityGrade = "Standard Processing Grade",
                    status = DrpStatus.AVAILABLE,
                    issuanceDate = "Today",
                    notes = "Newly issued Digital Resource Passport. Ready for marketplace matching."
                )
                enterpriseDrpsList = listOf(newPassport) + enterpriseDrpsList
                addNotification(
                    title = "Digital Resource Passport Generated",
                    description = "Passport $drpCode issued for $tons Tons of $material in $district. Ready for verified circular offtake.",
                    category = NotificationCategory.DRP_ACTION,
                    isUrgent = true,
                    actionLabel = "View DRP Slip"
                )
                addNotification(
                    title = "Matching Engine Active: $material",
                    description = "Matching algorithm is scanning regional aggregators and smelters within 60 km of $district for $material.",
                    category = NotificationCategory.MATERIAL_MATCH,
                    isUrgent = true,
                    actionLabel = "Monitor Offtakers"
                )
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Resource registered: $tons Tons of $material in $district! Digital Resource Passport generated.")
                }
            }
        )
    }

    // Notifications Bottom Sheet
    if (showNotificationsSheet) {
        DashboardNotificationsSheet(
            notifications = notificationsList,
            isDarkTheme = isDarkTheme,
            onDismiss = { showNotificationsSheet = false },
            onMarkAllAsRead = {
                notificationsList = notificationsList.map { it.copy(isRead = true) }
            },
            onNotificationActionClick = { notif ->
                notificationsList = notificationsList.map {
                    if (it.id == notif.id) it.copy(isRead = true) else it
                }
                showNotificationsSheet = false
                coroutineScope.launch {
                    when (notif.category) {
                        NotificationCategory.MATERIAL_MATCH -> {
                            snackbarHostState.showSnackbar("Opening match offer: ${notif.title}")
                        }
                        NotificationCategory.DRP_ACTION -> {
                            snackbarHostState.showSnackbar("Opening Digital Resource Passport: ${notif.title}")
                        }
                        NotificationCategory.PROFILE_RESOURCE -> {
                            isEditingProfile = true
                            snackbarHostState.showSnackbar("Navigating to Profile details: ${notif.title}")
                        }
                        else -> {
                            snackbarHostState.showSnackbar(notif.title)
                        }
                    }
                }
            },
            onDeleteNotification = { notifId ->
                notificationsList = notificationsList.filter { it.id != notifId }
            },
            onSimulateNewNotification = {
                val sampleAlerts = listOf(
                    Triple(
                        "High-Volume Offtake Match Found!",
                        "Tata Motors vendor cluster in Chakan requested 42 Tons of Mild Steel Turning Scrap at premium index rate.",
                        NotificationCategory.MATERIAL_MATCH
                    ),
                    Triple(
                        "DRP Action Required: SPCB Manifest Sign-off",
                        "Digital Resource Passport DRP-MH-2026-8812 requires digital authorized sign-off before dispatch.",
                        NotificationCategory.DRP_ACTION
                    ),
                    Triple(
                        "Resource Benchmark Rate Update",
                        "Aluminum wire scrap & extrusion scrap benchmark rates increased +3.8% across western state clusters.",
                        NotificationCategory.PROFILE_RESOURCE
                    ),
                    Triple(
                        "Offtake Verification Confirmed",
                        "Jindal Ancillary cluster verified physical inspection for lot DRP-MH-2026-9041.",
                        NotificationCategory.MATERIAL_MATCH
                    )
                )
                val randomAlert = sampleAlerts.random()
                addNotification(
                    title = randomAlert.first,
                    description = randomAlert.second,
                    category = randomAlert.third,
                    isUrgent = true,
                    actionLabel = "Review Alert"
                )
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("New notification received: ${randomAlert.first}")
                }
            }
        )
    }

    // Platform Global Search Sheet (Search any DRP, Resource, or Enterprise)
    if (showGlobalSearchSheet) {
        PlatformGlobalSearchSheet(
            passports = enterpriseDrpsList,
            isDarkTheme = isDarkTheme,
            onDismiss = { showGlobalSearchSheet = false },
            onSelectDrp = { drp ->
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Inspecting Passport: ${drp.drpId} - ${drp.materialName}")
                }
            }
        )
    }

    // Enterprise DRP Section Sheet (Current DRPs vs DRP History)
    if (showDrpSectionSheet) {
        EnterpriseDrpSectionSheet(
            passports = enterpriseDrpsList,
            enterpriseName = profileState.enterpriseName,
            isDarkTheme = isDarkTheme,
            initialTab = drpSectionInitialTab,
            onDismiss = { showDrpSectionSheet = false },
            onGenerateNewPassport = {
                showDrpSectionSheet = false
                showRegisterDialog = true
            }
        )
    }
}


}
