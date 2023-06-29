package com.example.tave.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import com.example.tave.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tave.items.profile.*
import com.example.tave.viewmodel.ProfileViewModel

@Composable
fun ProfilePage(
    modifier: Modifier,
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    Scaffold(
        floatingActionButton = {
            UpdateFloatingBtn(
                txt = "수정하기",
                onClicked = { navController.navigate("updateProfile") }
            )
        }
    ) { contentPadding ->
        Column(modifier = modifier.padding(contentPadding)) {
            ProfileImage(modifier = modifier)
            ProfileContent(
                modifier = modifier,
                viewModel.userProfile.value?.userRadix.toString(),
                viewModel.userProfile.value?.userUniv.toString(),
                viewModel.userProfile.value?.userUniv.toString(),
                "kimTavy@tave.com",
                "010-0000-0000",
                "앱(Android)",
                "안녕하세요 \n저는 김테비입니다.\n" +
                "안녕하세요 \n저는 김테비입니다.\n" +
                        "저는 김테비입니다.\n" +
                        "저는 김테비입니다.\n" +
                        "저는 김테비입니다.\n" +
                        "저는 김테비입니다."
            )
        }
    }
}

@Composable
fun ProfileImage(modifier: Modifier) {
    Box(modifier = modifier
        .fillMaxWidth()
        .height(300.dp)) {
        Text("이미지")
    }
}

@Composable
fun ProfileContent(
    modifier: Modifier,
    radix: String,
    university: String,
    name: String,
    email: String,
    phoneNumber: String,
    dept: String,
    intro: String
) {
    Column(
        modifier = modifier.padding(25.dp)
    ) {
        Row{
            ProfileContentTxtSizeLarge(txt = stringResource(id = R.string.radix), answer = radix)
            Spacer(modifier = modifier.width(16.dp))
            ProfileContentTxtSizeLarge(txt = stringResource(id = R.string.university), answer = university)
        }
        Spacer(modifier = modifier.height(20.dp))
        Row{
            ProfileContentTxtSizeLarge(txt = stringResource(id = R.string.name), answer = name)
            Spacer(modifier = modifier.width(16.dp))
            ProfileContentTxtSizeSmall(txt = stringResource(id = R.string.email), answer = email)
        }
        Spacer(modifier = modifier.height(20.dp))
        Row{
            ProfileContentTxtSizeSmall(txt = stringResource(id = R.string.phoneNumber), answer = phoneNumber)
            Spacer(modifier = modifier.width(16.dp))
            ProfileContentTxtSizeSmall(txt = stringResource(id = R.string.dept), answer = dept)
        }
        Spacer(modifier = modifier.height(20.dp))
        ProfileContentTxtSizeOnlyForIntro(txt = stringResource(id = R.string.intro), answer = intro)
    }

}


@Composable
@Preview("Profile Preview")
fun ProfilePreview() {
    Scaffold { contentPadding ->
        Column(modifier = Modifier.padding(contentPadding)) {
            ProfileImage(modifier = Modifier)
            ProfileContent(
                modifier = Modifier,
                "11기",
                "테이브 대학교",
                "김테비",
                "kimTavy@tave.com",
                "010-0000-0000",
                "앱(Android)",
                "안녕하세요 \n저는 김테비입니다.\n" +
                        "저는 김테비입니다.\n" +
                        "저는 김테비입니다.\n" +
                        "저는 김테비입니다.\n" +
                        "저는 김테비입니다."
            )
        }
    }
}
