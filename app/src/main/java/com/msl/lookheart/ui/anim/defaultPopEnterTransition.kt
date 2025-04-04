package com.msl.lookheart.ui.anim


import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import com.msl.lookheart.enums.ui.SlideType

fun defaultPopEnterTransition(
    slideType: SlideType,
    tweenDuration: Int
): EnterTransition {
    val fadeIn = fadeIn(animationSpec = tween(tweenDuration))

    val slideIn = when (slideType) {
        SlideType.VERTICALLY -> slideInVertically(
            initialOffsetY = { fullHeight ->
                -fullHeight // 위에서 아래로 슬라이드
            },
            animationSpec = tween(tweenDuration)
        )
        SlideType.HORIZONTALLY -> slideInHorizontally(
            initialOffsetX = { fullWidth ->
                -fullWidth // 왼쪽에서 오른쪽으로 슬라이드
            },
            animationSpec = tween(tweenDuration)
        )
    }

    return fadeIn + slideIn
}