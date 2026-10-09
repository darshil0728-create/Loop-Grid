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
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import com.example.data.EnterpriseProfile
import com.example.data.PopularIndustrialResources
import com.example.ui.components.RegisterFeedDialog
import com.example.ui.theme.CrispWhite
import com.example.ui.theme.DarkBgBase
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
            listOf(Color(0xFF060B0E), Color(0xFF091410), Color(0xFF050907))
        } else {
            listOf(Color(0xFFF0FDF4), Color(0xFFF8FAFC), Color(0xFFF1F5F9))
        }
    )

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
                // 1. Dashboard Top Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = onBackToHome,
                            modifier = Modifier.testTag("btn_dashboard_back")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back to Home",
                                tint = if (isDarkTheme) CrispWhite else LightTextHeadline
                            )
                        }

                        Spacer(modifier = Modifier.width(6.dp))

                        Column {
                            Text(
                                text = "ENTERPRISE DASHBOARD",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                letterSpacing = 1.0.sp,
                                color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                            )
                            Text(
                                text = profileState.enterpriseName.ifBlank { "My Enterprise" },
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 18.sp,
                                color = if (isDarkTheme) CrispWhite else LightTextHeadline
                            )
                        }
                    }

                    // Logout / Home Button
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
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

                Spacer(modifier = Modifier.height(12.dp))

                // 2. Enterprise Identity Overview Card
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
                            if (isDarkTheme) Color(0x33334155) else LightBorder,
                            RoundedCornerShape(20.dp)
                        )
                        .background(if (isDarkTheme) Color(0xF80E1714) else LightSurfaceCard)
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
                                            .background(if (isDarkTheme) Color(0x330B120F) else Color(0xFFF8FAFC))
                                            .border(1.dp, if (isDarkTheme) Color(0x22334155) else Color(0xFFE2E8F0), RoundedCornerShape(8.dp))
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

                // 4. Active Feeds & Digital Resource Passports Section
                DashboardFeedsSummarySection(
                    profile = profileState,
                    isDarkTheme = isDarkTheme,
                    onRegisterNewFeed = { showRegisterDialog = true }
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
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Resource registered: $tons Tons of $material in $district! Digital Resource Passport generated.")
                }
            }
        )
    }
}

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
            .border(1.dp, if (isDarkTheme) Color(0x3310B981) else Color(0x4D059669), RoundedCornerShape(20.dp))
            .background(if (isDarkTheme) Color(0xF80B1411) else LightSurfaceCard)
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
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (isDarkTheme) Color(0x3310B981) else Color(0x1F059669))
                                .border(1.dp, if (isDarkTheme) Color(0x5510B981) else Color(0x4D059669), RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = profile.orgType,
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                color = if (isDarkTheme) EmeraldLight else LightEmeraldDark
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (isDarkTheme) Color(0x333B82F6) else Color(0x1F2563EB))
                                .border(1.dp, if (isDarkTheme) Color(0x553B82F6) else Color(0x4D2563EB), RoundedCornerShape(6.dp))
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

                    Text(
                        text = profile.enterpriseName.ifBlank { "Registered Enterprise" },
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 22.sp,
                        color = if (isDarkTheme) CrispWhite else LightTextHeadline
                    )

                    Spacer(modifier = Modifier.height(4.dp))

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
    profile: EnterpriseProfile,
    isDarkTheme: Boolean,
    onRegisterNewFeed: () -> Unit
) {
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

            OutlinedButton(
                onClick = onRegisterNewFeed,
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(Icons.Default.QrCode2, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Generate Passport", fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Sample Active Passport Cards
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(14.dp)) {
            FeedPassportCard(
                drpId = "DRP-MH-2026-9041",
                material = "Cold Rolled Steel Trimmings",
                volume = "38.5 Tons",
                status = "Offtake Match Verified",
                statusColor = Color(0xFF10B981),
                isDarkTheme = isDarkTheme,
                modifier = Modifier.weight(1f)
            )

            FeedPassportCard(
                drpId = "DRP-MH-2026-8812",
                material = "Zinc Residue & Ash",
                volume = "14.2 Tons",
                status = "Logistics Coordinated",
                statusColor = Color(0xFF3B82F6),
                isDarkTheme = isDarkTheme,
                modifier = Modifier.weight(1f)
            )
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
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .border(1.dp, if (isDarkTheme) Color(0x22334155) else Color(0xFFE2E8F0), RoundedCornerShape(14.dp))
            .background(if (isDarkTheme) Color(0xFF0F1822) else Color.White)
            .padding(16.dp)
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
    unfocusedBorderColor = if (isDarkTheme) Color(0x33334155) else Color(0xFFCBD5E1),
    focusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
    unfocusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
    disabledTextColor = if (isDarkTheme) Color(0xD9CBD5E1) else LightTextHeadline,
    focusedContainerColor = if (isDarkTheme) Color(0x330B120F) else Color(0xFFF8FAFC),
    unfocusedContainerColor = if (isDarkTheme) Color(0x330B120F) else Color(0xFFF8FAFC),
    disabledContainerColor = if (isDarkTheme) Color(0x220B120F) else Color(0xFFF1F5F9)
)
