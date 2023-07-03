package com.example.tave.pages

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tave.R
import com.example.tave.items.login.LoginBtn
import com.example.tave.ui.font.NotoSansKr

@Composable
fun LoginPage(modifier: Modifier) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    style = TextStyle(
                        fontSize = 40.sp,
                        fontFamily = NotoSansKr,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.Blue
                    )
                )
                LoginBox(modifier)
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
        modifier = modifier.fillMaxWidth().padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(id = R.string.Enter_Email)) },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = stringResource(id = R.string.email)) },
            modifier = modifier
                .padding(bottom = 10.dp, top = 10.dp)
                .width(300.dp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(id = R.string.Enter_Pwd)) },
            leadingIcon = { Icon(Icons.Default.Info, contentDescription = stringResource(id = R.string.Password)) },
            modifier = modifier
                .padding(bottom = 10.dp, top = 10.dp)
                .width(300.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = modifier.height(10.dp))
        LoginBtn(
            txt = stringResource(id = R.string.Login),
            onClicked = { /*TODO*/ }
        )
        Spacer(modifier = modifier.height(10.dp))
    }
}

@Composable
@Preview
fun PreviewLogin(){
    LoginPage(modifier = Modifier)
}
