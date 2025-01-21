package com.example.pdpapp.core.di.module

import com.example.pdpapp.core.di.scope.AndroidContextScope
import com.example.pdpapp.core.navigation.navigator.AppNavigator
import com.example.pdpapp.core.navigation.navigator.AppNavigatorImpl
import dagger.Binds
import dagger.Module

@Module
interface NavigationModule {

    @AndroidContextScope
    @Binds
    fun provideNavigator(navigator: AppNavigatorImpl): AppNavigator
}