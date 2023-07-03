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
import com.example.tave.items.glide.GlideImageView
import com.example.tave.items.profile.*
import com.example.tave.viewmodel.ProfileViewModel

@Composable
fun ProfilePage(
    modifier: Modifier,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    Scaffold { contentPadding ->
        Column(
            modifier = modifier.padding(contentPadding),
            content = {
                ProfileImage(
                    modifier = modifier,
                    imageUrl = { profileViewModel.userProfile.value?.userProfileImage }
                )
                ProfileContent(
                    modifier = modifier,
                    profileViewModel.userProfile.value?.userRadix.toString(),
                    profileViewModel.userProfile.value?.userUniv.toString(),
                    profileViewModel.userProfile.value?.userUniv.toString(),
                    profileViewModel.userProfile.value?.userEmail.toString(),
                    profileViewModel.userProfile.value?.userPhoneNumber.toString(),
                    profileViewModel.userProfile.value?.userTech.toString()
                )
            }
        )
    }
}

@Composable
fun ProfileImage(
    modifier: Modifier,
    imageUrl: () -> Unit
) {
    GlideImageView(
        modifier = modifier.fillMaxWidth().height(300.dp),
        imageUrl = imageUrl,
        contentDescription = "Profile Image",
        painterResource = R.drawable.tave_profile
    )
}

@Composable
fun ProfileContent(
    modifier: Modifier,
    radix: String,
    university: String,
    name: String,
    email: String,
    phoneNumber: String,
    dept: String
) {
    Column(
        modifier = modifier.padding(25.dp),
        content = {
            Row {
                ProfileContentTxtSizeLarge(txt = stringResource(id = R.string.radix), answer = radix)
                Spacer(modifier = modifier.width(16.dp))
                ProfileContentTxtSizeLarge(txt = stringResource(id = R.string.university), answer = university)
            }
            Spacer(modifier = modifier.height(20.dp))
            Row {
                ProfileContentTxtSizeLarge(txt = stringResource(id = R.string.name), answer = name)
                Spacer(modifier = modifier.width(16.dp))
                ProfileContentTxtSizeSmall(txt = stringResource(id = R.string.email), answer = email)
            }
            Spacer(modifier = modifier.height(20.dp))
            Row {
                ProfileContentTxtSizeSmall(txt = stringResource(id = R.string.phoneNumber), answer = phoneNumber)
                Spacer(modifier = modifier.width(16.dp))
                ProfileContentTxtSizeSmall(txt = stringResource(id = R.string.dept), answer = dept)
            }
        }
    )
}