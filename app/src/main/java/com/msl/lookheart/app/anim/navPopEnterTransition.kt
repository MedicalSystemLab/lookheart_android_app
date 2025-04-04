package com.msl.lookheart.ui.anim.nav

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import com.msl.lookheart.enums.ui.SlideType

fun navPopEnterTransition(
    slideType: SlideType,
    tweenDuration: Int
): EnterTransition {
    return when (slideType) {
        SlideType.VERTICALLY -> slideInVertically(
            initialOffsetY = { -it },
            animationSpec = tween(tweenDuration)
        )
        SlideType.HORIZONTALLY -> slideInHorizontally(
            initialOffsetX = { -it }, // 왼쪽에서 들어옴
            animationSpec = tween(tweenDuration)
        )
    }
}
