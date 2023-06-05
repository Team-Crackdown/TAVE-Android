package com.example.tave.pages

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tave.R
import com.example.tave.items.*

@Composable
fun HomePage(
    name: String,
    radix: String,
    generation: String,
    context: Context,
    navController: NavController
) {
    Column(
        modifier = Modifier.padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
    ) {
        TopTitle(name)
        Row {
            UserBadge(radix)
            Spacer(modifier = Modifier.width(10.dp))
            UserBadge(generation)
        }
        Spacer(modifier = Modifier.height(33.dp))
        HomeButtons(navController)
    }
}

@Composable
fun TopTitle(name: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column {
            WelcomeTitleTxt(name)
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}

@Composable
fun HomeButtons(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row {
            MainMenuButton(
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
                HomeStatusCards(
                    width = 180.dp,
                    height = 130.dp,
                    painter = painterResource(R.drawable.score),
                    description = "개인 활동 점수 카드",
                    textTitle = "개인 활동 점수",
                    textContent = "126점"
                )
                Spacer(modifier = Modifier.height(20.dp))
                HomeStatusCards(
                    width = 180.dp,
                    height = 130.dp,
                    painter = painterResource(R.drawable.score),
                    description = "팀 활동 점수 카드",
                    textTitle = "팀 활동 점수",
                    textContent = "80점"
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
    Row {
        MainMenuButton(
            width = 109.dp,
            height = 102.dp,
            onClicked = {
                navController.navigate("profile")
            },
            color = ButtonDefaults.elevatedButtonColors(),
            painter = painterResource(R.drawable.profile),
            description = "profile",
            title = "프로필",
            subTitle = "",
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.size(12.dp))
        HomeStatusCards(
            width = 215.dp,
            height = 102.dp,
            painter = painterResource(R.drawable.calendar),
            description = "team",
            textTitle = "후반기 만남의 장까지",
            textContent = "D-day"
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
    MainMenuButton(
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