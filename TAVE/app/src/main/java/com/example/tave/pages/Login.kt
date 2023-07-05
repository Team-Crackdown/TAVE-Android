package com.example.tave.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tave.items.login.LogInInputField
import com.example.tave.items.login.LoginBtn
import com.example.tave.items.login.LoginIntro

@Composable
fun LoginPage(modifier: Modifier) {
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }

    Surface(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            content = { LoginIntro(modifier = modifier) }
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                LogInInputField(
                    modifier = modifier,
                    emailInput = userEmail,
                    passwordInput = userPassword
                )
                Spacer(modifier = modifier.height(50.dp))
                LoginBtn(onClicked = {})
            }
        )
    }
}

@Preview
@Composable
fun Preview() {
    LoginPage(modifier = Modifier)
}