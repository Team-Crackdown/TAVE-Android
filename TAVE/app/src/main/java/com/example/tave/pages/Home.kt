package com.example.tave.pages

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
import androidx.navigation.compose.rememberNavController
import com.example.tave.R
import com.example.tave.items.home.MainMenuButtons
import com.example.tave.items.home.MainMenuCards
import com.example.tave.items.home.UserBadge
import com.example.tave.items.home.WelcomeTitleTxt
import com.example.tave.ui.theme.LightColorPalette
import com.example.tave.ui.theme.TAVETheme
import com.example.tave.ui.theme.customShape

@Composable
fun HomePage(
    name: String,
    radix: String,
    generation: String,
    navController: NavController
) {
    Column(
        modifier = Modifier.padding(start = 24.dp, top = 24.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
    ) {
        TopTitle(name)
        Row {
            UserBadge(
                radix,
                LightColorPalette.onPrimary,
                CardDefaults.cardColors(LightColorPalette.primary)
            )
            Spacer(modifier = Modifier.width(10.dp))
            UserBadge(
                generation,
                LightColorPalette.onSecondary,
                CardDefaults.cardColors(LightColorPalette.secondary)
            )
        }
        Spacer(modifier = Modifier.height(33.dp))
        HomeMenu(navController)
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
fun HomeMenu(navController: NavController) {
    Column {
        Row {
            MainMenuButtons(
                modifier = Modifier
                    .width(146.dp)
                    .height(280.dp),
                shapes = MaterialTheme.shapes.large,
                onClicked = {},
                color = ButtonDefaults.buttonColors(),
                painter = painterResource(R.drawable.check),
                description = "check",
                title = "출석",
                subTitle = "출석 인증하기",
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.size(10.dp))
            Column {
                MainMenuCards(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp),
                    painter = painterResource(R.drawable.baseline_scoreboard_24),
                    colors = CardDefaults.cardColors(LightColorPalette.primaryContainer),
                    iconColor = LightColorPalette.onPrimaryContainer,
                    textColor = LightColorPalette.onPrimaryContainer,
                    shapes = customShape.extraLarge,
                    description = "개인 활동 점수 카드",
                    textTitle = "개인 활동 점수",
                    textContent = "126점"
                )
                Spacer(modifier = Modifier.height(20.dp))
                MainMenuCards(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp),
                    painter = painterResource(R.drawable.baseline_scoreboard_24),
                    colors = CardDefaults.cardColors(LightColorPalette.primary),
                    iconColor = LightColorPalette.onPrimaryContainer,
                    textColor = LightColorPalette.onPrimaryContainer,
                    shapes = customShape.extraLarge,
                    description = "팀 활동 점수 카드",
                    textTitle = "팀 활동 점수",
                    textContent = "80점"
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
    Row {
        MainMenuButtons(
            modifier = Modifier
                .width(109.dp)
                .height(102.dp),
            shapes = MaterialTheme.shapes.large,
            onClicked = { navController.navigate("profile") },
            color = ButtonDefaults.buttonColors(
                containerColor = LightColorPalette.secondaryContainer,
                contentColor = LightColorPalette.onSecondaryContainer
            ),
            painter = painterResource(R.drawable.profile),
            description = "profile",
            title = "프로필",
            subTitle = "",
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.size(10.dp))
        MainMenuCards(
            modifier = Modifier
                .fillMaxWidth()
                .height(102.dp),
            painter = painterResource(R.drawable.calendar),
            colors = CardDefaults.cardColors(LightColorPalette.secondaryContainer),
            iconColor = LightColorPalette.onSecondaryContainer,
            textColor = LightColorPalette.onSecondaryContainer,
            shapes = customShape.extraLarge,
            description = "team",
            textTitle = "후반기 만남의 장까지",
            textContent = "D-day"
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
    MainMenuButtons(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp),
        shapes = customShape.extraLarge,
        onClicked = {},
        color = ButtonDefaults.buttonColors(containerColor = LightColorPalette.secondary),
        painter = painterResource(R.drawable.notice),
        description = "notice",
        title = "공지사항",
        subTitle = "공지 확인하기",
        fontSize = 30.sp
    )
}

@Preview
@Composable
fun PreviewHomePage() {
    TAVETheme {
        HomePage("김건우", "11기", "YB", rememberNavController())
    }
}