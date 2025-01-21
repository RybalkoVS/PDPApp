package com.example.pdpapp.core.presentation.dialog.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.pdpapp.core.presentation.dialog.DialogResultContract

abstract class DialogState<RESULT : DialogResultContract> {
    abstract val titleRes: Int
    abstract val actionButtonTextRes: Int
    open var isActionButtonEnabled: Boolean by mutableStateOf(false)

    open fun getResult(): RESULT? = null
}