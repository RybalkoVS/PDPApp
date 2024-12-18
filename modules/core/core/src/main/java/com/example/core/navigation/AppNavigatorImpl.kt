package com.example.core.navigation

import androidx.navigation.NavHostController

class AppNavigatorImpl : AppNavigator {

    private var navHostController: NavHostController? = null

    fun initNavController(navController: NavHostController) {
        navHostController = navController
    }

    override fun navigate(route: ScreenRoute) {
        navHostController?.navigate(route.route)
    }
}