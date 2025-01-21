package com.example.pdpapp.core.presentation.dialog

import androidx.compose.runtime.Composable
import com.example.pdpapp.core.presentation.dialog.decorator.DefaultDialogDecorator
import com.example.pdpapp.core.presentation.dialog.state.DialogState

abstract class Dialog<RESULT : DialogResultContract, STATE : DialogState<RESULT>> {

    abstract val dialogState: STATE
    abstract val content: @Composable () -> Unit

    open val dialogDecorator: @Composable (STATE, @Composable () -> Unit) -> Unit =
        { state, dialogContent ->
            DefaultDialogDecorator(
                state = state,
                content = dialogContent,
                onClose = { onClose() },
                onActionClick = { onDialogResult(it) }
            )
        }

    open fun onClose() {}

    open fun onDialogResult(result: RESULT? = null) {
        onClose()
    }

    @Composable
    fun Inflate() {
        dialogDecorator(dialogState, content)
    }
}