package com.example.pdpapp.core.presentation.dialogmanager

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.pdpapp.core.presentation.dialog.Dialog
import javax.inject.Inject

class DialogManager @Inject constructor() : DialogManagerContract {

    override val dialogsQueue: MutableList<Dialog<*, *>> = mutableListOf()
    override var isDialogVisible: Boolean by mutableStateOf(false)
    override var currentDialog: Dialog<*, *>? by mutableStateOf(null)

    override fun showDialog(newDialog: Dialog<*, *>) {
        dialogsQueue.add(newDialog)
        currentDialog = newDialog
        isDialogVisible = true
    }

    override fun hideDialog() {
        dialogsQueue.removeAt(dialogsQueue.lastIndex)
        isDialogVisible = dialogsQueue.size > 0
        currentDialog = dialogsQueue.lastOrNull()
    }

    @Composable
    override fun Inflate() {
        if (isDialogVisible) {
            currentDialog?.Inflate()
        }
    }
}