package com.example.pdpapp.core.di.module

import com.example.pdpapp.core.di.scope.AndroidContextScope
import com.example.pdpapp.core.presentation.dialogmanager.DialogManager
import com.example.pdpapp.core.presentation.dialogmanager.DialogManagerContract
import dagger.Binds
import dagger.Module

@Module
interface DialogsModule {

    @AndroidContextScope
    @Binds
    fun provideDialogManager(dialogManager: DialogManager): DialogManagerContract
}