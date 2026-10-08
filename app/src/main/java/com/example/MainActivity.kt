package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.HomeScreen
import com.example.ui.WhatIsLoopGridScreen
import com.example.ui.theme.LoopGridTheme

enum class AppScreen {
    HOME,
    WHAT_IS_LOOPGRID
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Dark theme is the default as requested
            var isDarkTheme by remember { mutableStateOf(true) }
            var currentScreen by remember { mutableStateOf(AppScreen.HOME) }

            LoopGridTheme(darkTheme = isDarkTheme) {
                when (currentScreen) {
                    AppScreen.HOME -> {
                        HomeScreen(
                            isDarkTheme = isDarkTheme,
                            onToggleTheme = { isDarkTheme = !isDarkTheme },
                            onExploreHowLoopGridWorksClick = {
                                currentScreen = AppScreen.WHAT_IS_LOOPGRID
                            }
                        )
                    }
                    AppScreen.WHAT_IS_LOOPGRID -> {
                        WhatIsLoopGridScreen(
                            isDarkTheme = isDarkTheme,
                            onBack = { currentScreen = AppScreen.HOME }
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
