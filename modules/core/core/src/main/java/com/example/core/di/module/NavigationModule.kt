package com.example.core.di.module

import com.example.core.navigation.AppNavigator
import com.example.core.navigation.AppNavigatorImpl
import dagger.Module
import dagger.Provides

@Module
object NavigationModule {

    @Provides
    fun provideNavigator(): AppNavigator = AppNavigatorImpl()
}