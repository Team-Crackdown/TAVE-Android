package com.example.tave.pages

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tave.R
import com.example.tave.common.util.state.LogInUserState.*
import com.example.tave.items.login.LoginBtn
import com.example.tave.items.login.LoginIntro
import com.example.tave.ui.theme.Shape
import com.example.tave.HomePage
import com.example.tave.SendSMSCodePage
import com.example.tave.common.util.state.LogInUserState

@Composable
fun LoginPage(
    navController: NavController,
    loginState: LogInUserState,
    onLogIn: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    val localContext: Context = LocalContext.current

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
                OutlinedTextField(
                    value = userEmail,
                    onValueChange = { userEmail = it },
                    label = { Text(stringResource(id = R.string.LogIn_Email_Input)) },
                    leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = "") },
                    modifier = modifier
                        .padding(bottom = 10.dp, top = 10.dp)
                        .width(300.dp),
                    shape = Shape.large,
                    singleLine = true
                )
                OutlinedTextField(
                    value = userPassword,
                    onValueChange = { userPassword = it },
                    label = { Text(stringResource(id = R.string.LogIn_PWD_Input)) },
                    leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = "") },
                    modifier = modifier
                        .padding(bottom = 10.dp, top = 10.dp)
                        .width(300.dp),
                    shape = Shape.large,
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                Spacer(modifier = modifier.height(50.dp))

                when (loginState) {
                    is Idle -> LoginBtn(onClicked = { onLogIn(userEmail, userPassword) })
                    is IsLoading -> CircularProgressIndicator()
                    is IsSuccess -> LaunchedEffect(Unit) {
                        navController.navigate(HomePage.route)
                    }
                    is IsSMSCheckNeeded -> LaunchedEffect(Unit) {
                        navController.navigate(SendSMSCodePage.route)
                    }
                    is IsFailed -> {
                        LaunchedEffect(Unit) {
                            Toast.makeText(
                                localContext,
                                "로그인에 실패했습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        LoginBtn(onClicked = { onLogIn(userEmail, userPassword) })
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun Preview() {
    LoginPage(
        navController = rememberNavController(),
        loginState = Idle,
        onLogIn = { _, _ ->}
    )
}