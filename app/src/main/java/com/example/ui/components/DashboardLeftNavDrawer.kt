package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material3.HorizontalDivider
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

enum class DashboardNavTab(
    val title: String,
    val icon: ImageVector,
    val testTag: String
) {
    // 1. Profile
    PROFILE("Profile", Icons.Default.Person, "nav_item_profile"),
    // 2. Log Out
    LOGOUT("Log Out", Icons.Default.Logout, "nav_item_logout"),
    // 3. Notification
    NOTIFICATIONS("Notifications & Alerts", Icons.Default.Notifications, "nav_item_notifications"),
    // 4. Search Option (Search any DRP, resource, or enterprise)
    SEARCH("Search Platform", Icons.Default.Search, "nav_item_search"),
    // 5. Current DRP (Currently available lots of the enterprise)
    CURRENT_DRP("Current DRPs", Icons.Default.Inventory2, "nav_item_current_drp"),
    // 6. DRP History (Outdated & settled passports archive)
    DRP_HISTORY("DRP History", Icons.Default.History, "nav_item_drp_history"),

    // Platform Command Center & Ecosystem sections
    HOME("Command Center", Icons.Default.Home, "nav_item_home"),
    REPORT("Register Feed / DRP", Icons.Default.PostAdd, "nav_item_report"),
    EXPLORE("Marketplace & Offtakers", Icons.Default.Explore, "nav_item_explore"),
    MISSIONS("Missions & Logistics", Icons.Default.TrackChanges, "nav_item_missions"),
    MONITORING("Compliance & Monitoring", Icons.Default.MonitorHeart, "nav_item_monitoring"),
    RECOVERY("Recovery Operations", Icons.Default.Autorenew, "nav_item_recovery"),
    LEADERBOARD("Cluster Leaderboard", Icons.Default.EmojiEvents, "nav_item_leaderboard"),
    ABOUT("About LoopGrid", Icons.Default.Info, "nav_item_about")
}

/**
 * Left-side navigation center drawer / rail inspired by the reference design.
 * Features:
 * - Brand header with LoopGrid logo & dark/light mode toggle
 * - Full navigation list with active emerald pill indicator
 * - Dedicated NOTIFICATIONS tab with live unread badge count
 * - Bottom score box: Environmental / Circular Score (742 [DEMO DATA])
 * - User name and Role badge
 * - Creator credits with LinkedIn & GitHub links
 */
