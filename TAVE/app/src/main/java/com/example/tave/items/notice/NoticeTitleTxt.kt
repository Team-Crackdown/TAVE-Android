package com.example.tave.items.notice

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.tave.ui.font.NotoSansKr

@Composable
fun NoticeTitleTxt(title: String) {
    Column {
        Text(
            text = title,
            textAlign = TextAlign.Left,
            fontSize = 30.sp,
            fontFamily = NotoSansKr,
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