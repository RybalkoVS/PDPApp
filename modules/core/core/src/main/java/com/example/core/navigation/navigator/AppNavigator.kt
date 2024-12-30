package com.example.core.navigation.navigator

import androidx.navigation.NavHostController
import com.example.core.navigation.screen.route.ScreenRoute

interface AppNavigator {

    fun initNavController(navController: NavHostController)

    fun navigate(route: ScreenRoute)
}