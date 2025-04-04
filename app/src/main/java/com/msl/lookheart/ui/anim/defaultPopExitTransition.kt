package com.msl.lookheart.ui.anim

import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import com.msl.lookheart.enums.ui.SlideType

fun defaultPopExitTransition(
    slideType: SlideType,
    tweenDuration: Int
): ExitTransition {
    val fadeOut = fadeOut(animationSpec = tween(tweenDuration))

    val slideOut = when (slideType) {
        SlideType.VERTICALLY -> slideOutVertically(
            targetOffsetY = { fullHeight ->
                fullHeight  // 아래로 슬라이드
            },
            animationSpec = tween(tweenDuration)
        )
        SlideType.HORIZONTALLY -> slideOutHorizontally(
            targetOffsetX = { fullWidth ->
                fullWidth // 오른쪽으로 슬라이드
            },
            animationSpec = tween(tweenDuration)
        )
    }

    return fadeOut + slideOut
}