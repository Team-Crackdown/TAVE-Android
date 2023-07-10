package com.example.tave.items.otp

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
fun OtpLogo(
    modifier: Modifier
) {
    Column(
        modifier = modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = modifier.size(200.dp),
            painter = painterResource(id = R.drawable.otp_logo),
            alignment = Alignment.Center,
            contentScale = ContentScale.Fit,
            contentDescription = "OTP LOGO"
        )
        Spacer(modifier = modifier.size(20.dp))
        Text(
            text = "발송된 SMS의",
            style = TextStyle(
                fontSize = 25.sp,
                fontFamily = NotoSansKr,
                fontWeight = FontWeight.SemiBold,
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            )
        )
        Text(
            text = "인증번호를 입력해주세요!",
            style = TextStyle(
                fontSize = 25.sp,
                fontFamily = NotoSansKr,
                fontWeight = FontWeight.SemiBold,
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            )
        )
    }
}