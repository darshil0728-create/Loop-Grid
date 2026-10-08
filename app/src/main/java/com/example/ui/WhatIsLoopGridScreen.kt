package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CrispWhite
import com.example.ui.theme.DarkBgBase
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.LightBgBase
import com.example.ui.theme.LightTextHeadline
import com.example.ui.theme.PlusJakartaSans

/**
 * "WHAT IS LOOPGRID" About Us Page.
 * Kept blank as explicitly requested by user:
 * "this is about us page about the app . Do not write anything write now in this page keep it blank ."
 */
@Composable
fun WhatIsLoopGridScreen(
    isDarkTheme: Boolean,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Hardware & gesture back handler
    BackHandler {
        onBack()
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = if (isDarkTheme) DarkBgBase else LightBgBase
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .statusBarsPadding()
        ) {
            // Minimalist Top Bar with Back Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier.testTag("btn_back_to_home")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back to Home",
                        tint = if (isDarkTheme) CrispWhite else LightTextHeadline
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "WHAT IS LOOPGRID",
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    color = if (isDarkTheme) CrispWhite else LightTextHeadline,
                    letterSpacing = 0.5.sp,
                    modifier = Modifier.testTag("what_is_loopgrid_title")
                )
            }

            // Blank canvas area (as requested)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("what_is_loopgrid_blank_canvas")
            )
        }
    }
}
