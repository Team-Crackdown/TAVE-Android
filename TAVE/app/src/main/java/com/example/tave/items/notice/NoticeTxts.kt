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
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                ),
                lineHeight = 2.5.em
            )
        )
    }
}

@Composable
fun NoticeDetailWriterTxt(title: String){
    Text(
        text = title,
        fontSize = 14.sp,
        fontFamily = NotoSansKr,
        fontWeight = FontWeight.Normal,
    )
}

@Composable
fun NoticeDetailContentTxt(title: String) {
    Column {
        Text(
            text = title,
            textAlign = TextAlign.Left,
            fontSize = 16.sp,
            fontFamily = NotoSansKr,
            fontWeight = FontWeight.Medium,
            style = TextStyle(
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                ),
                lineHeight = 1.5.em
            )
        )
    }
}