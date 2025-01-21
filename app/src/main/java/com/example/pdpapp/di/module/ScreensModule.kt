package com.example.pdpapp.di.module

import com.example.pdpapp.contentprovider.screen.ContentProviderScreen
import com.example.pdpapp.core.presentation.screen.Screen
import com.example.pdpapp.rootscreen.RootScreen
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
}