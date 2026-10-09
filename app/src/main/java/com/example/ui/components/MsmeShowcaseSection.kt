package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Factory
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.CrispWhite
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.PlusJakartaSans
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate300
import com.example.ui.theme.Slate400

/**
 * Dedicated Dark Green Section showcasing the MSME workshop in action.
 * Features:
 * - Rich dark green colored section background (#042618 gradient)
 * - The MSME manufacturing image framed neatly inside with border space on all sides (not covering the whole screen)
 * - Visual details highlighting ground-level resource recovery
 */
@Composable
fun MsmeShowcaseSection(
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier
) {
    // Rich dark green section background
    val darkGreenSectionBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF031E13), // Deep dark emerald green
            Color(0xFF052B1B), // Rich dark forest green
            Color(0xFF02160E)  // Dark green base
        )
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(darkGreenSectionBrush)
            .padding(horizontal = 20.dp, vertical = 48.dp)
            .testTag("msme_showcase_section"),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 760.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Tag in the Dark Green Section
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .border(1.dp, Color(0x5534D399), RoundedCornerShape(50.dp))
                    .background(Color(0x33064E3B))
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(EmeraldLight)
                    )
                    Spacer(modifier = Modifier.width(7.dp))
                    Text(
                        text = "ON-GROUND RESOURCE RECOVERY",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = EmeraldLight,
                        letterSpacing = 1.0.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Heading
            Text(
                text = "PRECISION IN ACTION AT MSME PLANTS",
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                lineHeight = 30.sp,
                letterSpacing = (-0.5).sp,
                color = CrispWhite,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Recovered steel coils and alloys re-manufactured by verified industrial fabrication partners.",
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = CrispWhite,
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(max = 560.dp)
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Framed Image Box:
            // "over that add the image that I am attaching . Dont cover the whole screen with the image . Keep a little space in the border ."
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp) // Keeps deliberate space around all borders
                    .shadow(
                        elevation = 20.dp,
                        shape = RoundedCornerShape(20.dp),
                        ambientColor = Color(0x66000000),
                        spotColor = Color(0x8002160E)
                    )
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 1.5.dp,
                        color = Color(0x4D10B981), // Subtle glowing emerald rim
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(Color(0xFF04180F))
                    .testTag("msme_image_frame")
            ) {
                Column {
                    // The attached industrial MSME photo
                    Image(
                        painter = painterResource(id = R.drawable.img_msme_welding_sparks),
                        contentDescription = "MSME industrial welding workshop in action",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 10f)
                            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    )

                    // Information caption ribbon directly integrated into the frame
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xE6052317))
                            .padding(horizontal = 18.dp, vertical = 14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Factory,
                                contentDescription = null,
                                tint = EmeraldLight,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = "Shree Balaji Fabrication Works",
                                    fontFamily = PlusJakartaSans,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = CrispWhite
                                )
                                Text(
                                    text = "Cluster: Pune MIDC • Tier-2 Verified Offtaker",
                                    fontFamily = PlusJakartaSans,
                                    fontSize = 11.sp,
                                    color = Color(0xB3CBD5E1)
                                )
                            }
                        }

                        // Status pill badge
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color(0x3310B981))
                                .border(1.dp, Color(0x5510B981), RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Verified,
                                    contentDescription = null,
                                    tint = EmeraldLight,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "VERIFIED UNIT",
                                    fontFamily = PlusJakartaSans,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp,
                                    color = EmeraldLight
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
