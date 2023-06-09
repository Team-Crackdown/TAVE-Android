package com.example.tave.items.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun UpdateProfileTxt(text: String) {
    Text(
        text = text,
        style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
    )
}