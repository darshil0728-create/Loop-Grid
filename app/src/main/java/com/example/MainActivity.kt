package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.data.EnterpriseRepository
import com.example.ui.EnterpriseDashboardScreen
import com.example.ui.HomeScreen
import com.example.ui.LoginScreen
import com.example.ui.SignUpScreen
import com.example.ui.WhatIsLoopGridScreen
import com.example.ui.theme.LoopGridTheme

enum class AppScreen {
    HOME,
    WHAT_IS_LOOPGRID,
    LOGIN,
    SIGNUP,
    DASHBOARD
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDarkTheme by remember { mutableStateOf(true) }
            var currentScreen by remember { mutableStateOf(AppScreen.HOME) }

            val context = LocalContext.current
            val enterpriseRepo = remember { EnterpriseRepository.getInstance(context) }
            val currentProfile by enterpriseRepo.currentProfile.collectAsState()

            LoopGridTheme(darkTheme = isDarkTheme) {
                when (currentScreen) {
                    AppScreen.HOME -> {
                        HomeScreen(
                            isDarkTheme = isDarkTheme,
                            onToggleTheme = { isDarkTheme = !isDarkTheme },
                            onExploreHowLoopGridWorksClick = {
                                currentScreen = AppScreen.WHAT_IS_LOOPGRID
                            },
                            onExploreDemoClick = {
                                enterpriseRepo.loadDemoAccount()
                                currentScreen = AppScreen.DASHBOARD
                            },
                            onLogInClick = {
                                currentScreen = AppScreen.LOGIN
                            },
                            onSignUpClick = {
                                currentScreen = AppScreen.SIGNUP
                            }
                        )
                    }

                    AppScreen.WHAT_IS_LOOPGRID -> {
                        WhatIsLoopGridScreen(
                            isDarkTheme = isDarkTheme,
                            onBack = { currentScreen = AppScreen.HOME }
                        )
                    }

                    AppScreen.LOGIN -> {
                        LoginScreen(
                            isDarkTheme = isDarkTheme,
                            onLoginSuccess = { enterpriseName ->
                                enterpriseRepo.validateLogin(enterpriseName, "")
                                currentScreen = AppScreen.DASHBOARD
                            },
                            onNavigateToSignUp = {
                                currentScreen = AppScreen.SIGNUP
                            },
                            onExploreDemoClick = {
                                enterpriseRepo.loadDemoAccount()
                                currentScreen = AppScreen.DASHBOARD
                            },
                            onBack = { currentScreen = AppScreen.HOME }
                        )
                    }

                    AppScreen.SIGNUP -> {
                        SignUpScreen(
                            isDarkTheme = isDarkTheme,
                            onSignUpSuccess = { orgName, ownerName, role, orgType, orgNature, phone, email, password ->
                                enterpriseRepo.registerNewEnterprise(
                                    enterpriseName = orgName,
                                    ownerName = ownerName,
                                    role = role,
                                    orgType = orgType,
                                    orgNature = orgNature,
                                    phone = phone,
                                    email = email,
                                    password = password
                                )
                                currentScreen = AppScreen.DASHBOARD
                            },
                            onNavigateToLogin = {
                                currentScreen = AppScreen.LOGIN
                            },
                            onExploreDemoClick = {
                                enterpriseRepo.loadDemoAccount()
                                currentScreen = AppScreen.DASHBOARD
                            },
                            onBack = { currentScreen = AppScreen.HOME }
                        )
                    }

                    AppScreen.DASHBOARD -> {
                        EnterpriseDashboardScreen(
                            initialProfile = currentProfile,
                            isDarkTheme = isDarkTheme,
                            onSaveProfile = { profile, isDraft ->
                                enterpriseRepo.saveProfile(profile, isDraft = isDraft)
                            },
                            onLogout = {
                                currentScreen = AppScreen.HOME
                            },
                            onBackToHome = {
                                currentScreen = AppScreen.HOME
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LoopGridTheme(darkTheme = true) {
        HomeScreen(isDarkTheme = true, onToggleTheme = {})
    }
}
