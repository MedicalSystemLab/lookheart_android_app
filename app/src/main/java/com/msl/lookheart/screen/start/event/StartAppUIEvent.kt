package com.msl.lookheart.screen.start.event

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.msl.lookheart.manager.DialogManager
import com.msl.lookheart.manager.LoadingManager
import com.msl.lookheart.screen.start.mvvm.StartAppViewModel
import com.msl.lookheart.ui.theme.MyRed
import org.koin.androidx.compose.get

@Composable
fun StartAppUIEvent(viewModel: StartAppViewModel) {
    /** manager **/
    val dialogManager: DialogManager = get()
    val loadingManager: LoadingManager = get()

    /** dialog **/
    LaunchedEffect(viewModel.dialog) {
        viewModel.dialog.collect { dialogState ->
            dialogManager.showDialog(dialogState)
        }
    }


    /** loading bar **/
    LaunchedEffect(viewModel.isLoading) {
        viewModel.isLoading.collect { isLoading ->
            if (isLoading) {
                loadingManager.showLoadingBar(
                    isVisible = true,
                    color = MyRed,
                )
            } else {
                loadingManager.showLoadingBar(
                    isVisible = false
                )
            }
        }
    }
}