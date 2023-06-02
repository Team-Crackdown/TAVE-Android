@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.tave.pages

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.tave.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

@Composable
fun homePage(name: String, context: Context, navController: NavController) {
    Column() {
        topTitle("김테비")
//        Row() {
//            Button(
//                onClick = {}
//            ) {
//
//            }
//        }

    }
}

@Composable
fun topTitle(name: String) {
    Row {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "${name}님", fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "환영합니다", fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Left,
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.size(10.dp))
            //11기 OB Box 넣기
            Row() {
                topTitleCard(text = "11기")
                topTitleCard(text = "YB")
//                Button(onClick = {}) {
//                    Text(text = stringResource(R.string.app_name))
//                }
//                FloatingActionButton(onClick = {}) {
//                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
//                }
            }

        }
        //Image 프로필 넣기
    }
}

@Composable
fun topTitleCard(text: String){
    Card(
        shape = MaterialTheme.shapes.small,
        elevation = CardDefaults.cardElevation(10.dp)
    ){
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 9.sp,
            modifier = Modifier.padding(6.dp).size(35.dp, 15.dp)
        )
    }
    Spacer(modifier = Modifier.size(10.dp))
}