package com.example.tave.items.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tave.R

@Composable
fun UploadImageBtn() {
    ElevatedButton(
        modifier = Modifier.size(50.dp, 50.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(5.dp),
        colors = ButtonDefaults.buttonColors(),
        onClick = {}
    ) {
        Image(
            painter = painterResource(R.drawable.baseline_file_upload_24),
            contentDescription = "upload",
        )
    }
}