package com.example.core.navigation.navigator

import androidx.navigation.NavHostController
import com.example.core.navigation.screen.route.ScreenRoute
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppNavigatorImpl @Inject constructor(): AppNavigator {

    private var navHostController: NavHostController? = null

    override fun initNavController(navController: NavHostController) {
        navHostController = navController
    }

    override fun navigate(route: ScreenRoute) {
        navHostController?.navigate(route.routeName)
    }
}