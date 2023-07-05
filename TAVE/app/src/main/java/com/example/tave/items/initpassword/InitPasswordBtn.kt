package com.example.tave.items.initpassword

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
                text = stringResource(id = R.string.Init_Password_Btn),
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