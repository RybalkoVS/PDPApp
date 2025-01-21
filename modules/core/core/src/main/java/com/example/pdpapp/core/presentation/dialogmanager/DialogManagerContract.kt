package com.example.pdpapp.core.presentation.dialogmanager

import androidx.compose.runtime.Composable
import com.example.pdpapp.core.presentation.dialog.Dialog

interface DialogManagerContract {
    val dialogsQueue: MutableList<Dialog<*, *>>
    var isDialogVisible: Boolean
    var currentDialog: Dialog<*, *>?

    fun showDialog(newDialog: Dialog<*, *>)

    fun hideDialog()

    @Composable
    fun Inflate()
}