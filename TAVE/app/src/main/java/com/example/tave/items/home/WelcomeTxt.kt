package com.example.tave.items.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.tave.ui.theme.notosanskr

@Composable
fun WelcomeTitleTxt(name: String) {
    Column {
        Text(
            text = name + "님",
            textAlign = TextAlign.Left,
            fontSize = 24.sp,
            fontFamily = notosanskr,
            fontWeight = FontWeight.SemiBold,
            style = TextStyle(
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                ),
                lineHeight = 2.5.em
            )
        )
        Text(
            text = "환영합니다",
            textAlign = TextAlign.Left,
            fontSize = 20.sp,
            fontFamily = notosanskr,
            fontWeight = FontWeight.SemiBold,
            style = TextStyle(
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                ),
                lineHeight = 2.5.em
            )
        )
    }
}