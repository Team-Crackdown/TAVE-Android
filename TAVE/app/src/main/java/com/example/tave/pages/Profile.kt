package com.example.tave.pages

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import com.example.tave.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tave.items.ProfileContentTxtSizeLarge
import com.example.tave.items.ProfileContentTxtSizeSmall
import com.example.tave.items.UpdateFloatingBtn
import com.example.tave.ui.theme.LightColorPalette

@Composable
fun profilePage(context: Context, navController: NavController) {
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
fun profileContent() {
    Column(
        modifier = Modifier.padding(25.dp)
    ) {
        Row{
            ProfileContentTxtSizeLarge(txt = "기수", answer = "10기")
            Spacer(modifier = Modifier.width(77.dp))
            ProfileContentTxtSizeLarge(txt = "대학교", answer = "베를린 공과 대학")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row{
            ProfileContentTxtSizeLarge(txt = "이름", answer = "김테비")
            Spacer(modifier = Modifier.width(77.dp))
            ProfileContentTxtSizeSmall(txt = "이메일", answer = "KimTavy@Tavy.com")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row{
            ProfileContentTxtSizeSmall(txt = "전화", answer = "010-0000-0000")
            Spacer(modifier = Modifier.width(77.dp))
            ProfileContentTxtSizeSmall(txt = "분야", answer = "앱(Android)")
        }
        Spacer(modifier = Modifier.height(20.dp))
        ProfileContentTxtSizeSmall(txt = "자기소개", answer = "안녕하세요. 베를린 공대에 다니고 있는 11기 김테비 입니다")
    }

}


@Composable
@Preview
fun preview1() {
    Scaffold(
        floatingActionButton = { UpdateFloatingBtn(txt = "수정하기") }
    ) {
        profileContent()
    }
}
