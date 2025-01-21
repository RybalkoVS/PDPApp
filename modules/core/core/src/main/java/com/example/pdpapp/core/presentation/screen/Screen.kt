package com.example.pdpapp.core.presentation.screen

import androidx.annotation.CallSuper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.pdpapp.core.presentation.dialog.Dialog
import com.example.pdpapp.core.presentation.dialogmanager.DialogManagerContract
import com.example.pdpapp.core.presentation.screen.route.ScreenRoute
import com.example.pdpapp.core.presentation.screen.state.ScreenState

abstract class Screen<STATE : ScreenState> {
    abstract val route: ScreenRoute
    abstract val screenState: STATE
    abstract val dialogManager: DialogManagerContract
    abstract val view: @Composable () -> Unit

    @Composable
    fun Inflate() {
        LaunchedEffect(Unit) {
            screenState.init()
        }

        view()

        dialogManager.Inflate()
    }

    @CallSuper
    fun showDialog(newDialog: Dialog<*, *>) {
        dialogManager.showDialog(newDialog)
    }

    fun hideDialog() {
        dialogManager.hideDialog()
    }
}