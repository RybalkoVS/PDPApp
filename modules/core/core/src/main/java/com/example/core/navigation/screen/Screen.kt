package com.example.core.navigation.screen

import androidx.compose.runtime.Composable
import com.example.core.navigation.navigator.AppNavigator
import com.example.core.navigation.screen.route.ScreenRoute

interface Screen {
    val route: ScreenRoute
    val navigator: AppNavigator
    val view: @Composable () -> Unit
}