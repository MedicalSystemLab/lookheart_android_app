package com.msl.lookheart.screen.start.composable

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import com.msl.lookheart.ui.theme.LightSkyBlue
import com.msl.lookheart.ui.theme.MySkyBlueAlpha30

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BranchModalSheetBackground(
    isPositionSet: Boolean,
    content: @Composable (ConstraintLayoutScope.() -> Unit)
) {
    val sheetState = rememberModalBottomSheetState(
        confirmValueChange = { newState -> false }  // touch 비활성화
    )

    val configuration = LocalConfiguration.current
    val canvasSize = (0.9f * configuration.screenWidthDp)

    ModalBottomSheet(
        sheetState = sheetState,
        scrimColor = Color.Transparent,
        onDismissRequest = { },
        dragHandle = null,
        content = {
            BackHandler(enabled = true) { /** 뒤로 가기 동작 핸들러 **/ }

            Surface(
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                ) {
                    val circleRef = createRef()
                    Canvas(
                        modifier = Modifier
                            .wrapContentSize(Alignment.Center)
                            .size(canvasSize.dp)
                            .constrainAs(circleRef) {
                                top.linkTo(parent.top, margin = (-0.3f * configuration.screenHeightDp).dp)
                                end.linkTo(parent.end, margin = (0.5f * configuration.screenWidthDp).dp)
                            }
                    ) {
                        drawCircle(
                            color = MySkyBlueAlpha30,
                            radius = size.minDimension / 2
                        )
                    }

                    val circle2Ref = createRef()
                    Canvas(
                        modifier = Modifier
                            .wrapContentSize(Alignment.Center)
                            .size(canvasSize.dp)
                            .constrainAs(circle2Ref) {
                                if (isPositionSet) {
                                    start.linkTo(parent.start, margin = (0.4f * configuration.screenWidthDp).dp)
                                    bottom.linkTo(parent.bottom, margin = (-0.2f * configuration.screenHeightDp).dp)
                                }
                            }
                    ) {
                        drawCircle(
                            color = LightSkyBlue,
                            radius = size.minDimension / 2
                        )
                    }

                    content(this)
                }
            }
        }
    )
}