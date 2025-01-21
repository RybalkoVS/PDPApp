package com.example.pdpapp.core.di.providers

import android.content.ContentResolver
import android.content.Context

interface AndroidComponentsProvider {

    fun provideAndroidContext(): Context

    fun provideContentResolver(): ContentResolver
}