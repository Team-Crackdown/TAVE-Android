package com.example.tave.pages

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tave.common.util.state.InitPasswordState.*
import com.example.tave.items.initpassword.InitPasswordBtn
import com.example.tave.items.initpassword.InitPasswordLogo
import com.example.tave.ui.theme.Shape
import com.example.tave.viewmodel.InitPasswordViewModel
import com.example.tave.HomePage

@Composable
fun InitPasswordPage(
    modifier: Modifier,
    navController: NavController,
    initPWViewModel: InitPasswordViewModel = hiltViewModel()
) {
    val localContext: Context = LocalContext.current
    val isPasswordChange by initPWViewModel.isPasswordChanged.collectAsState()

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

            when (isPasswordChange) {
                is Idle -> InitPasswordBtn { initPWViewModel.validatePassword(password, confirmPassword) }
                is IsLoading -> CircularProgressIndicator()
                is IsComplete -> LaunchedEffect(Unit) { navController.navigate(HomePage.route) }
                is IsFailed -> {
                    LaunchedEffect(Unit) {
                        Toast.makeText(
                            localContext,
                            "변경에 실패 했습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    InitPasswordBtn { initPWViewModel.validatePassword(password, confirmPassword) }
                }
            }
        }
    }
}