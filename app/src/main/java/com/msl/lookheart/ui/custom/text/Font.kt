package com.msl.lookheart.ui.custom.text

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.msl.lookheart.R

// 해당 폰트는 includeFontPadding = true 적용 시 정상 패딩
val gothicFontFamily = FontFamily(
    Font(R.font.gothic_a1_thin, FontWeight.Thin),
    Font(R.font.gothic_a1_extra_light, FontWeight.ExtraLight),
    Font(R.font.gothic_a1_light, FontWeight.Light),
    Font(R.font.gothic_a1_regular, FontWeight.Normal),
    Font(R.font.gothic_a1_medium, FontWeight.Medium),
    Font(R.font.gothic_a1_bold, FontWeight.Bold),
    Font(R.font.gothic_a1_extra_bold, FontWeight.ExtraBold),
    Font(R.font.gothic_a1_black, FontWeight.Black)
)