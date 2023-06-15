package com.example.tave.items.profile

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.tave.ui.theme.LightColorPalette
import com.example.tave.ui.font.NotoSansKr

@Composable
fun UpdateFloatingBtn(txt: String, onClicked: () -> Unit) {
    FloatingActionButton(
        modifier = Modifier.size(100.dp, 40.dp),
        shape = MaterialTheme.shapes.large,
        elevation = FloatingActionButtonDefaults.elevation(10.dp),
        containerColor = LightColorPalette.tertiary,
        onClick = onClicked
    ) {
        Text(
            text = txt,
            textAlign = TextAlign.Center,
            style = TextStyle(
                color = Color.White,
                fontSize = 10.sp,
                fontFamily = NotoSansKr,
                fontWeight = FontWeight.SemiBold,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
                lineHeight = 2.5.em
            ),
        )
    }
}