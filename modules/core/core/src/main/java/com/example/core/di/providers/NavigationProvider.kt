package com.example.core.di.providers

import com.example.core.navigation.AppNavigator

interface NavigationProvider {

    fun provideAppNavigator(): AppNavigator
}