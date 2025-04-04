package com.msl.lookheart.screen.start.mvvm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.msl.lookheart.app.viewModel.SharedViewModel
import com.msl.lookheart.screen.start.composable.StartAppScreen
import com.msl.lookheart.screen.start.event.StartAppPermissionEvent
import com.msl.lookheart.screen.start.event.StartAppNavigationEvent
import com.msl.lookheart.screen.start.event.StartAppBranchModalSheetEvent
import com.msl.lookheart.screen.start.event.StartAppUIEvent
import org.koin.androidx.compose.getViewModel


@Composable
fun StartAppView(sharedViewModel: SharedViewModel) {
    // 1. ViewModel 초기화
    val viewModel: StartAppViewModel = getViewModel()

    // 2. 기본 화면 렌더링
    StartAppScreen()

    // 3. 이벤트 초기화 및 구독
    InitStartAppEvents(viewModel, sharedViewModel)

    // 4. 앱 시작 시 버전 체크 (일회성)
    LaunchedEffect(Unit) {
        viewModel.checkAppVersion()
    }
}

@Composable
private fun InitStartAppEvents(viewModel: StartAppViewModel, sharedViewModel: SharedViewModel) {
    StartAppBranchModalSheetEvent(viewModel)
    StartAppNavigationEvent(viewModel = viewModel, sharedViewModel = sharedViewModel)
    StartAppPermissionEvent(viewModel)
    StartAppUIEvent(viewModel)
}