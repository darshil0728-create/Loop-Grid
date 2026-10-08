package com.example.ui.components

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Factory
import androidx.compose.material.icons.filled.Recycling
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.CrispWhite
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.PlusJakartaSans
import com.example.ui.theme.Slate300
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900
import com.example.ui.theme.Slate950

@Composable
fun RegisterFeedDialog(
    isDarkTheme: Boolean,
    onDismiss: () -> Unit,
    onSubmitSuccess: (material: String, tons: Double, district: String) -> Unit
) {
    var materialName by remember { mutableStateOf("Cold-Rolled Steel Offcut Coils") }
    var tonsText by remember { mutableStateOf("25.0") }
    var districtText by remember { mutableStateOf("Pune Industrial Corridor (MIDC)") }
    var category by remember { mutableStateOf("Metal & Alloys") }
    var submitted by remember { mutableStateOf(false) }

    val categories = listOf("Metal & Alloys", "Polymer / Plastic", "Textile Lint", "Foundry Slag", "Chemical Slurry")

    val estimatedValue = (tonsText.toDoubleOrNull() ?: 0.0) * 42000
    val estimatedCo2Saved = (tonsText.toDoubleOrNull() ?: 0.0) * 1.85

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .widthIn(max = 480.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .border(
                    width = 1.dp,
                    color = if (isDarkTheme) Color(0x6610B981) else Color(0xFFCBD5E1),
                    shape = RoundedCornerShape(20.dp)
                )
                .background(if (isDarkTheme) Color(0xF80B1120) else Color.White)
                .padding(24.dp)
                .testTag("register_feed_dialog")
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
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(EmeraldPrimary.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Recycling,
                                contentDescription = null,
                                tint = EmeraldPrimary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "REGISTER A RESOURCE",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 18.sp,
                                color = if (isDarkTheme) CrispWhite else Slate950
                            )
                            Text(
                                text = "Step 01 • Create Digital Resource Passport (DRP)",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                                color = if (isDarkTheme) EmeraldLight else Color(0xFF047857)
                            )
                        }
                    }

                    IconButton(onClick = onDismiss, modifier = Modifier.testTag("close_register_dialog")) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = if (isDarkTheme) Slate400 else Slate700
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                if (submitted) {
                    // Success state
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(EmeraldDark.copy(alpha = 0.2f))
                            .border(1.dp, EmeraldPrimary, RoundedCornerShape(12.dp))
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.Done,
                                contentDescription = null,
                                tint = EmeraldLight,
                                modifier = Modifier.size(36.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Feed Successfully Registered!",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = EmeraldLight
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Broadcasting to 128 registered MSME buyers in $districtText. Database synced.",
                                fontFamily = PlusJakartaSans,
                                fontSize = 13.sp,
                                color = if (isDarkTheme) Slate300 else Slate800,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(14.dp))
                            Button(
                                onClick = onDismiss,
                                colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("Back to LoopGrid", color = Slate950, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                } else {
                    // Category selector
                    Text(
                        text = "MATERIAL CATEGORY",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = EmeraldLight,
                        letterSpacing = 0.5.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        categories.forEach { cat ->
                            val isSelected = cat == category
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .border(
                                        width = 1.dp,
                                        color = if (isSelected) EmeraldPrimary else (if (isDarkTheme) Slate700 else Color(0xFFCBD5E1)),
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .background(
                                        if (isSelected) EmeraldPrimary.copy(alpha = 0.2f)
                                        else (if (isDarkTheme) Slate900 else Color(0xFFF1F5F9))
                                    )
                                    .clickable { category = cat }
                                    .padding(horizontal = 12.dp, vertical = 7.dp)
                            ) {
                                Text(
                                    text = cat,
                                    fontFamily = PlusJakartaSans,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    fontSize = 12.sp,
                                    color = if (isSelected) EmeraldLight else (if (isDarkTheme) Slate300 else Slate800)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Material Spec Name
                    Text(
                        text = "MATERIAL SPEC / DESCRIPTION",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = if (isDarkTheme) Slate300 else Slate700,
                        letterSpacing = 0.5.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = materialName,
                        onValueChange = { materialName = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_material_name"),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = EmeraldPrimary,
                            unfocusedBorderColor = if (isDarkTheme) Slate700 else Color(0xFFCBD5E1),
                            focusedTextColor = if (isDarkTheme) CrispWhite else Slate950,
                            unfocusedTextColor = if (isDarkTheme) CrispWhite else Slate950
                        ),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Quantity in Tons & District
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "VOLUME (TONS)",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                color = if (isDarkTheme) Slate300 else Slate700
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            OutlinedTextField(
                                value = tonsText,
                                onValueChange = { tonsText = it },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("input_tons"),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = EmeraldPrimary,
                                    unfocusedBorderColor = if (isDarkTheme) Slate700 else Color(0xFFCBD5E1),
                                    focusedTextColor = if (isDarkTheme) CrispWhite else Slate950,
                                    unfocusedTextColor = if (isDarkTheme) CrispWhite else Slate950
                                ),
                                singleLine = true
                            )
                        }

                        Column(modifier = Modifier.weight(1.5f)) {
                            Text(
                                text = "DISTRICT / CLUSTER",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                color = if (isDarkTheme) Slate300 else Slate700
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            OutlinedTextField(
                                value = districtText,
                                onValueChange = { districtText = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("input_district"),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = EmeraldPrimary,
                                    unfocusedBorderColor = if (isDarkTheme) Slate700 else Color(0xFFCBD5E1),
                                    focusedTextColor = if (isDarkTheme) CrispWhite else Slate950,
                                    unfocusedTextColor = if (isDarkTheme) CrispWhite else Slate950
                                ),
                                singleLine = true
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Real-time Automated Valuation Card
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isDarkTheme) Slate900 else Color(0xFFF1F5F9))
                            .border(1.dp, if (isDarkTheme) Color(0x3310B981) else Color(0xFFCBD5E1), RoundedCornerShape(12.dp))
                            .padding(14.dp)
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Estimated Circular Value",
                                    fontFamily = PlusJakartaSans,
                                    fontSize = 12.sp,
                                    color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                                )
                                Text(
                                    text = "₹${"%,.0f".format(estimatedValue)}",
                                    fontFamily = PlusJakartaSans,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = EmeraldLight
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Projected CO₂ Abatement",
                                    fontFamily = PlusJakartaSans,
                                    fontSize = 12.sp,
                                    color = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                                )
                                Text(
                                    text = "%.1f MT CO₂e".format(estimatedCo2Saved),
                                    fontFamily = PlusJakartaSans,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 13.sp,
                                    color = if (isDarkTheme) CrispWhite else Slate950
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Submit Button
                    Button(
                        onClick = {
                            val tons = tonsText.toDoubleOrNull() ?: 10.0
                            onSubmitSuccess(materialName, tons, districtText)
                            submitted = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("submit_feed_btn"),
                        colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "REGISTER RESOURCE & GENERATE DRP",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Slate950,
                            letterSpacing = 0.5.sp
                        )
                    }
                }
            }
        }
    }
}
