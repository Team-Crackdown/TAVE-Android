package com.example.tave.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import com.example.tave.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tave.items.glide.GlideImageView
import com.example.tave.items.profile.*
import com.example.tave.viewmodel.ProfileViewModel

@Composable
fun ProfilePage(
    modifier: Modifier,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val profileInfo = profileViewModel.userProfile.observeAsState()

    Scaffold { contentPadding ->
        Column(
            modifier = modifier.padding(contentPadding),
            content = {
                ProfileImage(
                    modifier = modifier,
                    imageUrl = profileInfo.value?.userProfileImage?: ""
                )
                Column {
                    ProfileContent(
                        modifier = modifier,
                        userRadix = profileInfo.value?.userRadix,
                        userUniversity = profileInfo.value?.userUniv,
                        userName = profileInfo.value?.userName,
                        userEmail = profileInfo.value?.userEmail,
                        userPhoneNumber = profileInfo.value?.userPhoneNumber,
                        userTechDept = profileInfo.value?.userTech
                    )
                }
            }
        )
    }
}

@Composable
fun ProfileImage(
    modifier: Modifier,
    imageUrl: String
) {
    GlideImageView(
        modifier = modifier.fillMaxWidth().height(300.dp),
        imageUrl = imageUrl,
        contentDescription = "Profile Image",
        painterResource = R.drawable.profile_default
    )
}

@Composable
fun ProfileContent(
    modifier: Modifier,
    userRadix: Int?,
    userUniversity: String?,
    userName: String?,
    userEmail: String?,
    userPhoneNumber: String?,
    userTechDept: String?
) {
    Column(
        modifier = modifier.padding(25.dp),
        content = {
            Row {
                ProfileContentTxtSizeLarge(
                    txt = stringResource(id = R.string.radix),
                    answer = "$userRadix 기"
                )
                Spacer(modifier = modifier.width(16.dp))
                ProfileContentTxtSizeLarge(
                    txt = stringResource(id = R.string.university),
                    answer = userUniversity ?: ""
                )
            }
            Spacer(modifier = modifier.height(20.dp))
            Row {
                ProfileContentTxtSizeLarge(
                    txt = stringResource(id = R.string.name),
                    answer = userName ?: ""
                )
                Spacer(modifier = modifier.width(16.dp))
                ProfileContentTxtSizeSmall(
                    txt = stringResource(id = R.string.email),
                    answer = userEmail ?: ""
                )
            }
            Spacer(modifier = modifier.height(20.dp))
            Row {
                ProfileContentTxtSizeSmall(
                    txt = stringResource(id = R.string.phoneNumber),
                    answer = userPhoneNumber ?: ""
                )
                Spacer(modifier = modifier.width(16.dp))
                ProfileContentTxtSizeSmall(
                    txt = stringResource(id = R.string.dept),
                    answer = userTechDept ?: ""
                )
            }
        }
    )
}