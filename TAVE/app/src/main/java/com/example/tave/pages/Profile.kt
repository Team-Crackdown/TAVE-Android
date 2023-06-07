package com.example.tave.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import com.example.tave.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tave.items.profile.ProfileContentTxtSizeLarge
import com.example.tave.items.profile.ProfileContentTxtSizeSmall
import com.example.tave.items.profile.UpdateFloatingBtn

@Composable
fun profilePage() {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        Column{
            Text("HI")

        }
        uploadImageBtn()
        UpdateFloatingBtn(txt = "수정하기")
    }
}

@Composable
fun profileImage() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ){

    }
}

@Composable
fun uploadImageBtn() {
    ElevatedButton(
        modifier = Modifier.size(50.dp, 50.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(5.dp),
        colors = ButtonDefaults.buttonColors(),
        onClick = {}
    ) {
        Image(
            painter = painterResource(R.drawable.baseline_file_upload_24),
            contentDescription = "upload",
        )
    }
}

@Composable
fun profileContent(
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
            ProfileContentTxtSizeLarge(txt = "기수", answer = radix)
            Spacer(modifier = Modifier.width(77.dp))
            ProfileContentTxtSizeLarge(txt = "대학교", answer = university)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row{
            ProfileContentTxtSizeLarge(txt = "이름", answer = name)
            Spacer(modifier = Modifier.width(77.dp))
            ProfileContentTxtSizeSmall(txt = "이메일", answer = email)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row{
            ProfileContentTxtSizeSmall(txt = "전화", answer = phoneNumber)
            Spacer(modifier = Modifier.width(77.dp))
            ProfileContentTxtSizeSmall(txt = "분야", answer = dept)
        }
        Spacer(modifier = Modifier.height(20.dp))
        ProfileContentTxtSizeSmall(txt = "자기소개", answer = intro)
    }

}


@Composable
@Preview
fun preview1() {
    profileContent(
        "11기",
        "테이브 대학교",
        "김테비",
        "kimTavy@tave.com",
        "010-0000-0000",
        "앱(Android)",
        "안녕하세요 \n저는 김테비입니다."
    )
}
