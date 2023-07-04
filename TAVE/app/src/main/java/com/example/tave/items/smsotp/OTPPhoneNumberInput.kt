package com.example.tave.items.smsotp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tave.ui.font.NotoSansKr
import com.example.tave.ui.theme.Shape
import com.example.tave.ui.theme.TAVETheme

@Composable
fun SMSPhoneNumberInput(
    modifier: Modifier
) {
    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("휴대폰 번호") },
            leadingIcon = { Icon(Icons.Outlined.Phone, contentDescription = "휴대폰 번호") },
            modifier = modifier.width(300.dp),
            shape = Shape.medium,
            singleLine = true
        )
        Spacer(modifier = modifier.size(10.dp))
        ElevatedButton(
            modifier = modifier.width(300.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary),
            onClick = { /*TODO*/ },
            content = {
                Text(
                    text = "인증번호 발송",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = NotoSansKr,
                        fontWeight = FontWeight.Medium,
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    )
                )
            }
        )
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewInput(){
    TAVETheme {
        SMSPhoneNumberInput(Modifier)
    }
}