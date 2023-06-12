package com.example.tave.items.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.tave.ui.theme.notosanskr

@Composable
fun UpdateProfileTxt(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = notosanskr,
            fontWeight = FontWeight.SemiBold,
            platformStyle = PlatformTextStyle(includeFontPadding = false),
            lineHeight = 2.5.em
        ),
    )
}