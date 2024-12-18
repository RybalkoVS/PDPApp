package com.example.pdpapp.rootscreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.pdpapp.navigation.AppRoutes

@Composable
fun RootScreen(navController: NavHostController) {
    NavHost(
        startDestination = AppRoutes.Main,
        navController = navController
    ) {

    }
}