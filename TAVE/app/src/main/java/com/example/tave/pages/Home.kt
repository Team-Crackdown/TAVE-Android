@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.tave.pages

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tave.R


@Composable
fun homePage(name: String, context: Context, navController: NavController) {
    Column(
        modifier = Modifier.padding(30.dp),
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
    }
}

@Composable
fun topTitle(name: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
    ) {
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
        Card(
            modifier = Modifier.size(50.dp, 50.dp),
            shape = MaterialTheme.shapes.extraSmall,
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            //Image 넣기!!! (여기가 Glide ? )
            Card(
                modifier = Modifier
                    .size(44.dp, 44.dp)
                    .padding(start = 6.dp, top = 6.dp),
                shape = MaterialTheme.shapes.extraSmall,
            ) {

            }
        }
    }
}

@Composable
fun topTitleCard(text: String) {
    Card(
        modifier = Modifier.size(49.dp, 25.dp),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            fontWeight = FontWeight.W600,
            modifier = Modifier
                .padding(5.dp)
                .size(35.dp, 15.dp)
        )
    }
    Spacer(modifier = Modifier.size(10.dp))
}

@Composable
fun homeButtons() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row() {
            buttons(
                width = 146.dp,
                height = 280.dp,
                onClicked = {},
                color = ButtonDefaults.buttonColors(),
                painter = painterResource(R.drawable.check),
                description = "check",
                title = "출석",
                subTitle = "출석 인증하기",
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.size(12.dp))
            Column {
                cards(
                    modifier = Modifier
                        .width(180.dp)
                        .height(130.dp),
                    painter = painterResource(R.drawable.score),
                    description = "personalScore",
                    textTitle = "개인 활동 점수",
                    textContent = "126점"
                )
                Spacer(modifier = Modifier.height(20.dp))
                cards(
                    modifier = Modifier
                        .width(180.dp)
                        .height(130.dp),
                    painter = painterResource(R.drawable.score),
                    description = "teamScore",
                    textTitle = "팀 활동 점수",
                    textContent = "80점"
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
    Row {
        buttons(
            width = 109.dp,
            height = 102.dp,
            onClicked = {},
            color = ButtonDefaults.elevatedButtonColors(),
            painter = painterResource(R.drawable.profile),
            description = "profile",
            title = "프로필",
            subTitle = "",
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.size(12.dp))
        buttons(
            width = 215.dp,
            height = 102.dp,
            onClicked = {},
            color = ButtonDefaults.elevatedButtonColors(),
            painter = painterResource(R.drawable.team),
            description = "team",
            title = "팀",
            subTitle = "",
            fontSize = 15.sp
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
    buttons(
        width = 336.dp,
        height = 130.dp,
        onClicked = {},
        color = ButtonDefaults.buttonColors(),
        painter = painterResource(R.drawable.notice),
        description = "notice",
        title = "공지사항",
        subTitle = "공지 확인하기",
        fontSize = 30.sp
    )
}

@Composable
fun buttons(
    width: Dp,
    height: Dp,
    onClicked: () -> Unit,
    color: ButtonColors,
    painter: Painter,
    description: String,
    title: String,
    subTitle: String,
    fontSize: TextUnit,
) {
    ElevatedButton(
        modifier = Modifier.size(width, height),
        contentPadding = PaddingValues(
            start = 10.dp,
            top = 12.dp,
            end = 20.dp,
            bottom = 12.dp
        ),
        shape = MaterialTheme.shapes.large,
        elevation = ButtonDefaults.elevatedButtonElevation(5.dp),
        colors = color,
        onClick = onClicked
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
        ) {
            Image(
                painter = painter,
                contentDescription = description,
            )
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = title,
                    fontSize = fontSize,
                    fontWeight = FontWeight.W600,
                )
                Text(
                    text = subTitle,
                    fontSize = if (subTitle == "") 0.sp else 15.sp,
                    fontWeight = FontWeight.W600,
                )
            }
        }
    }
}

@Composable
fun cards(
    modifier: Modifier,
    painter: Painter,
    description: String,
    textTitle: String,
    textContent: String,
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(5.dp),
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Row {
                Icon(painter, description)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = textTitle,
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.W600
                    ),
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = textContent,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W600
                ),
            )
        }

    }
}

@Composable
@Preview
fun preview() {
    homeButtons()
}
