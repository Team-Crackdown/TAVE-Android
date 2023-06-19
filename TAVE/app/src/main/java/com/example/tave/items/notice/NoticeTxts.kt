package com.example.tave.items.notice

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.tave.R
import com.example.tave.ui.font.NotoSansKr

@Composable
fun NoticeTitleTxt(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        content = {
            Text(
                text = stringResource(id = R.string.notice),
                textAlign = TextAlign.Left,
                fontSize = 30.sp,
                fontFamily = NotoSansKr,
                fontWeight = FontWeight.SemiBold,
                style = TextStyle(
                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                    lineHeight = 2.5.em
                )
            )
        }
    )
}