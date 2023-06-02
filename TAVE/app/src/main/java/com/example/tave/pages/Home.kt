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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.tave.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

@Composable
fun homePage(name: String, context: Context, navController: NavController) {
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
    ) {
        topTitle("김테비")
        Row() {
            topTitleCard(text = "11기")
            topTitleCard(text = "YB")
        }
        Spacer(modifier = Modifier.height(33.dp))
        homeButtons()
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
        Column() {
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
        }
        //Image 프로필 넣기
    }
}

@Composable
fun topTitleCard(text: String) {
    Card(
        shape = MaterialTheme.shapes.small,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 9.sp,
            modifier = Modifier.padding(5.dp).size(35.dp, 15.dp)
        )
    }
    Spacer(modifier = Modifier.size(10.dp))
}

@Composable
fun buttons(width: Dp, height: Dp, onClicked: () -> Unit){
    ElevatedButton(
        modifier = Modifier.size(width, height),
        shape = MaterialTheme.shapes.large,
        elevation = ButtonDefaults.elevatedButtonElevation(5.dp),
        colors = ButtonDefaults.buttonColors(), // Uses the right colors defined in the theme, no need to specify here
        onClick = onClicked
    ) {

    }
}
@Composable
fun homeButtons() {
    Column {
        Row() {
            buttons(width = 146.dp, height = 280.dp, onClicked = {})
            Spacer(modifier = Modifier.size(10.dp))
            Column {
                ElevatedButton(
                    modifier = Modifier.size(180.dp, 130.dp),
                    elevation = ButtonDefaults.elevatedButtonElevation(5.dp),
                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.elevatedButtonColors(), // Uses the right colors defined in the theme, no need to specify here
                    onClick = { }
                ) {

                }
                Spacer(modifier = Modifier.height(20.dp))
                ElevatedButton(
                    modifier = Modifier.size(180.dp, 130.dp),
                    elevation = ButtonDefaults.elevatedButtonElevation(5.dp),
                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.elevatedButtonColors(), // Uses the right colors defined in the theme, no need to specify here
                    onClick = { }
                ) {

                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            ElevatedButton(
                modifier = Modifier.size(109.dp, 102.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(5.dp),
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.elevatedButtonColors(), // Uses the right colors defined in the theme, no need to specify here
                onClick = { }
            ) {

            }
            Spacer(modifier = Modifier.size(12.dp))
            ElevatedButton(
                modifier = Modifier.size(215.dp, 102.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(5.dp),
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.elevatedButtonColors(), // Uses the right colors defined in the theme, no need to specify here
                onClick = { }
            ) {

            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedButton(
            modifier = Modifier.size(336.dp, 130.dp),
            elevation = ButtonDefaults.elevatedButtonElevation(5.dp),
            shape = MaterialTheme.shapes.large,
            colors = ButtonDefaults.buttonColors(), // Uses the right colors defined in the theme, no need to specify here
            onClick = { }
        ) {

        }
    }
}