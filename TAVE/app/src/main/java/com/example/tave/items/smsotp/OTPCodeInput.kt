package com.example.tave.items.smsotp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tave.ui.theme.Shape
import com.example.tave.ui.theme.TAVETheme

@Composable
fun OTPCodeInput(
    modifier: Modifier
) {
    var otpCode: String by remember { mutableStateOf("") }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = otpCode,
            onValueChange = { otpCode = it },
            modifier = modifier.width(300.dp),
            shape = Shape.medium
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewOTPCodeInput() {
    TAVETheme {
        OTPCodeInput(modifier = Modifier)
    }
}