package com.example.tave.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tave.items.profile.*
import com.example.tave.viewmodel.ProfileViewModel

@Composable
fun ProfilePage(
    modifier: Modifier,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val profileInfo = profileViewModel.userProfile.observeAsState()

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { contentPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .padding(contentPadding),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    ProfileBox(
                        modifier = modifier,
                        userName = profileInfo.value?.userName ?: "",
                        userProfileImage = profileInfo.value?.userProfileImage?: ""
                    )
                }
            )
            Spacer(modifier = modifier.size(20.dp))
            ProfileInfoRow(
                modifier = modifier,
                userRadix = profileInfo.value?.userRadix,
                userDivision = profileInfo.value?.userType,
                userTechDept = profileInfo.value?.userTech
            )
            ProfileInfoColumn(
                modifier = modifier,
                userUniv = profileInfo.value?.userUniv,
                userEmail = profileInfo.value?.userEmail,
                userPhoneNumber = profileInfo.value?.userPhoneNumber
            )
        }
    }
}