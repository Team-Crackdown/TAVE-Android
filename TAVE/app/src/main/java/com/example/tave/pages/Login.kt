package com.example.tave.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tave.R
import com.example.tave.items.login.LoginBtn
import com.example.tave.ui.font.NotoSansKr

@Composable
fun LoginPage(modifier: Modifier) {
    Surface(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            content = {
                Text(
                    text = "환영합니다!",
                    style = TextStyle(
                        fontSize = 35.sp,
                        fontFamily = NotoSansKr,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    )
                )
                Text(
                    text = stringResource(id = R.string.app_name) + " 입니다!",
                    style = TextStyle(
                        fontSize = 40.sp,
                        fontFamily = NotoSansKr,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    )
                )
                Text(
                    text = "로그인하여 많은 컨텐츠를 즐기세요!",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontFamily = NotoSansKr,
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray,
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    )
                )
            }
        )
        LoginBox(modifier)
    }
}

@Composable
private fun LoginBox(modifier: Modifier) {
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = userEmail,
            onValueChange = { userEmail = it },
            label = { Text(stringResource(id = R.string.Enter_Email)) },
            leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = stringResource(id = R.string.email)) },
            modifier = modifier
                .padding(bottom = 10.dp, top = 10.dp)
                .width(300.dp)
        )
        OutlinedTextField(
            value = userPassword,
            onValueChange = { userPassword = it },
            label = { Text(stringResource(id = R.string.Enter_Pwd)) },
            leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = stringResource(id = R.string.Password)) },
            modifier = modifier
                .padding(bottom = 10.dp, top = 10.dp)
                .width(300.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = modifier.height(50.dp))
        LoginBtn(onClicked = {})
    }
}

@Preview
@Composable
fun Preview() {
    LoginPage(modifier = Modifier)
}