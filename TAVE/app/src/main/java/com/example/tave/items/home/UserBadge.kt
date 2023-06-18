package com.example.tave.items.home

import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.tave.ui.theme.LightColorPalette
import com.example.tave.ui.theme.Shape
import com.example.tave.ui.font.NotoSansKr

/**
 * TODO("User Badge 수정 필요!")
 */
@Composable
fun UserBadge(
    text: String,
    textColor: Color,
    cardColor: CardColors
) {
    Card(
        modifier = Modifier.size(50.dp, 25.dp),
        shape = Shape.large,
        colors = cardColor,
        elevation = CardDefaults.cardElevation()
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            fontFamily = NotoSansKr,
            fontWeight = FontWeight.SemiBold,
            style = TextStyle(
                platformStyle = PlatformTextStyle(includeFontPadding = false),
                lineHeight = 2.5.em
            ),
            color = textColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserBadge() {
    UserBadge(
        text = "11기",
        LightColorPalette.onPrimaryContainer,
        CardDefaults.cardColors(LightColorPalette.primaryContainer)
    )
}