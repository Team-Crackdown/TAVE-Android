package com.example.tave.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tave.R
import com.example.tave.items.home.*
import com.example.tave.ui.theme.CustomShape
import com.example.tave.viewmodel.HomeViewModel

@Composable
fun HomePage(
    modifier: Modifier,
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier.padding(start = 24.dp, top = 24.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
    ) {
        TopTitle(modifier = modifier, name = homeViewModel.userProfile.value?.userName.toString())
        Row {
            UserBadge(
                text = homeViewModel.userProfile.value?.userRadix.toString(),
                textColor = MaterialTheme.colorScheme.onPrimary,
                backgroundColor = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = modifier.width(10.dp))
            UserBadge(
                text = homeViewModel.userProfile.value?.userType.toString(),
                textColor = MaterialTheme.colorScheme.onSecondary,
                backgroundColor = MaterialTheme.colorScheme.secondary
            )
        }
        Spacer(modifier = modifier.height(33.dp))
        HomeMenu(modifier = modifier, navController= navController)
    }
}

@Composable
fun TopTitle(modifier: Modifier, name: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth(),
        content = {
            Column {
                WelcomeTitleTxt(name)
                Spacer(modifier = modifier.size(10.dp))
            }
        }
    )
}

@Composable
fun HomeMenu(
    modifier: Modifier,
    navController: NavController
) {
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        CheckQrcode(onDismiss = { showDialog.value = false })
    }
    Column {
        Row {
            MainMenuButtons(
                modifier = modifier
                    .width(146.dp)
                    .height(280.dp),
                shapes = MaterialTheme.shapes.large,
                onClicked = { showDialog.value = true },
                color = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                painter = painterResource(R.drawable.check),
                description = stringResource(id = R.string.Check),
                title = stringResource(id = R.string.Check),
                subTitle = stringResource(id = R.string.Check_Confirm),
                fontSize = 30.sp
            )
            Spacer(modifier = modifier.size(10.dp))
            Column {
                MainMenuCards(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(130.dp),
                    painter = painterResource(R.drawable.baseline_scoreboard_24),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
                    iconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    shapes = CustomShape.extraLarge,
                    description = stringResource(id = R.string.Personal_Score),
                    textTitle = stringResource(id = R.string.Personal_Score),
                    textContent = stringResource(id = R.string.Score_126)
                )
                Spacer(modifier = modifier.height(20.dp))
                MainMenuCards(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(130.dp),
                    painter = painterResource(R.drawable.baseline_scoreboard_24),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                    iconColor = MaterialTheme.colorScheme.onPrimary,
                    textColor = MaterialTheme.colorScheme.onPrimary,
                    shapes = CustomShape.extraLarge,
                    description = stringResource(id = R.string.Team_Score),
                    textTitle = stringResource(id = R.string.Team_Score),
                    textContent = stringResource(id = R.string.Score_80)
                )
            }
        }
    }
    Spacer(modifier = modifier.height(20.dp))
    Row {
        MainMenuButtons(
            modifier = modifier
                .width(109.dp)
                .height(102.dp),
            shapes = MaterialTheme.shapes.large,
            onClicked = { navController.navigate("profile") },
            color = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            painter = painterResource(R.drawable.profile),
            description = stringResource(id = R.string.Profile),
            title = stringResource(id = R.string.Profile),
            subTitle = "",
            fontSize = 15.sp
        )
        Spacer(modifier = modifier.size(10.dp))
        MainMenuCards(
            modifier = modifier
                .fillMaxWidth()
                .height(102.dp),
            painter = painterResource(R.drawable.calendar),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondaryContainer),
            iconColor = MaterialTheme.colorScheme.onSecondaryContainer,
            textColor = MaterialTheme.colorScheme.onSecondaryContainer,
            shapes = CustomShape.extraLarge,
            description = stringResource(id = R.string.D_day),
            textTitle = stringResource(id = R.string.D_day_Title),
            textContent = stringResource(id = R.string.D_day)
        )
    }
    Spacer(modifier = modifier.height(20.dp))
    MainMenuButtons(
        modifier = modifier
            .fillMaxWidth()
            .height(130.dp),
        shapes = CustomShape.extraLarge,
        onClicked = { navController.navigate("notice") },
        color = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
        painter = painterResource(R.drawable.notice),
        description = stringResource(id = R.string.Notice),
        title = stringResource(id = R.string.Notice),
        subTitle = stringResource(id = R.string.Notice_Confirm),
        fontSize = 30.sp
    )
}