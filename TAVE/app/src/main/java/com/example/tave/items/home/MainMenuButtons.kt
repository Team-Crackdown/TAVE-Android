package com.example.tave.items.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.tave.ui.font.NotoSansKr

@Composable
fun MainMenuButtons(
    modifier: Modifier,
    onClicked: () -> Unit,
    shapes: Shape,
    color: ButtonColors,
    painter: Painter,
    description: String,
    title: String,
    subTitle: String,
    fontSize: TextUnit
){
    ElevatedButton(
        modifier = modifier,
        contentPadding = PaddingValues(
            start = 10.dp,
            top = 10.dp,
            end = 20.dp,
            bottom = 10.dp
        ),
        shape = shapes,
        elevation = ButtonDefaults.elevatedButtonElevation(5.dp),
        colors = color,
        onClick = onClicked
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
            content = {
                Image(
                    painter = painter,
                    contentDescription = description,
                )
                Column(
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = title,
                        style = TextStyle(
                            fontSize = fontSize,
                            fontFamily = NotoSansKr,
                            fontWeight = FontWeight.SemiBold,
                            platformStyle = PlatformTextStyle(includeFontPadding = false),
                            lineHeight = 2.5.em
                        )
                    )
                    Text(
                        text = subTitle,
                        style = TextStyle(
                            fontSize = if(subTitle == "") 0.sp else 15.sp,
                            fontFamily = NotoSansKr,
                            fontWeight = FontWeight.SemiBold,
                            platformStyle = PlatformTextStyle(includeFontPadding = false),
                            lineHeight = 2.5.em
                        )
                    )
                }
            }
        )
    }
}