package com.example.tave.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tave.R
import com.example.tave.items.login.LoginBtn
import com.example.tave.ui.font.NotoSansKr

@Composable
fun LoginPage() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                    style = TextStyle(
                        fontSize = 40.sp,
                        fontFamily = NotoSansKr,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.Blue
                    )
                )
                LoginBox(Modifier)
            }
        )
    }
}


@Composable
private fun LoginBox(
    modifier: Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxWidth().padding(10.dp).background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Enter your e-mail") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "person") },
            modifier = modifier
                .padding(bottom = 10.dp, top = 10.dp)
                .width(300.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Enter your password") },
            leadingIcon = { Icon(Icons.Default.Info, contentDescription = "password") },
            modifier = modifier
                .padding(bottom = 10.dp, top = 10.dp)
                .width(300.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        LoginBtn(
            txt = "로그인",
            onClicked = { /*TODO*/ }
        )
    }
}
