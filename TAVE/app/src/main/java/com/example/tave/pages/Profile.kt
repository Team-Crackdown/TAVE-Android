package com.example.tave.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import com.example.tave.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tave.items.profile.*

@Composable
fun ProfilePage() {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        Column{
            Text("HI")
        }
        UploadImageBtn()
        UpdateFloatingBtn(txt = "수정하기")
    }
}

@Composable
fun ProfileImage() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
    ){
        Text("이미지")
    }
}

@Composable
fun ProfileContent(
    radix: String,
    university: String,
    name: String,
    email: String,
    phoneNumber: String,
    dept: String,
    intro: String
) {
    Column(
        modifier = Modifier.padding(25.dp)
    ) {
        Row{
            ProfileContentTxtSizeLarge(txt = stringResource(id = R.string.radix), answer = radix)
            Spacer(modifier = Modifier.width(16.dp))
            ProfileContentTxtSizeLarge(txt = stringResource(id = R.string.university), answer = university)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row{
            ProfileContentTxtSizeLarge(txt = stringResource(id = R.string.name), answer = name)
            Spacer(modifier = Modifier.width(16.dp))
            ProfileContentTxtSizeSmall(txt = stringResource(id = R.string.email), answer = email)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row{
            ProfileContentTxtSizeSmall(txt = stringResource(id = R.string.phoneNumber), answer = phoneNumber)
            Spacer(modifier = Modifier.width(16.dp))
            ProfileContentTxtSizeSmall(txt = stringResource(id = R.string.dept), answer = dept)
        }
        Spacer(modifier = Modifier.height(20.dp))
        ProfileContentTxtSizeOnlyForIntro(txt = stringResource(id = R.string.intro), answer = intro)
    }

}
