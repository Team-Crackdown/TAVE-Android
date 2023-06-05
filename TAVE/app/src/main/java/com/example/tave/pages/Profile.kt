package com.example.tave.pages

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import com.example.tave.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun profilePage(context: Context, navController: NavController) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        uploadImageBtn()
        updateBtn()
    }
}

@Composable
fun profileImage() {
    Box(
        modifier = Modifier.fillMaxWidth().height(300.dp)
    ){

    }
}

@Composable
fun uploadImageBtn() {
    ElevatedButton(
        modifier = Modifier.size(60.dp, 60.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(5.dp),
        colors = ButtonDefaults.buttonColors(),
        onClick = {}
    ) {
        Image(
            modifier = Modifier.size(16.dp, 20.dp),
            painter = painterResource(R.drawable.upload),
            contentDescription = "upload",
        )
    }
}

@Composable
fun profileContent() {

}

@Composable
fun updateBtn() {
    ElevatedButton(
        modifier = Modifier.size(100.dp, 40.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = ButtonDefaults.elevatedButtonElevation(5.dp),
        colors = ButtonDefaults.buttonColors(Color.Blue),
        onClick = {}
    ) {
        Text(
            text = "수정하기",
            style = TextStyle(
                color = Color.White,
                fontSize = 10.sp,
                fontWeight = FontWeight.W600
            ),
            textAlign = TextAlign.Center
        )
    }
}


@Composable
@Preview
fun preview1() {
    uploadImageBtn()
}
