package com.msl.lookheart.screen.start.event

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.msl.lookheart.app.composable.LocalNavController
import com.msl.lookheart.app.viewModel.SharedViewModel
import com.msl.lookheart.manager.DialogManager
import com.msl.lookheart.manager.LoadingManager
import com.msl.lookheart.screen.start.mvvm.StartAppViewModel
import com.msl.lookheart.screen.start.mvvm.StartAppViewModel.NavigationEvent.*
import com.msl.lookheart.ui.theme.MyRed
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel

@Composable
fun StartAppNavigationEvent(
    viewModel: StartAppViewModel,
    sharedViewModel: SharedViewModel,
) {
    /** state flow & shared flow **/
    val navigationEvent by viewModel.navigationEvent.collectAsState()

    /** Navi **/
    val navController = LocalNavController.current

    /** 네비게이션 처리 **/
    LaunchedEffect(navigationEvent) {
        navigationEvent?.let { event ->
            when (event) {
                ToMain -> {
//                    navController.navigate("main") {
//                        popUpTo("start") {
//                            inclusive = true
//                        }
//                    }
                }

                ToLogin -> {
//                    navController.navigate("login") {
//                        popUpTo("start") {
//                            inclusive = true
//                        }
//                    }
                }

                ToSignup -> {
//                    navController.navigate("login") {
//                        popUpTo("start") {
//                            inclusive = true
//                        }
//                    }
//
//                    navController.navigate("signup")
                }

                UpdateApp -> sharedViewModel.updateAppVersion()
                ExitApp -> sharedViewModel.onExitRequested()
            }
        }
    }
}