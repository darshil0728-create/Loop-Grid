package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.CrispWhite
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.PlusJakartaSans
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900
import com.example.ui.theme.Slate950

@Composable
fun AuthModalDialog(
    initialIsSignUp: Boolean,
    isDarkTheme: Boolean,
    onDismiss: () -> Unit,
    onAuthSuccess: (userName: String) -> Unit
) {
    var isSignUp by remember { mutableStateOf(initialIsSignUp) }
    var email by remember { mutableStateOf("plant.manager@precisionmetals.in") }
    var password by remember { mutableStateOf("••••••••••••") }
    var companyName by remember { mutableStateOf("Precision Metals Ltd") }
    var role by remember { mutableStateOf("Enterprise Producer") }

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .widthIn(max = 440.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .border(
                    width = 1.dp,
                    color = if (isDarkTheme) Color(0x66334155) else Color(0xFFCBD5E1),
                    shape = RoundedCornerShape(20.dp)
                )
                .background(if (isDarkTheme) Color(0xF80B1120) else Color.White)
                .padding(24.dp)
                .testTag("auth_modal_dialog")
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Header & Close
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (isSignUp) "CREATE LOOPGRID ACCOUNT" else "LOG IN TO LOOPGRID",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 17.sp,
                        color = if (isDarkTheme) CrispWhite else Slate950
                    )

                    IconButton(onClick = onDismiss, modifier = Modifier.testTag("close_auth_modal")) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = if (isDarkTheme) Slate400 else Slate700
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Toggle tabs: Log In / Sign Up
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isDarkTheme) Slate900 else Color(0xFFF1F5F9))
                        .padding(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (!isSignUp) (if (isDarkTheme) Slate800 else Color.White) else Color.Transparent)
                            .clickable { isSignUp = false }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Log In",
                            fontFamily = PlusJakartaSans,
                            fontWeight = if (!isSignUp) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 13.sp,
                            color = if (!isSignUp) (if (isDarkTheme) CrispWhite else Slate950) else Slate400
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (isSignUp) EmeraldPrimary else Color.Transparent)
                            .clickable { isSignUp = true }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Sign Up",
                            fontFamily = PlusJakartaSans,
                            fontWeight = if (isSignUp) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 13.sp,
                            color = if (isSignUp) Slate950 else Slate400
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (isSignUp) {
                    Text(
                        text = "COMPANY / MSME NAME",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = if (isDarkTheme) Slate400 else Slate700
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = companyName,
                        onValueChange = { companyName = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("auth_company_name"),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = EmeraldPrimary,
                            unfocusedBorderColor = if (isDarkTheme) Slate700 else Color(0xFFCBD5E1),
                            focusedTextColor = if (isDarkTheme) CrispWhite else Slate950,
                            unfocusedTextColor = if (isDarkTheme) CrispWhite else Slate950
                        ),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                Text(
                    text = "WORK EMAIL",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = if (isDarkTheme) Slate400 else Slate700
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("auth_email"),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = EmeraldPrimary,
                        unfocusedBorderColor = if (isDarkTheme) Slate700 else Color(0xFFCBD5E1),
                        focusedTextColor = if (isDarkTheme) CrispWhite else Slate950,
                        unfocusedTextColor = if (isDarkTheme) CrispWhite else Slate950
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "PASSWORD",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = if (isDarkTheme) Slate400 else Slate700
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("auth_password"),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = EmeraldPrimary,
                        unfocusedBorderColor = if (isDarkTheme) Slate700 else Color(0xFFCBD5E1),
                        focusedTextColor = if (isDarkTheme) CrispWhite else Slate950,
                        unfocusedTextColor = if (isDarkTheme) CrispWhite else Slate950
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        onAuthSuccess(if (isSignUp) companyName else "Plant Lead")
                        onDismiss()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("auth_submit_btn"),
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = if (isSignUp) "CREATE ACCOUNT" else "SIGN IN",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Slate950
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Fast Quick Demo access
                Button(
                    onClick = {
                        onAuthSuccess("Demo MSME Lead")
                        onDismiss()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isDarkTheme) Color(0x33334155) else Color(0xFFF1F5F9)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Instant 1-Click Demo Login",
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = if (isDarkTheme) EmeraldLight else EmeraldDark
                    )
                }
            }
        }
    }
}
