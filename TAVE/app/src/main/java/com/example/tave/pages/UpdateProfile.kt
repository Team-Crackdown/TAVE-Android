package com.example.tave.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.example.tave.R
import com.example.tave.items.profile.UpdateFloatingBtn
import com.example.tave.items.profile.UpdateTextField
import com.example.tave.items.profile.UpdateTextFieldForIntro

@Composable
fun UpdateProfilePage(modifier: Modifier, navController: NavController) {
    Scaffold(
        floatingActionButton = { UpdateFloatingBtn(
            txt = "수정하기",
            onClicked = { navController.navigate("updateProfile") }
        ) }
    ) { contentPadding ->
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(contentPadding)
        ) {
            UpdateTextField(text = stringResource(id = R.string.name), keyboardType = KeyboardType.Text)
            UpdateTextField(text = stringResource(id = R.string.email), keyboardType = KeyboardType.Email)
            UpdateTextField(text = stringResource(id = R.string.phoneNumber), keyboardType = KeyboardType.Phone)
            UpdateTextField(text = stringResource(id = R.string.university), keyboardType = KeyboardType.Text)
            UpdateTextField(text = stringResource(id = R.string.radix), keyboardType = KeyboardType.Text)
            UpdateTextField(text = stringResource(id = R.string.dept), keyboardType = KeyboardType.Text)
            UpdateTextFieldForIntro(text = stringResource(id = R.string.intro))
        }
    }
}