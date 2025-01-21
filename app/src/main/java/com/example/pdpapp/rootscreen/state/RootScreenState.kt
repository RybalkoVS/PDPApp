package com.example.pdpapp.rootscreen.state

import com.example.pdpapp.core.navigation.navigator.AppNavigator
import com.example.pdpapp.core.presentation.screen.route.Routes
import com.example.pdpapp.core.presentation.screen.state.ScreenState

class RootScreenState(override val appNavigator: AppNavigator): ScreenState() {

    fun getNonRootEnabledScreens(): List<Routes> {
        return Routes.entries
            .filter { it != Routes.Root && it.enabled }
    }

    fun navigateToSelectedSubProject(route: Routes){
        appNavigator.navigate(route)
    }
}