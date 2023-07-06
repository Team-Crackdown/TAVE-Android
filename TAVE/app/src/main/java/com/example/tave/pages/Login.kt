package com.example.tave.pages

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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tave.R
import com.example.tave.TaveApplication
import com.example.tave.items.login.LoginBtn
import com.example.tave.items.login.LoginIntro
import com.example.tave.ui.theme.Shape
import com.example.tave.ui.theme.TAVETheme
import com.example.tave.viewmodel.LogInViewModel

@Composable
fun LoginPage(
    modifier: Modifier,
    navController: NavController,
    logInViewModel: LogInViewModel = hiltViewModel()
) {
    if (TaveApplication.authPrefs.getTokenValue("accessToken", "").isNotEmpty()) {
        navController.navigate("HomePage")
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val localContext = LocalContext.current

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
                OutlinedTextField(
                    value = userEmail,
                    onValueChange = { userEmail = it },
                    label = { Text(stringResource(id = R.string.Enter_Email)) },
                    leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = stringResource(id = R.string.email)) },
                    modifier = modifier
                        .padding(bottom = 10.dp, top = 10.dp)
                        .width(300.dp),
                    shape = Shape.large,
                    singleLine = true
                )
                OutlinedTextField(
                    value = userPassword,
                    onValueChange = { userPassword = it },
                    label = { Text(stringResource(id = R.string.Enter_Pwd)) },
                    leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = stringResource(id = R.string.Password)) },
                    modifier = modifier
                        .padding(bottom = 10.dp, top = 10.dp)
                        .width(300.dp),
                    shape = Shape.large,
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                Spacer(modifier = modifier.height(50.dp))
                LoginBtn(onClicked = {
                    logInViewModel.userLogInAccount(userEmail, userPassword)
                    logInViewModel.logInResult.observe(lifecycleOwner) {
                        if (it.isSuccess) {
                            logInViewModel.isCheckedSMS.observe(lifecycleOwner) { result ->
                                if (result) {
                                    navController.navigate("HomePage")
                                } else {
                                    navController.navigate("SendSMSCodePage")
                                }
                            }
                        } else {
                            Toast.makeText(
                                localContext,
                                "로그인에 실패했습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                })
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewLoginPage() {
    TAVETheme {
        LoginPage(modifier = Modifier, navController = rememberNavController())
    }
}