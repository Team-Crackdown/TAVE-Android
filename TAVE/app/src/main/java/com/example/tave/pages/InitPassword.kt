package com.example.tave.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tave.items.login.LoginBtn

@Composable
private fun InitPasswordPage(
    modifier: Modifier
) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Surface(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("새로운 비밀번호") },
                modifier = modifier
                    .padding(bottom = 10.dp, top = 10.dp)
                    .width(300.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("새로운 비밀번호 확인") },
                modifier = modifier
                    .padding(bottom = 10.dp, top = 10.dp)
                    .width(300.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = modifier.height(70.dp))
            /** padding 값 확인 부탁드립니다 */
            Column(
                modifier = modifier.padding(start = 100.dp)
            ){
                LoginBtn(
                    txt = "변경하기",
                    onClicked = {/*TODO*/}
                )
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun PreviewInitPassword() {
    InitPasswordPage(modifier = Modifier)
}