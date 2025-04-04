package com.msl.lookheart.ui.custom.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.msl.lookheart.R
import com.msl.lookheart.manager.DialogManager
import org.koin.androidx.compose.get
import kotlin.let

@Composable
fun AppDialog() {
    /** manager **/
    val dialogManager: DialogManager = get()

    /** state flow **/
    val dialogState by dialogManager.dialogState.collectAsState()

    /** dialog **/
    BasicAlertDialog(
        visible = dialogState.isVisible,
        title = stringResource(dialogState.titleResId ?: R.string.dialog_notification),
        body = dialogState.messageResId?.let {
            stringResource(it)
        } ?: dialogState.message ?: stringResource(R.string.error_not_found),
        confirmText = stringResource(dialogState.confirmButtonResId ?: R.string.msg_ok),
        cancelText = if (dialogState.cancelButtonResId != null) stringResource(dialogState.cancelButtonResId!!) else null,
        onConfirm = { dialogState.onConfirm?.invoke() },
        onDismiss = { dialogState.onDismiss?.invoke() },
        dismissEnable = dialogState.dismissEnable
    )
}