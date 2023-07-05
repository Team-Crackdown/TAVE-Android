package com.example.tave.items.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.tave.R

@Composable
fun LogInInputField(
    modifier: Modifier,
    emailInput: String,
    passwordInput: String
) {
    var userEmail by remember { mutableStateOf(emailInput) }
    var userPassword by remember { mutableStateOf(passwordInput) }

    Column(
        modifier = modifier.fillMaxWidth(),
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
        }
    )
}