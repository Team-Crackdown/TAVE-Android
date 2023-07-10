package com.example.tave.items.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tave.R
import com.example.tave.ui.font.NotoSansKr

@Composable
fun LoginIntro(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        content = {
            Text(
                text = "환영합니다!",
                style = TextStyle(
                    fontSize = 35.sp,
                    fontFamily = NotoSansKr,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
            Text(
                text = stringResource(id = R.string.app_name) + " 입니다!",
                style = TextStyle(
                    fontSize = 40.sp,
                    fontFamily = NotoSansKr,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
            Text(
                text = "로그인하여 많은 컨텐츠를 즐기세요!",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = NotoSansKr,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
        }
    )
}