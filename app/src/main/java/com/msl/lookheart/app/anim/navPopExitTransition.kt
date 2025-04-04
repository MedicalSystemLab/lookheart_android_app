package com.msl.lookheart.ui.anim.nav
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import com.msl.lookheart.enums.ui.SlideType

fun navPopExitTransition(
    slideType: SlideType,
    tweenDuration: Int
): ExitTransition {
    return when (slideType) {
        SlideType.VERTICALLY -> slideOutVertically(
            targetOffsetY = { it }, // 아래로 나감
            animationSpec = tween(tweenDuration)
        )
        SlideType.HORIZONTALLY -> slideOutHorizontally(
            targetOffsetX = { it }, // 오른쪽으로 나감
            animationSpec = tween(tweenDuration)
        )
    }
}