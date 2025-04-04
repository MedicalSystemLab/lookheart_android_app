package com.msl.lookheart.ui.custom.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.msl.lookheart.enums.ui.ButtonImgLocation
import com.msl.lookheart.enums.ui.ButtonImgLocation.*
import com.msl.lookheart.ui.custom.text.FixedText
import kotlinx.coroutines.delay

@Composable
fun FixedTextImgButton(
    /** img **/
    painter: Painter,
    painterColor: Color? = null,
    painterSize: Dp,
    painterLocation: ButtonImgLocation = CENTER,

    /** button **/
    text: String = "",
    textColor: Color? = null,
    textSize: TextUnit? = null,
    fontWeight: FontWeight? = null,
    onClick: () -> Unit,
    modifier: Modifier
) {
    var isClickable by remember { mutableStateOf(true) }

    LaunchedEffect(isClickable) {
        if (!isClickable) {
            delay(1000L)
            isClickable = true
        }
    }

    Button(
        onClick = {
            if (isClickable) {
                isClickable = false
                onClick.invoke()
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Transparent,
            contentColor = Transparent,
        ),
        modifier = modifier
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val textRef = createRef()
            FixedText(
                text = text,
                color = textColor ?: Color.Black,
                fontSize = textSize ?: 12.sp,
                fontWeight = fontWeight ?: FontWeight.Normal,
                modifier = Modifier
                    .constrainAs(textRef) {
                        centerTo(parent)
                    }
            )

            val imgRef = createRef()
            Image(
                painter = painter,
                contentDescription = "button",
                colorFilter = painterColor?.let { ColorFilter.tint(it) },
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(painterSize)
                    .constrainAs(imgRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)

                        when (painterLocation) {
                            START -> {
                                start.linkTo(parent.start)
                                end.linkTo(textRef.start)
                            }
                            CENTER -> {
                                centerHorizontallyTo(parent)
                            }
                            END -> {
                                start.linkTo(textRef.end)
                                end.linkTo(parent.end)
                            }
                        }
                    }
            )
        }
    }
}