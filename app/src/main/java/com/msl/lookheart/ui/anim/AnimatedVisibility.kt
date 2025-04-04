package com.msl.lookheart.ui.anim

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.msl.lookheart.enums.ui.AnimationDirection
import com.msl.lookheart.enums.ui.SlideType

@Composable
fun Animate(
    visible: Boolean,
    slideType: SlideType,
    animationDirection: AnimationDirection,
    tweenDuration: Int,
    reenterFromExitEdge: Boolean = false,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val enterTransition = when (animationDirection) {
        AnimationDirection.FORWARD -> defaultEnterTransition(slideType, tweenDuration)
        AnimationDirection.BACK -> defaultPopEnterTransition(slideType, tweenDuration)
    }

    val exitTransition = when (animationDirection) {
        AnimationDirection.FORWARD -> {
            if (reenterFromExitEdge)
                defaultPopExitTransition(slideType, tweenDuration)
            else
                defaultExitTransition(slideType, tweenDuration)
        }
        AnimationDirection.BACK -> {
            if (reenterFromExitEdge)
                defaultExitTransition(slideType, tweenDuration)
            else
                defaultPopExitTransition(slideType, tweenDuration)
        }
    }

    AnimatedVisibility(
        visible = visible,
        enter = enterTransition,
        exit = exitTransition,
        modifier = modifier
    ) {
        content()
    }
}