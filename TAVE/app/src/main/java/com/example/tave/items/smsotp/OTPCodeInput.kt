package com.example.tave.items.smsotp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tave.ui.theme.TAVETheme

@Composable
fun OTPCodeInput(
    modifier: Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {

    }
}

@Composable
@Preview(showBackground = true)
fun PreviewOTPCodeInput() {
    TAVETheme {
        OTPCodeInput(modifier = Modifier)
    }
}