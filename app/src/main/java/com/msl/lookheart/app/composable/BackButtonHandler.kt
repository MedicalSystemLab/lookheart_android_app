package com.msl.lookheart.app.composable

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import com.msl.lookheart.R
import com.msl.lookheart.app.viewModel.SharedViewModel
import com.msl.lookheart.manager.DialogManager
import org.koin.androidx.compose.get

@Composable
fun AppBackHandler(
    sharedViewModel: SharedViewModel
) {
    val dialogManager: DialogManager = get()

    BackHandler(enabled = true) {
        dialogManager.showDialog(
            titleResId = R.string.dialog_notification,
            messageResId = R.string.dialog_exit,
            confirmButtonResId = R.string.msg_exit,
            cancelButtonResID = R.string.msg_cancel_upper,
            onConfirm = {
                sharedViewModel.onExitRequested()
            }
        )
    }
}