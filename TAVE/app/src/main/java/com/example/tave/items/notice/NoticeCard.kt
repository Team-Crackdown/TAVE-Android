package com.example.tave.items.notice

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.tave.ui.theme.notosanskr

@Composable
fun NoticeCard(title: String, content: String){
    Card(
        modifier = Modifier.height(228.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            CardTextTitle(title = title)
            Spacer(modifier = Modifier.height(22.dp))
            CardTextContent(content = content)
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun CardTextTitle(title: String){
    Text(
        text = title,
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = notosanskr,
            platformStyle = PlatformTextStyle(includeFontPadding = false),
            lineHeight = 2.5.em
        ),
    )
}

@Composable
fun CardTextContent(content: String){
    Text(
        text = content,
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = notosanskr,
            platformStyle = PlatformTextStyle(includeFontPadding = false),
            lineHeight = 1.25.em
        ),
    )
}