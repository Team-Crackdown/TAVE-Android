package com.example.tave.items

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeStatusCards(
    width: Dp,
    height: Dp,
    painter: Painter,
    description: String,
    textTitle: String,
    textContent: String
) {
    Card(
        modifier = Modifier.width(width).height(height),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(5.dp),
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Row {
                Icon(painter, description)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = textTitle,
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.W600
                    ),
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = textContent,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W600
                ),
            )
        }
    }
}