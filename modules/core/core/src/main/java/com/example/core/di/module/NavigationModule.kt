package com.example.core.di.module

import com.example.core.navigation.navigator.AppNavigator
import com.example.core.navigation.navigator.AppNavigatorImpl
import dagger.Binds
import dagger.Module

@Module
interface NavigationModule {

    @Binds
    fun provideNavigator(navigator: AppNavigatorImpl): AppNavigator
}