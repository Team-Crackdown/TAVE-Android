package com.example.tave.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tave.items.smsotp.OtpLogo
import com.example.tave.items.smsotp.SMSPhoneNumberInput
import com.example.tave.ui.theme.TAVETheme

@Composable
fun SMSOTPPage() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        content = {
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OtpLogo(modifier = Modifier)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SMSPhoneNumberInput(modifier = Modifier)
                }
            }
        }
    )
}


@Composable
@Preview(showBackground = true)
fun PreviewSMSOTPPage() {
    TAVETheme {
        SMSOTPPage()
    }
}