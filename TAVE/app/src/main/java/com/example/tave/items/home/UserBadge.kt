package com.example.tave.items.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tave.ui.theme.Shape
import com.example.tave.ui.font.NotoSansKr

@Composable
fun UserBadge(
    text: String,
    textColor: Color,
    backgroundColor: Color
) {
    Box(
        modifier = Modifier
            .size(50.dp, 25.dp)
            .clip(Shape.large)
            .background(color = backgroundColor)
            .shadow(10.dp),
        contentAlignment = Alignment.Center,
        content = {
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = text,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = NotoSansKr,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        color = textColor,
                        platformStyle = PlatformTextStyle(false)
                    ),
                )
            }
        }
    )
}