package com.msl.lookheart.ui.custom.text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density

@Composable
fun FixedFontSizeContent(
    content: @Composable () -> Unit
) {
    val currentDensity = LocalDensity.current

    CompositionLocalProvider(
        LocalDensity provides Density(
            density = currentDensity.density,
            fontScale = 1.0f    // 사용자 설정 무시
        )
    ) {
        content()
    }
}