package com.example.pdpapp.core.di.providers

import com.example.pdpapp.core.presentation.dialogmanager.DialogManagerContract

interface DialogsProvider {

    fun provideDialogManager(): DialogManagerContract
}