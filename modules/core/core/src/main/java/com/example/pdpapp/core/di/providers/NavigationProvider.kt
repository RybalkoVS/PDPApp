package com.example.pdpapp.core.di.providers

import com.example.pdpapp.core.navigation.navigator.AppNavigator

interface NavigationProvider {

    fun provideAppNavigator(): AppNavigator
}