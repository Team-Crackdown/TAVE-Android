package com.example.tave.items.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.*

@Composable
fun UpdateTextField(text: String, keyboardType: KeyboardType) {
    var txt by remember { mutableStateOf(TextFieldValue("")) }

    Column{
        UpdateProfileTxt(text = text)
        TextField(
            value = txt,
            onValueChange = { newText ->
                txt = newText
            },
            colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent),
            label = { Text(text = text, style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold)) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(15.dp))
    }
}

@Composable
fun UpdateTextFieldForIntro(text: String) {
    var txt by remember { mutableStateOf(TextFieldValue("")) }

    Column{
        UpdateProfileTxt(text = text)
        OutlinedTextField(
            value = txt,
            onValueChange = { newText ->
                txt = newText
            },
            colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent),
            label = { Text(text = text, style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(15.dp))
    }
}