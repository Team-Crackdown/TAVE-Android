package com.example.tave.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainMenuButton(
    width: Dp,
    height: Dp,
    onClicked: () -> Unit,
    color: ButtonColors,
    painter: Painter,
    description: String,
    title: String,
    subTitle: String,
    fontSize: TextUnit
){
    ElevatedButton(
        modifier = Modifier.size(width, height),
        contentPadding = PaddingValues(
            start = 10.dp,
            top = 12.dp,
            end = 20.dp,
            bottom = 10.dp
        ),
        shape = MaterialTheme.shapes.large,
        elevation = ButtonDefaults.elevatedButtonElevation(5.dp),
        colors = color,
        onClick = onClicked
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
        ) {
            Image(
                painter = painter,
                contentDescription = description,
            )
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = title,
                    fontSize = fontSize,
                    fontWeight = FontWeight.W600,
                )
                Text(
                    text = subTitle,
                    fontSize = if(subTitle == "") 0.sp else 15.sp,
                    fontWeight = FontWeight.W600,
                )
            }
        }
    }
}