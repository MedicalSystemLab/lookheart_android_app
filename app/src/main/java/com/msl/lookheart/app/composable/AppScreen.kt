package com.msl.lookheart.app.composable

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import com.msl.lookheart.app.viewModel.SharedViewModel
import com.msl.lookheart.manager.DialogManager
import com.msl.lookheart.manager.LoadingManager
import com.msl.lookheart.screen.start.mvvm.StartAppViewModel
import com.msl.lookheart.ui.custom.dialog.AppDialog
import com.msl.lookheart.ui.custom.progressbar.AppLoadingBar
import com.msl.lookheart.utils.getActivityOrNull
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel
import kotlin.contracts.contract

@Composable
fun AppScreen(sharedViewModel: SharedViewModel) {
    /** screens **/
    Scaffold(
        content = { innerPadding ->
            AppNavHost(
                sharedViewModel = sharedViewModel,
                innerPadding = innerPadding,
            )
        }
    )

    /** Back Button Event **/
    AppBackHandler(sharedViewModel)

    /** Dialog **/
    AppDialog()

    /** AppLoadingBar **/
    AppLoadingBar()
}