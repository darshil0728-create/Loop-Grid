package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.OrganizationNatures
import com.example.data.OrganizationTypes
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
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate950

/**
 * Enterprise Sign Up Form:
 * Asks strictly for:
 * 1. Name of the organisation
 * 2. Name of the owner
 * 3. What type of organisation is it (dropdown selection)
 * 4. Nature of organisation (with inbuilt options to choose from)
 * 5. Phone number
 * 6. Email ID
 * 7. Password
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    isDarkTheme: Boolean,
    onSignUpSuccess: (
        orgName: String,
        ownerName: String,
        orgType: String,
        orgNature: String,
        phone: String,
        email: String,
        password: String
    ) -> Unit,
    onNavigateToLogin: () -> Unit,
    onExploreDemoClick: () -> Unit = {},
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onBack() }

    var orgName by remember { mutableStateOf("") }
    var ownerName by remember { mutableStateOf("") }
    var selectedOrgType by remember { mutableStateOf(OrganizationTypes[1]) }
    var orgTypeExpanded by remember { mutableStateOf(false) }

    var selectedOrgNature by remember { mutableStateOf(OrganizationNatures[0]) }
    var orgNatureExpanded by remember { mutableStateOf(false) }

    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val bgBrush = Brush.verticalGradient(
        colors = if (isDarkTheme) {
            listOf(Color(0xFF070D09), Color(0xFF0B1410), Color(0xFF060907))
        } else {
            listOf(Color(0xFFF0FDF4), Color(0xFFF8FAFC), Color(0xFFF1F5F9))
        }
    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = if (isDarkTheme) DarkBgBase else LightBgBase
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
                // Top Navigation Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("btn_signup_back")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = if (isDarkTheme) CrispWhite else LightTextHeadline
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Back to LoopGrid",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = if (isDarkTheme) CrispWhite else LightTextHeadline
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Form Card
                Box(
                    modifier = Modifier
                        .widthIn(max = 560.dp)
                        .fillMaxWidth()
                        .shadow(
                            elevation = if (isDarkTheme) 16.dp else 8.dp,
                            shape = RoundedCornerShape(24.dp),
                            ambientColor = if (isDarkTheme) Color(0x60000000) else Color(0x150F172A),
                            spotColor = if (isDarkTheme) Color(0x80000000) else Color(0x200F172A)
                        )
                        .clip(RoundedCornerShape(24.dp))
                        .border(
                            1.dp,
                            if (isDarkTheme) Color(0x33334155) else LightBorder,
                            RoundedCornerShape(24.dp)
                        )
                        .background(if (isDarkTheme) Color(0xF80E1714) else LightSurfaceCard)
                        .padding(26.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Register Your Organisation",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 24.sp,
                            lineHeight = 30.sp,
                            color = if (isDarkTheme) CrispWhite else LightTextHeadline
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Create an enterprise account to list by-products, access AI matches, and generate Digital Resource Passports.",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            lineHeight = 19.sp,
                            color = if (isDarkTheme) Color(0xBF94A3B8) else Color(0xFF64748B)
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        // EXPLORE DEMO ACCOUNT Direct Access (No ID / Password required)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(
                                    elevation = 6.dp,
                                    shape = RoundedCornerShape(14.dp),
                                    ambientColor = if (isDarkTheme) Color(0x4010B981) else Color(0x20059669)
                                )
                                .clip(RoundedCornerShape(14.dp))
                                .background(if (isDarkTheme) Color(0x33064E3B) else Color(0x2010B981))
                                .border(1.5.dp, if (isDarkTheme) Color(0x6610B981) else Color(0x99059669), RoundedCornerShape(14.dp))
                                .clickable { onExploreDemoClick() }
                                .padding(horizontal = 16.dp, vertical = 14.dp)
                                .testTag("btn_signup_explore_demo_account")
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = "EXPLORE DEMO ACCOUNT",
                                        fontFamily = PlusJakartaSans,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 13.sp,
                                        color = if (isDarkTheme) EmeraldLight else LightEmeraldDark,
                                        letterSpacing = 0.5.sp
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "Instant Access • No ID or Password Required",
                                        fontFamily = PlusJakartaSans,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 11.sp,
                                        color = if (isDarkTheme) CrispWhite else LightTextHeadline
                                    )
                                }

                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (isDarkTheme) EmeraldPrimary else LightEmerald)
                                        .padding(horizontal = 10.dp, vertical = 6.dp)
                                ) {
                                    Text(
                                        text = "Open Demo →",
                                        fontFamily = PlusJakartaSans,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp,
                                        color = if (isDarkTheme) Slate950 else CrispWhite
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(22.dp))

                        // 1. Name of the Organisation
                        FormLabel("1. Name of the Organisation *", isDarkTheme)
                        OutlinedTextField(
                            value = orgName,
                            onValueChange = {
                                orgName = it
                                errorMessage = null
                            },
                            placeholder = { Text("e.g., Apex Precision Castings Pvt Ltd", fontFamily = PlusJakartaSans, color = if (isDarkTheme) Slate400 else Color(0xFF94A3B8)) },
                            leadingIcon = { Icon(Icons.Default.Business, contentDescription = null, tint = if (isDarkTheme) EmeraldLight else LightEmerald) },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            shape = RoundedCornerShape(12.dp),
                            colors = formTextFieldColors(isDarkTheme),
                            modifier = Modifier.fillMaxWidth().testTag("input_signup_org_name")
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // 2. Name of the Owner
                        FormLabel("2. Name of the Owner *", isDarkTheme)
                        OutlinedTextField(
                            value = ownerName,
                            onValueChange = {
                                ownerName = it
                                errorMessage = null
                            },
                            placeholder = { Text("e.g., Vikram Deshmukh", fontFamily = PlusJakartaSans, color = if (isDarkTheme) Slate400 else Color(0xFF94A3B8)) },
                            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = if (isDarkTheme) EmeraldLight else LightEmerald) },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            shape = RoundedCornerShape(12.dp),
                            colors = formTextFieldColors(isDarkTheme),
                            modifier = Modifier.fillMaxWidth().testTag("input_signup_owner_name")
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // 3. What Type of Organisation is it (Interactive Dropdown)
                        FormLabel("3. What Type of Organisation is it? *", isDarkTheme)
                        ExposedDropdownMenuBox(
                            expanded = orgTypeExpanded,
                            onExpandedChange = { orgTypeExpanded = !orgTypeExpanded },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedTextField(
                                value = selectedOrgType,
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = orgTypeExpanded) },
                                shape = RoundedCornerShape(12.dp),
                                colors = formTextFieldColors(isDarkTheme),
                                modifier = Modifier
                                    .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                                    .fillMaxWidth()
                                    .testTag("dropdown_org_type")
                            )

                            ExposedDropdownMenu(
                                expanded = orgTypeExpanded,
                                onDismissRequest = { orgTypeExpanded = false },
                                modifier = Modifier.background(if (isDarkTheme) Color(0xFF0F1A15) else Color.White)
                            ) {
                                OrganizationTypes.forEach { type ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = type,
                                                fontFamily = PlusJakartaSans,
                                                fontWeight = if (selectedOrgType == type) FontWeight.Bold else FontWeight.Medium,
                                                color = if (isDarkTheme) CrispWhite else LightTextHeadline
                                            )
                                        },
                                        onClick = {
                                            selectedOrgType = type
                                            orgTypeExpanded = false
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // 4. Nature of Organisation (Inbuilt option chooser)
                        FormLabel("4. Nature of Organisation (Inbuilt Options) *", isDarkTheme)
                        ExposedDropdownMenuBox(
                            expanded = orgNatureExpanded,
                            onExpandedChange = { orgNatureExpanded = !orgNatureExpanded },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedTextField(
                                value = selectedOrgNature,
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = orgNatureExpanded) },
                                shape = RoundedCornerShape(12.dp),
                                colors = formTextFieldColors(isDarkTheme),
                                modifier = Modifier
                                    .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                                    .fillMaxWidth()
                                    .testTag("dropdown_org_nature")
                            )

                            ExposedDropdownMenu(
                                expanded = orgNatureExpanded,
                                onDismissRequest = { orgNatureExpanded = false },
                                modifier = Modifier.background(if (isDarkTheme) Color(0xFF0F1A15) else Color.White)
                            ) {
                                OrganizationNatures.forEach { nature ->
                                    DropdownMenuItem(
                                        text = {
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Text(
                                                    text = nature,
                                                    fontFamily = PlusJakartaSans,
                                                    fontWeight = if (selectedOrgNature == nature) FontWeight.Bold else FontWeight.Medium,
                                                    color = if (isDarkTheme) CrispWhite else LightTextHeadline
                                                )
                                                if (selectedOrgNature == nature) {
                                                    Icon(
                                                        imageVector = Icons.Default.Check,
                                                        contentDescription = null,
                                                        tint = if (isDarkTheme) EmeraldLight else LightEmerald,
                                                        modifier = Modifier.size(16.dp)
                                                    )
                                                }
                                            }
                                        },
                                        onClick = {
                                            selectedOrgNature = nature
                                            orgNatureExpanded = false
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // 5. Phone Number
                        FormLabel("5. Phone Number *", isDarkTheme)
                        OutlinedTextField(
                            value = phone,
                            onValueChange = {
                                phone = it
                                errorMessage = null
                            },
                            placeholder = { Text("e.g., +91 98234 56789", fontFamily = PlusJakartaSans, color = if (isDarkTheme) Slate400 else Color(0xFF94A3B8)) },
                            leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = if (isDarkTheme) EmeraldLight else LightEmerald) },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next),
                            shape = RoundedCornerShape(12.dp),
                            colors = formTextFieldColors(isDarkTheme),
                            modifier = Modifier.fillMaxWidth().testTag("input_signup_phone")
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // 6. Email ID
                        FormLabel("6. Email ID *", isDarkTheme)
                        OutlinedTextField(
                            value = email,
                            onValueChange = {
                                email = it
                                errorMessage = null
                            },
                            placeholder = { Text("e.g., operations@apexcastings.in", fontFamily = PlusJakartaSans, color = if (isDarkTheme) Slate400 else Color(0xFF94A3B8)) },
                            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = if (isDarkTheme) EmeraldLight else LightEmerald) },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                            shape = RoundedCornerShape(12.dp),
                            colors = formTextFieldColors(isDarkTheme),
                            modifier = Modifier.fillMaxWidth().testTag("input_signup_email")
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // 7. Password
                        FormLabel("7. Account Password *", isDarkTheme)
                        OutlinedTextField(
                            value = password,
                            onValueChange = {
                                password = it
                                errorMessage = null
                            },
                            placeholder = { Text("Create a secure password", fontFamily = PlusJakartaSans, color = if (isDarkTheme) Slate400 else Color(0xFF94A3B8)) },
                            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = if (isDarkTheme) EmeraldLight else LightEmerald) },
                            trailingIcon = {
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                        contentDescription = if (passwordVisible) "Hide password" else "Show password",
                                        tint = if (isDarkTheme) Slate400 else Color(0xFF64748B)
                                    )
                                }
                            },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                            shape = RoundedCornerShape(12.dp),
                            colors = formTextFieldColors(isDarkTheme),
                            modifier = Modifier.fillMaxWidth().testTag("input_signup_password")
                        )

                        if (errorMessage != null) {
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = errorMessage ?: "",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = Color(0xFFEF4444)
                            )
                        }

                        Spacer(modifier = Modifier.height(26.dp))

                        // Submit Button
                        Button(
                            onClick = {
                                if (orgName.isBlank()) {
                                    errorMessage = "Please enter the Name of the Organisation."
                                } else if (ownerName.isBlank()) {
                                    errorMessage = "Please enter the Name of the Owner."
                                } else if (phone.isBlank()) {
                                    errorMessage = "Please enter a valid Phone Number."
                                } else if (email.isBlank() || !email.contains("@")) {
                                    errorMessage = "Please enter a valid Email ID."
                                } else if (password.length < 4) {
                                    errorMessage = "Please enter a password with at least 4 characters."
                                } else {
                                    onSignUpSuccess(
                                        orgName.trim(),
                                        ownerName.trim(),
                                        selectedOrgType,
                                        selectedOrgNature,
                                        phone.trim(),
                                        email.trim(),
                                        password
                                    )
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isDarkTheme) EmeraldPrimary else LightEmerald
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp)
                                .shadow(
                                    elevation = 12.dp,
                                    shape = RoundedCornerShape(12.dp),
                                    ambientColor = Color(0x5010B981),
                                    spotColor = Color(0x8010B981)
                                )
                                .testTag("btn_submit_signup")
                        ) {
                            Text(
                                text = "Create Enterprise Profile",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 15.sp,
                                color = if (isDarkTheme) Slate950 else CrispWhite
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Already have account? Log In
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Already registered? ",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp,
                                color = if (isDarkTheme) Color(0xBF94A3B8) else Color(0xFF64748B)
                            )

                            Text(
                                text = "Log In",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 13.sp,
                                color = if (isDarkTheme) EmeraldLight else LightEmerald,
                                modifier = Modifier
                                    .clickable { onNavigateToLogin() }
                                    .testTag("link_navigate_to_login")
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FormLabel(text: String, isDarkTheme: Boolean) {
    Text(
        text = text,
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
        color = if (isDarkTheme) CrispWhite else LightTextHeadline
    )
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun formTextFieldColors(isDarkTheme: Boolean) = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = if (isDarkTheme) EmeraldLight else LightEmerald,
    unfocusedBorderColor = if (isDarkTheme) Color(0x40334155) else LightBorderStrong,
    focusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
    unfocusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
    focusedContainerColor = if (isDarkTheme) Color(0x330B120F) else Color(0xFFF8FAFC),
    unfocusedContainerColor = if (isDarkTheme) Color(0x330B120F) else Color(0xFFF8FAFC)
)
