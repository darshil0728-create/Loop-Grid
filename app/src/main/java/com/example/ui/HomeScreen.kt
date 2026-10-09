package com.example.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ui.components.AiIntelligenceSection
import com.example.ui.components.AuthModalDialog
import com.example.ui.components.ExploreDemoTour
import com.example.ui.components.ExploreWhatWeDoSheet
import com.example.ui.components.HowItWorksSection
import com.example.ui.components.IndustrialFactoryBackdrop
import com.example.ui.components.LoopGridHeader
import com.example.ui.components.LoopGridHero
import com.example.ui.components.LoopGridLiveStatsBar
import com.example.ui.components.MsmePillarsSection
import com.example.ui.components.MsmeShowcaseSection
import com.example.ui.components.RegisterFeedDialog
import com.example.ui.components.StatDetailDialog
import com.example.ui.components.StatType
import com.example.ui.components.SustainabilitySection
import com.example.ui.components.TransformCtaAndFooterSection
import com.example.ui.theme.DarkBgBase
import com.example.ui.theme.LightBgBase
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    onExploreHowLoopGridWorksClick: () -> Unit = {},
    onLogInClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    // Dialog & Sheet States
    var showRegisterDialog by remember { mutableStateOf(false) }
    var showExploreSheet by remember { mutableStateOf(false) }
    var showDemoTour by remember { mutableStateOf(false) }
    var showAuthModal by remember { mutableStateOf(false) }
    var authIsSignUp by remember { mutableStateOf(false) }
    var activeStatDetail by remember { mutableStateOf<StatType?>(null) }

    // Dynamic Live Stats Counter (simulating live database sync)
    var activeDistrictsCount by remember { mutableIntStateOf(48) }
    var wasteRemovedTons by remember { mutableDoubleStateOf(3420.0) }
    var msmePartnersCount by remember { mutableIntStateOf(128) }
    var savingsLakhs by remember { mutableDoubleStateOf(8.4) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = if (isDarkTheme) DarkBgBase else LightBgBase,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(bottom = 16.dp)
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Main Vertical Scrollable Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // SECTION 1: MSME Factory Picture as the main and full-space background over Section 1
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Industrial MSME Factory Photo Background for Section 1
                    IndustrialFactoryBackdrop(
                        isDarkTheme = isDarkTheme,
                        modifier = Modifier.matchParentSize()
                    )

                    // Foreground Content of Section 1
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .statusBarsPadding(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Header / Navigation
                        LoopGridHeader(
                            isDarkTheme = isDarkTheme,
                            onToggleTheme = onToggleTheme,
                            onExploreDemoClick = { showDemoTour = true },
                            onLogInClick = onLogInClick,
                            onSignUpClick = onSignUpClick,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        // Centered Badge, Massive Hero Headlines, Subtitle, Buttons, & Social Proof
                        LoopGridHero(
                            isDarkTheme = isDarkTheme,
                            onRegisterFeedClick = { showRegisterDialog = true },
                            onExploreWhatWeDoClick = { showExploreSheet = true },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(36.dp))

                        // Opaque Live Stats Bar (Lower Screen of Section 1)
                        LoopGridLiveStatsBar(
                            isDarkTheme = isDarkTheme,
                            onStatClick = { stat ->
                                activeStatDetail = stat
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(36.dp))
                    }
                }

                // SECTION 2: HOW LOOPGRID WORKS (Light-dark background with green tint, 6-step journey boxes & Start Your Journey button)
                HowItWorksSection(
                    isDarkTheme = isDarkTheme,
                    onStartJourneyClick = {
                        // Connected directly to "REGISTER A FEED" flow as requested
                        showRegisterDialog = true
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                // SECTION 3: MSME ON-GROUND SHOWCASE (Dark green section with framed photo keeping space in the border)
                MsmeShowcaseSection(
                    isDarkTheme = isDarkTheme,
                    modifier = Modifier.fillMaxWidth()
                )

                // SECTION 4: MAKING SUSTAINABILITY WORK FOR MSMEs
                SustainabilitySection(
                    isDarkTheme = isDarkTheme,
                    onExploreHowLoopGridWorksClick = onExploreHowLoopGridWorksClick,
                    modifier = Modifier.fillMaxWidth()
                )

                // SECTION 5: AI THAT TURNS EVIDENCE INTO ACTION
                AiIntelligenceSection(
                    isDarkTheme = isDarkTheme,
                    modifier = Modifier.fillMaxWidth()
                )

                // SECTION 6: PRACTICAL SUSTAINABILITY & 3 PILLARS
                MsmePillarsSection(
                    isDarkTheme = isDarkTheme,
                    modifier = Modifier.fillMaxWidth()
                )

                // SECTION 7: DON'T JUST SCRAP IT, TRANSFORM IT & FOOTER
                TransformCtaAndFooterSection(
                    isDarkTheme = isDarkTheme,
                    onRegisterFeedClick = { showRegisterDialog = true },
                    onExploreWhatWeDoClick = { showExploreSheet = true },
                    modifier = Modifier.fillMaxWidth()
                )

                // Bottom padding to ensure comfortable spacing above gesture navigation
                Spacer(modifier = Modifier.navigationBarsPadding())
            }

            // Interactive Dialogs & Modals
            if (showRegisterDialog) {
                RegisterFeedDialog(
                    isDarkTheme = isDarkTheme,
                    onDismiss = { showRegisterDialog = false },
                    onSubmitSuccess = { material, tons, district ->
                        wasteRemovedTons += tons
                        savingsLakhs += (tons * 0.04)
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Feed registered: $tons Tons of $material in $district!",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                )
            }

            if (showExploreSheet) {
                ExploreWhatWeDoSheet(
                    isDarkTheme = isDarkTheme,
                    onDismiss = { showExploreSheet = false },
                    onRegisterFeedAction = { showRegisterDialog = true }
                )
            }

            if (showDemoTour) {
                ExploreDemoTour(
                    isDarkTheme = isDarkTheme,
                    onDismiss = { showDemoTour = false },
                    onSimulateTransaction = {
                        wasteRemovedTons += 12.0
                        msmePartnersCount += 1
                        savingsLakhs += 0.84
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Live database updated: +12 Tons diverted to Deccan Smelters!",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                )
            }

            if (showAuthModal) {
                AuthModalDialog(
                    initialIsSignUp = authIsSignUp,
                    isDarkTheme = isDarkTheme,
                    onDismiss = { showAuthModal = false },
                    onAuthSuccess = { user ->
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Welcome to LoopGrid Circular Network, $user!",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                )
            }

            activeStatDetail?.let { statType ->
                StatDetailDialog(
                    statType = statType,
                    isDarkTheme = isDarkTheme,
                    onDismiss = { activeStatDetail = null }
                )
            }
        }
    }
}
