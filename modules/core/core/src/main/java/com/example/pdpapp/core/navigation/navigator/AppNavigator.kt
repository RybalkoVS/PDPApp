package com.example.pdpapp.core.navigation.navigator

import androidx.navigation.NavHostController
import com.example.pdpapp.core.presentation.screen.route.ScreenRoute

interface AppNavigator {

    fun initNavController(navController: NavHostController)

    fun navigate(route: ScreenRoute)

    fun destroyNavController()
}