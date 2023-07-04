package com.example.tave.items.login

import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
fun InitPasswordBtn(onClicked: () -> Unit){
    ElevatedButton(
        modifier = Modifier.size(300.dp, 60.dp),
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary),
        onClick = onClicked,
        content = {
            Text(
                text = "변경",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = NotoSansKr,
                    fontWeight = FontWeight.SemiBold,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
        }
    )
}