package com.example.tave.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onInterceptKeyBeforeSoftKeyboard
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tave.R
import com.example.tave.items.profile.UpdateFloatingBtn
import com.example.tave.items.profile.UpdateTextField
import com.example.tave.items.profile.UpdateTextFieldForIntro

@Composable
fun UpdateProfilePage() {

    Scaffold(
        floatingActionButton = { UpdateFloatingBtn(txt = "수정하기") }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
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



@Composable
@Preview
fun preview2() {
    UpdateProfilePage()
}