package com.example.tave.pages

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tave.R
import com.example.tave.items.MainMenuButton
import com.example.tave.items.UserBadge
import com.example.tave.items.WelcomeTitleTxt

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
            UserBadge(generation)
        }
        Spacer(modifier = Modifier.height(33.dp))
        HomeMenu()
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
fun HomeMenu() {
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
                MainMenuButton(
                    width = 180.dp,
                    height = 130.dp,
                    onClicked = {},
                    color = ButtonDefaults.elevatedButtonColors(),
                    painter = painterResource(R.drawable.calendar),
                    description = "calendar",
                    title = "일정",
                    subTitle = "일정 확인하기",
                    fontSize = 20.sp,
                )
                Spacer(modifier = Modifier.height(20.dp))
                MainMenuButton(
                    width = 180.dp,
                    height = 130.dp,
                    onClicked = {},
                    color = ButtonDefaults.elevatedButtonColors(),
                    painter = painterResource(R.drawable.score),
                    description = "score",
                    title = "점수",
                    subTitle = "활동 점수 확인하기",
                    fontSize = 25.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            MainMenuButton(
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
            MainMenuButton(
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
}

/*** HomePage Preview Stub (For Preview) ***/
@Composable
fun HomePageMock() {
    Column(
        modifier = Modifier.padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
    ) {
        TopTitle("김테비")
        Row {
            UserBadge(text = "11기")
            UserBadge(text = "YB")
        }
        Spacer(modifier = Modifier.height(33.dp))
        HomeMenu()
    }
}

@Preview
@Composable
fun PreviewHomePage() {
    HomePageMock()
}