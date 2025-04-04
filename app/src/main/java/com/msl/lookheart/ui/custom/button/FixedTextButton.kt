package com.msl.lookheart.ui.custom.button

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.msl.lookheart.ui.custom.text.FixedText
import kotlinx.coroutines.delay

@Composable
fun FixedTextButton(
    /** text **/
    text: String,
    textColor: Color,
    fontSize: TextUnit,
    fontWeight: FontWeight = FontWeight.Normal,
    style: TextStyle = TextStyle.Default,
    fixed: Boolean = true,

    /** button **/
    enable: Boolean = true,
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
        enabled = enable,
        modifier = modifier
    ) {
        FixedText(
            text = text,
            color = textColor,
            fontSize = fontSize,
            fontWeight = fontWeight,
            style = style,
            fixed = fixed,
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
        )
    }
}