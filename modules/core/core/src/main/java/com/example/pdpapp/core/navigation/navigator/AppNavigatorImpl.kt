package com.example.pdpapp.core.navigation.navigator

import androidx.navigation.NavHostController
import com.example.pdpapp.core.di.scope.AndroidContextScope
import com.example.pdpapp.core.presentation.screen.route.ScreenRoute
import javax.inject.Inject

@AndroidContextScope
class AppNavigatorImpl @Inject constructor() : AppNavigator {

    private var navHostController: NavHostController? = null

    override fun initNavController(navController: NavHostController) {
        navHostController = navController
    }

    override fun navigate(route: ScreenRoute) {
        navHostController?.navigate(route.routeName)
    }

    override fun destroyNavController() {
        navHostController = null
    }
}