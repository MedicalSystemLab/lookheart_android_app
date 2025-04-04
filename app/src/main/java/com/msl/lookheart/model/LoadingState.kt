package com.msl.lookheart.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.msl.lookheart.ui.theme.MyBlue

data class LoadingState(
    val isVisible: Boolean = false,
    val color: Color = MyBlue,
    val isNotTouchable: Boolean = true,
    val backgroundEnable: Boolean = false,
    val size: Dp = 64.dp
)
