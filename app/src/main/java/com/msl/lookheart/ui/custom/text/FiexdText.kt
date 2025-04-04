package com.msl.lookheart.ui.custom.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun FixedText(
    text: String?,
    color: Color,
    fontSize: TextUnit,
    lineHeight: TextUnit = 20.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    style: TextStyle = TextStyle.Default,
    textAlign: TextAlign = TextAlign.Center,
    fixed: Boolean = true,
    modifier: Modifier = Modifier
) {
    if (fixed) {
        FixedFontSizeContent {
            Text(
                text = text ?: "",
                style = style.merge(
                    color = color,
                    fontSize = fontSize,
                    fontWeight = fontWeight,
                    fontFamily = gothicFontFamily,
                    textAlign = textAlign,
                    lineHeight = lineHeight,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = true
                    ),
                ),
                modifier = modifier
            )
        }
    } else {
        Text(
            text = text ?: "",
            style = style.merge(
                color = color,
                fontSize = fontSize,
                fontWeight = fontWeight,
                fontFamily = gothicFontFamily,
                textAlign = textAlign,
                lineHeight = lineHeight
            ),
            modifier = modifier
        )
    }
}