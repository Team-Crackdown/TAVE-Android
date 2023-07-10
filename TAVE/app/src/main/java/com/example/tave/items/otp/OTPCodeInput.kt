package com.example.tave.items.otp

import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tave.R
import com.example.tave.ui.font.NotoSansKr

@Composable
fun OTPCodeInput(
    modifier: Modifier,
    checkOTPCode: () -> Unit
) {
    ElevatedButton(
        modifier = modifier.width(300.dp),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary),
        onClick = checkOTPCode,
        content = {
            Text(
                text = stringResource(id = R.string.OTP_Btn),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 13.sp,
                    fontFamily = NotoSansKr,
                    fontWeight = FontWeight.Medium,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
        }
    )

}