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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.ui.theme.CrispWhite
import com.example.ui.theme.DarkBgBase
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.LightBgBase
import com.example.ui.theme.LightBorder
import com.example.ui.theme.LightBorderStrong
import com.example.ui.theme.LightEmerald
import com.example.ui.theme.LightSurfaceCard
import com.example.ui.theme.LightTextHeadline
import com.example.ui.theme.PlusJakartaSans
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate950

/**
 * Enterprise Login Screen:
 * - Asks for Name of your Enterprise and Password
 * - Takes user to the Enterprise Dashboard upon login
 */
@Composable
fun LoginScreen(
    isDarkTheme: Boolean,
    onLoginSuccess: (enterpriseName: String) -> Unit,
    onNavigateToSignUp: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onBack() }

    var enterpriseName by remember { mutableStateOf("Shree Balaji Fabrication Works") }
    var password by remember { mutableStateOf("password123") }
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
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top Navigation Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("btn_login_back")
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

                Spacer(modifier = Modifier.height(28.dp))

                // Card Container
                Box(
                    modifier = Modifier
                        .widthIn(max = 480.dp)
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
                        .padding(28.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        // Title
                        Text(
                            text = "Log In to Enterprise Portal",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 24.sp,
                            lineHeight = 30.sp,
                            color = if (isDarkTheme) CrispWhite else LightTextHeadline
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Welcome back! Enter your enterprise name and password to access your dashboard.",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            lineHeight = 19.sp,
                            color = if (isDarkTheme) Color(0xBF94A3B8) else Color(0xFF64748B)
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // Field 1: Name of your Enterprise
                        Text(
                            text = "Name of your Enterprise",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = if (isDarkTheme) CrispWhite else LightTextHeadline
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        OutlinedTextField(
                            value = enterpriseName,
                            onValueChange = {
                                enterpriseName = it
                                errorMessage = null
                            },
                            placeholder = {
                                Text(
                                    text = "e.g., Shree Balaji Fabrication Works",
                                    fontFamily = PlusJakartaSans,
                                    fontWeight = FontWeight.Medium,
                                    color = if (isDarkTheme) Slate400 else Color(0xFF94A3B8)
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Business,
                                    contentDescription = null,
                                    tint = if (isDarkTheme) EmeraldLight else LightEmerald
                                )
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = if (isDarkTheme) EmeraldLight else LightEmerald,
                                unfocusedBorderColor = if (isDarkTheme) Color(0x40334155) else LightBorderStrong,
                                focusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
                                unfocusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
                                focusedContainerColor = if (isDarkTheme) Color(0x330B120F) else Color(0xFFF8FAFC),
                                unfocusedContainerColor = if (isDarkTheme) Color(0x330B120F) else Color(0xFFF8FAFC)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("input_login_enterprise_name")
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        // Field 2: Password
                        Text(
                            text = "Password",
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = if (isDarkTheme) CrispWhite else LightTextHeadline
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        OutlinedTextField(
                            value = password,
                            onValueChange = {
                                password = it
                                errorMessage = null
                            },
                            placeholder = {
                                Text(
                                    text = "Enter your password",
                                    fontFamily = PlusJakartaSans,
                                    fontWeight = FontWeight.Medium,
                                    color = if (isDarkTheme) Slate400 else Color(0xFF94A3B8)
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = null,
                                    tint = if (isDarkTheme) EmeraldLight else LightEmerald
                                )
                            },
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
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    if (enterpriseName.isNotBlank() && password.isNotBlank()) {
                                        onLoginSuccess(enterpriseName.trim())
                                    } else {
                                        errorMessage = "Please enter both enterprise name and password."
                                    }
                                }
                            ),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = if (isDarkTheme) EmeraldLight else LightEmerald,
                                unfocusedBorderColor = if (isDarkTheme) Color(0x40334155) else LightBorderStrong,
                                focusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
                                unfocusedTextColor = if (isDarkTheme) CrispWhite else LightTextHeadline,
                                focusedContainerColor = if (isDarkTheme) Color(0x330B120F) else Color(0xFFF8FAFC),
                                unfocusedContainerColor = if (isDarkTheme) Color(0x330B120F) else Color(0xFFF8FAFC)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("input_login_password")
                        )

                        if (errorMessage != null) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = errorMessage ?: "",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = Color(0xFFEF4444)
                            )
                        }

                        Spacer(modifier = Modifier.height(26.dp))

                        // Submit Button: Log In
                        Button(
                            onClick = {
                                if (enterpriseName.isBlank()) {
                                    errorMessage = "Please enter the name of your enterprise."
                                } else if (password.isBlank()) {
                                    errorMessage = "Please enter your password."
                                } else {
                                    onLoginSuccess(enterpriseName.trim())
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isDarkTheme) EmeraldPrimary else LightEmerald
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .shadow(
                                    elevation = 10.dp,
                                    shape = RoundedCornerShape(12.dp),
                                    ambientColor = Color(0x5010B981),
                                    spotColor = Color(0x8010B981)
                                )
                                .testTag("btn_submit_login")
                        ) {
                            Text(
                                text = "Log In to Dashboard",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 15.sp,
                                color = if (isDarkTheme) Slate950 else CrispWhite
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Switch to Sign Up
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Don't have an account? ",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp,
                                color = if (isDarkTheme) Color(0xBF94A3B8) else Color(0xFF64748B)
                            )

                            Text(
                                text = "Sign Up",
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 13.sp,
                                color = if (isDarkTheme) EmeraldLight else LightEmerald,
                                modifier = Modifier
                                    .clickable { onNavigateToSignUp() }
                                    .testTag("link_navigate_to_signup")
                            )
                        }
                    }
                }
            }
        }
    }
}
