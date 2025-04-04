package com.msl.lookheart.manager

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.msl.lookheart.model.LoadingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoadingManager {
    private val _loadingState = MutableStateFlow(LoadingState())
    val loadingState: StateFlow<LoadingState> = _loadingState.asStateFlow()

    fun showLoadingBar(
        isVisible: Boolean,
        color: Color = com.msl.lookheart.ui.theme.MyBlue,
        isNotTouchable: Boolean = true,
        backgroundEnable: Boolean = false,
        size: Dp = 50.dp
    ) {
        _loadingState.value =
            LoadingState(
                isVisible = isVisible,
                color = color,
                isNotTouchable = isNotTouchable,
                backgroundEnable = backgroundEnable,
                size = size
            )
    }
}