@Composable
fun DashboardLeftNavDrawer(
    currentTab: DashboardNavTab,
    onTabSelected: (DashboardNavTab) -> Unit,
    profile: EnterpriseProfile,
    unreadNotificationCount: Int,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    onCloseDrawer: (() -> Unit)? = null,
    onOpenLinkedIn: () -> Unit = {},
    onOpenGitHub: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val navBg = if (isDarkTheme) Color(0xFF091210) else Color(0xFFF8FAFC)
    val borderColor = if (isDarkTheme) Color(0x33334155) else Color(0xFFE2E8F0)

    Column(
        modifier = modifier
            .fillMaxHeight()
            .width(280.dp)
            .background(navBg)
            .border(width = 1.dp, color = borderColor)
            .padding(vertical = 16.dp, horizontal = 14.dp)
            .testTag("dashboard_left_nav_drawer")
    ) {
        // 1. Brand Header Row
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
                        .background(if (isDarkTheme) Color(0x3310B981) else Color(0x2010B981)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Autorenew,
                        contentDescription = "LoopGrid Logo",
                        tint = if (isDarkTheme) EmeraldLight else LightEmerald,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Loop",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp,
                        color = if (isDarkTheme) CrispWhite else LightTextHeadline
                    )
                    Text(
                        text = "Grid",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp,
                        color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = onToggleTheme,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(if (isDarkTheme) Color(0x22334155) else Color(0xFFE2E8F0))
                ) {
                    Icon(
                        imageVector = if (isDarkTheme) Icons.Default.LightMode else Icons.Default.DarkMode,
                        contentDescription = "Toggle Theme",
                        tint = if (isDarkTheme) Color(0xFFFBBF24) else Color(0xFF475569),
                        modifier = Modifier.size(16.dp)
                    )
                }

                if (onCloseDrawer != null) {
                    Spacer(modifier = Modifier.width(6.dp))
                    IconButton(
                        onClick = onCloseDrawer,
                        modifier = Modifier.size(30.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Menu",
                            tint = if (isDarkTheme) Slate400 else Color(0xFF64748B),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(color = borderColor, thickness = 1.dp)
        Spacer(modifier = Modifier.height(12.dp))

        // 2. Navigation Items (Scrollable)
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            DashboardNavTab.entries.forEach { tab ->
                val isSelected = currentTab == tab
                val isNotificationsTab = tab == DashboardNavTab.NOTIFICATIONS

                val tabIcon = if (isNotificationsTab && unreadNotificationCount > 0) {
                    Icons.Default.NotificationsActive
                } else {
                    tab.icon
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            if (isSelected) {
                                if (isDarkTheme) Color(0x2E10B981) else Color(0x1F10B981)
                            } else {
                                Color.Transparent
                            }
                        )
                        .border(
                            width = if (isSelected) 1.dp else 0.dp,
                            color = if (isSelected) {
                                if (isDarkTheme) Color(0x4D10B981) else Color(0x66059669)
                            } else {
                                Color.Transparent
                            },
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable { onTabSelected(tab) }
                        .padding(horizontal = 12.dp, vertical = 10.dp)
                        .testTag(tab.testTag)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = tabIcon,
                                contentDescription = null,
                                tint = if (isSelected) {
                                    if (isDarkTheme) EmeraldLight else LightEmeraldDark
                                } else if (isNotificationsTab && unreadNotificationCount > 0) {
                                    Color(0xFFEF4444)
                                } else {
                                    if (isDarkTheme) Slate400 else Color(0xFF64748B)
                                },
                                modifier = Modifier.size(18.dp)
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = if (tab == DashboardNavTab.RECOVERY && profile.role == EnterpriseRole.FINANCIAL_PARTNER) {
                                    "Credit & Settlements"
                                } else {
                                    tab.title
                                },
                                fontFamily = PlusJakartaSans,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 13.sp,
                                color = if (isSelected) {
                                    if (isDarkTheme) CrispWhite else LightTextHeadline
                                } else {
                                    if (isDarkTheme) Color(0xCCCBD5E1) else Color(0xFF475569)
                                }
                            )
                        }

                        // Badge indicator for Notifications
                        if (isNotificationsTab && unreadNotificationCount > 0) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xFFEF4444))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "$unreadNotificationCount",
                                    fontFamily = PlusJakartaSans,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 10.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider(color = borderColor, thickness = 1.dp)
        Spacer(modifier = Modifier.height(12.dp))

        // 3. Score & User Box at the bottom of navigation center (matches reference screenshot)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(if (isDarkTheme) Color(0xFF0F1B17) else Color(0xFFF1F5F9))
                .border(1.dp, if (isDarkTheme) Color(0x2210B981) else Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = when (profile.role) {
                            EnterpriseRole.RESOURCE_PROVIDER -> "CIRCULAR SCORE"
                            EnterpriseRole.TRANSIT_PARTNER -> "LOGISTICS SCORE"
                            EnterpriseRole.PROCESSING_ENTERPRISE -> "YIELD SCORE"
                            EnterpriseRole.FINANCIAL_PARTNER -> "CAPITAL SCORE"
                        },
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                    )

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

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = when (profile.role) {
                        EnterpriseRole.RESOURCE_PROVIDER -> "742"
                        EnterpriseRole.TRANSIT_PARTNER -> "865"
                        EnterpriseRole.PROCESSING_ENTERPRISE -> "915"
                        EnterpriseRole.FINANCIAL_PARTNER -> "790"
                    },
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                )

                Text(
                    text = profile.enterpriseName.ifBlank { "My Enterprise" },
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 11.sp,
                    color = if (isDarkTheme) CrispWhite else LightTextHeadline
                )

                Text(
                    text = profile.role.badgeLabel,
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 9.sp,
                    color = if (isDarkTheme) EmeraldLight else LightEmerald
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // 4. Footer Developer Info
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "© 2026 LOOPGRID",
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                color = if (isDarkTheme) Slate400 else Color(0xFF94A3B8)
            )

            Text(
                text = "Darshil Jain & Sarvesh Kulkarni",
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Medium,
                fontSize = 9.sp,
                color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "LinkedIn",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    color = if (isDarkTheme) EmeraldLight else LightEmerald,
                    modifier = Modifier.clickable { onOpenLinkedIn() }
                )
                Text(
                    text = "•",
                    fontSize = 10.sp,
                    color = if (isDarkTheme) Slate700 else Color(0xFFCBD5E1)
                )
                Text(
                    text = "GitHub",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    color = if (isDarkTheme) EmeraldLight else LightEmerald,
                    modifier = Modifier.clickable { onOpenGitHub() }
                )
            }
        }
    }
}
