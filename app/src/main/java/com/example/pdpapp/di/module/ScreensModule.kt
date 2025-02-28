package com.example.pdpapp.di.module

import com.example.pdpapp.animations.screen.AnimationsScreen
import com.example.pdpapp.animations.screen.state.AnimationsScreenState
import com.example.pdpapp.contentprovider.screen.ContentProviderScreen
import com.example.pdpapp.contentprovider.screen.state.ContentProviderScreenState
import com.example.pdpapp.core.presentation.screen.Screen
import com.example.pdpapp.rootscreen.RootScreen
import com.example.pdpapp.rootscreen.state.RootScreenState
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
interface ScreensModule {

    @Binds
    @IntoSet
    fun bindRootScreen(rootScreen: RootScreen): Screen<*>

    @Binds
    @IntoSet
    fun bindContentProviderScreen(contentProviderScreen: ContentProviderScreen): Screen<*>

    @Binds
    @IntoSet
    fun bindAnimationsScreen(animationsScreen: AnimationsScreen): Screen<*>
}