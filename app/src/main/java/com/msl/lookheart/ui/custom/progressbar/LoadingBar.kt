package com.msl.lookheart.ui.custom.loadingbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.msl.lookheart.ui.theme.MyBlue

@Composable
fun LoadingBar(
    visible: Boolean,
    color: Color = MyBlue,
    isNotTouchable: Boolean = true,
    background: Boolean = false,
    strokeWidth: Dp = 3.dp,
    size: Dp = 40.dp
) {
    if (visible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = if (background) 0.3f else 0f))
                .then(
                    if (isNotTouchable)
                        Modifier.clickable(enabled = false) {}
                    else Modifier
                ),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = color,
                strokeWidth = strokeWidth,
                modifier = Modifier.size(size)
            )
        }
    }
}