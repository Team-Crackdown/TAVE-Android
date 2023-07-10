package com.example.tave.items.initpassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tave.R
import com.example.tave.ui.font.NotoSansKr

@Composable
fun InitPasswordLogo(
    modifier: Modifier
) {
    Column(
        modifier = modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = modifier.size(200.dp),
            painter = painterResource(id = R.drawable.icon_password),
            alignment = Alignment.Center,
            contentScale = ContentScale.Fit,
            contentDescription = "OTP LOGO"
        )
        Spacer(modifier = modifier.size(20.dp))
        Text(
            text = "초기 비밀 번호를 변경해 주세요!",
            style = TextStyle(
                fontSize = 25.sp,
                fontFamily = NotoSansKr,
                fontWeight = FontWeight.SemiBold,
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            )
        )
        Text(
            text = "새로 변경할 패스워드를 입력해 주세요!",
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = NotoSansKr,
                fontWeight = FontWeight.Medium,
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            )
        )
    }
}