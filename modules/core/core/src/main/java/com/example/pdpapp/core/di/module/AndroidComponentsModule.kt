package com.example.pdpapp.core.di.module

import android.content.ContentResolver
import android.content.Context
import androidx.activity.ComponentActivity
import com.example.pdpapp.core.di.scope.AndroidContextScope
import dagger.Module
import dagger.Provides

@Module
class AndroidComponentsModule(private val activity: ComponentActivity) {

    @AndroidContextScope
    @Provides
    fun provideAndroidContext(): Context {
        return activity.applicationContext
    }

    @AndroidContextScope
    @Provides
    fun provideContentResolver(): ContentResolver {
        return activity.contentResolver
    }
}