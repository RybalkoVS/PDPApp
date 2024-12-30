package com.example.core.di.providers

import com.example.core.navigation.navigator.AppNavigator

interface NavigationProvider {

    fun provideAppNavigator(): AppNavigator
}