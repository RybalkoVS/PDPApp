package com.example.pdpapp.core.presentation.screen.state

import com.example.pdpapp.core.navigation.navigator.AppNavigator

abstract class ScreenState {
    abstract val appNavigator: AppNavigator

    open fun init() {}
}