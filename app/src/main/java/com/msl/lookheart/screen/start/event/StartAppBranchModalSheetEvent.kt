package com.msl.lookheart.screen.start.event

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.msl.lookheart.screen.start.composable.StartAppBranchModalSheet
import com.msl.lookheart.screen.start.mvvm.StartAppViewModel

@Composable
fun StartAppBranchModalSheetEvent(
    viewModel: StartAppViewModel,
) {
    /** state flow **/
    val showModalSheet by viewModel.branchModalSheet.collectAsState()

    StartAppBranchModalSheet(
        visible = showModalSheet,
        moveLogin = {
            viewModel.moveLoginScreen()
        },
        moveSignup = {
            viewModel.moveSignupScreen()
        }
    )
}