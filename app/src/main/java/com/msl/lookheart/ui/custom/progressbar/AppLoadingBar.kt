package com.msl.lookheart.ui.custom.progressbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.msl.lookheart.manager.LoadingManager
import com.msl.lookheart.ui.custom.loadingbar.LoadingBar
import org.koin.androidx.compose.get

@Composable
fun AppLoadingBar() {
    /** manager **/
    val loadingManager: LoadingManager = get()

    /** state flow **/
    val loadingState by loadingManager.loadingState.collectAsState()

    /** loading bar **/
    LoadingBar(
        visible = loadingState.isVisible,
        color = loadingState.color
    )
}