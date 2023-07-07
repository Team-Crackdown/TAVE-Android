package com.example.tave.pages

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tave.LogInPage
import com.example.tave.items.initpassword.InitPasswordBtn
import com.example.tave.items.initpassword.InitPasswordLogo
import com.example.tave.ui.theme.Shape
import com.example.tave.viewmodel.InitPasswordViewModel

@Composable
fun InitPasswordPage(
    modifier: Modifier,
    navController: NavController,
    initPasswordViewModel: InitPasswordViewModel = hiltViewModel()
) {
    val localContext = LocalContext.current
    val isPasswordMatch = initPasswordViewModel.isNotMatchPassword.observeAsState()
    val isChangedComplete = initPasswordViewModel.isChangedComplete.observeAsState()

    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Surface(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            InitPasswordLogo(modifier = modifier)
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("새로운 비밀번호") },
                modifier = modifier
                    .padding(bottom = 10.dp, top = 10.dp)
                    .width(300.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                shape = Shape.large
            )
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("새로운 비밀번호 확인") },
                modifier = modifier
                    .padding(bottom = 10.dp, top = 10.dp)
                    .width(300.dp),
                shape = Shape.large,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = modifier.height(70.dp))
            InitPasswordBtn(onClicked = {
                initPasswordViewModel.validateConfirmPassword(password, confirmPassword)
                if (isPasswordMatch.value == false) {
                    Toast.makeText(
                        localContext,
                        "비밀번호가 일치하지 않습니다. 다시 작성해 주세요",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    if (isChangedComplete.value!!.isSuccess) {
                        navController.navigate(route = LogInPage.route)
                    }
                }
            })
        }
    }
}