package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AmberOrange
import com.example.ui.theme.CrispWhite
import com.example.ui.theme.ElectricPurpleLight
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.LightBorder
import com.example.ui.theme.LightEmeraldDark
import com.example.ui.theme.LightOrange
import com.example.ui.theme.LightPurple
import com.example.ui.theme.LightSurfaceCard
import com.example.ui.theme.LightTextHeadline
import com.example.ui.theme.LightTextMuted
import com.example.ui.theme.PlusJakartaSans
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate950

enum class StatType {
    DISTRICTS,
    WASTE_REMOVED,
    MSME_PARTNERS,
    SAVINGS
}

@Composable
fun LoopGridLiveStatsBar(
    isDarkTheme: Boolean,
    onStatClick: (StatType) -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .testTag("live_stats_bar_container")
    ) {
        val isWide = maxWidth >= 680.dp

        // Horizontal banner (Dark: bg-slate-950/90; Light: Pure white with clean crisp border & soft shadow)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = if (isDarkTheme) 20.dp else 12.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = if (isDarkTheme) Color(0x66020617) else Color(0x180F172A),
                    spotColor = if (isDarkTheme) Color(0x80020617) else Color(0x220F172A)
                )
                .clip(RoundedCornerShape(16.dp))
                .border(
                    width = 1.dp,
                    color = if (isDarkTheme) Color(0xCC1E293B) else LightBorder,
                    shape = RoundedCornerShape(16.dp)
                )
                .background(
                    if (isDarkTheme) Color(0xF2020617) else LightSurfaceCard
                )
                .padding(horizontal = 22.dp, vertical = 18.dp)
        ) {
            if (isWide) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StatColumn(
                        value = "48",
                        category = "ACTIVE DISTRICTS",
                        categoryColor = if (isDarkTheme) EmeraldLight else LightEmeraldDark,
                        subtitle = "Verified locations",
                        isDarkTheme = isDarkTheme,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onStatClick(StatType.DISTRICTS) }
                            .testTag("stat_col_districts")
                    )

                    VerticalStatDivider(isDarkTheme)

                    StatColumn(
                        value = "3,420 TONS",
                        category = "WASTE REMOVED",
                        categoryColor = if (isDarkTheme) EmeraldLight else LightEmeraldDark,
                        subtitle = "Total materials tracked",
                        isDarkTheme = isDarkTheme,
                        modifier = Modifier
                            .weight(1.15f)
                            .clickable { onStatClick(StatType.WASTE_REMOVED) }
                            .testTag("stat_col_waste")
                    )

                    VerticalStatDivider(isDarkTheme)

                    StatColumn(
                        value = "128 MSMEs",
                        category = "ACTIVE PARTNERS",
                        categoryColor = if (isDarkTheme) AmberOrange else LightOrange,
                        subtitle = "Registered enterprises",
                        isDarkTheme = isDarkTheme,
                        modifier = Modifier
                            .weight(1.15f)
                            .clickable { onStatClick(StatType.MSME_PARTNERS) }
                            .testTag("stat_col_partners")
                    )

                    VerticalStatDivider(isDarkTheme)

                    StatColumn(
                        value = "8.4 LAKHS",
                        category = "SAVINGS GENERATED",
                        categoryColor = if (isDarkTheme) ElectricPurpleLight else LightPurple,
                        subtitle = "Financial value generated",
                        isDarkTheme = isDarkTheme,
                        modifier = Modifier
                            .weight(1.15f)
                            .clickable { onStatClick(StatType.SAVINGS) }
                            .testTag("stat_col_savings")
                    )
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StatColumn(
                        value = "48",
                        category = "ACTIVE DISTRICTS",
                        categoryColor = if (isDarkTheme) EmeraldLight else LightEmeraldDark,
                        subtitle = "Verified locations",
                        isDarkTheme = isDarkTheme,
                        modifier = Modifier
                            .widthIn(min = 150.dp)
                            .clickable { onStatClick(StatType.DISTRICTS) }
                            .testTag("stat_col_districts")
                    )

                    VerticalStatDivider(isDarkTheme)

                    StatColumn(
                        value = "3,420 TONS",
                        category = "WASTE REMOVED",
                        categoryColor = if (isDarkTheme) EmeraldLight else LightEmeraldDark,
                        subtitle = "Total materials tracked",
                        isDarkTheme = isDarkTheme,
                        modifier = Modifier
                            .widthIn(min = 180.dp)
                            .clickable { onStatClick(StatType.WASTE_REMOVED) }
                            .testTag("stat_col_waste")
                    )

                    VerticalStatDivider(isDarkTheme)

                    StatColumn(
                        value = "128 MSMEs",
                        category = "ACTIVE PARTNERS",
                        categoryColor = if (isDarkTheme) AmberOrange else LightOrange,
                        subtitle = "Registered enterprises",
                        isDarkTheme = isDarkTheme,
                        modifier = Modifier
                            .widthIn(min = 180.dp)
                            .clickable { onStatClick(StatType.MSME_PARTNERS) }
                            .testTag("stat_col_partners")
                    )

                    VerticalStatDivider(isDarkTheme)

                    StatColumn(
                        value = "8.4 LAKHS",
                        category = "SAVINGS GENERATED",
                        categoryColor = if (isDarkTheme) ElectricPurpleLight else LightPurple,
                        subtitle = "Financial value generated",
                        isDarkTheme = isDarkTheme,
                        modifier = Modifier
                            .widthIn(min = 190.dp)
                            .clickable { onStatClick(StatType.SAVINGS) }
                            .testTag("stat_col_savings")
                    )
                }
            }
        }
    }
}

@Composable
private fun StatColumn(
    value: String,
    category: String,
    categoryColor: Color,
    subtitle: String,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Value: Huge text-4xl font-extrabold
        Text(
            text = value,
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
            lineHeight = 36.sp,
            color = if (isDarkTheme) CrispWhite else LightTextHeadline,
            letterSpacing = (-0.5).sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Category: bold, uppercase tracking-wider text-xs
        Text(
            text = category,
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            lineHeight = 14.sp,
            color = categoryColor,
            letterSpacing = 1.0.sp
        )

        Spacer(modifier = Modifier.height(3.dp))

        // Subtitle: readable secondary text
        Text(
            text = subtitle,
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            lineHeight = 17.sp,
            color = if (isDarkTheme) Slate400 else LightTextMuted
        )
    }
}

@Composable
private fun VerticalStatDivider(isDarkTheme: Boolean) {
    Box(
        modifier = Modifier
            .height(52.dp)
            .width(1.dp)
            .background(if (isDarkTheme) Slate800.copy(alpha = 0.8f) else LightBorder)
    )
}
