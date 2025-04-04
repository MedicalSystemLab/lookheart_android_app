package com.msl.lookheart.manager

import com.msl.lookheart.model.DialogState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.MutableStateFlow

class DialogManager {
    private val _dialogState = MutableStateFlow(DialogState())
    val dialogState: StateFlow<DialogState> = _dialogState.asStateFlow()

    fun showDialog(
        titleResId: Int,
        messageResId: Int,
        confirmButtonResId: Int,
        cancelButtonResID: Int? = null,
        onConfirm: (() -> Unit)? = null,
        onDismiss: (() -> Unit)? = null
    ) {
        DialogState(
            isVisible = true,
            titleResId = titleResId,
            messageResId = messageResId,
            confirmButtonResId = confirmButtonResId,
            cancelButtonResId = cancelButtonResID,
            onConfirm = {
                onConfirm?.invoke()
                dismissDialog()
            },
            onDismiss = {
                onDismiss?.invoke()
                dismissDialog()
            }
        )
    }

    fun showDialog(dialogState: DialogState) {
        _dialogState.value = dialogState.copy(
            onConfirm = {
                dialogState.onConfirm?.invoke()
                dismissDialog()
            },
            onDismiss = {
                dialogState.onDismiss?.invoke()
                dismissDialog()
                if (dialogState.dismissEnable) {
                    dialogState.onDismiss?.invoke()
                    dismissDialog()
                }
            }
        )
    }

    private fun dismissDialog() {
        _dialogState.value = DialogState(isVisible = false)
    }
}