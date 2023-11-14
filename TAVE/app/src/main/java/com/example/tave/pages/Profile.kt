package com.example.tave.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domain.entity.profile.UserProfileEntity
import com.example.tave.items.profile.*

@Composable
fun ProfilePage(
    userProfile: UserProfileEntity?,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        content = { contentPadding ->
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
                            userName = userProfile?.userName ?: "",
                            userProfileImage = userProfile?.userProfileImage?: ""
                        )
                    }
                )
                Spacer(modifier = modifier.size(20.dp))
                ProfileInfoRow(
                    modifier = modifier,
                    userRadix = userProfile?.userRadix,
                    userDivision = userProfile?.userType,
                    userTechDept = userProfile?.userTech
                )
                ProfileInfoColumn(
                    modifier = modifier,
                    userUniv = userProfile?.userUniv,
                    userEmail = userProfile?.userEmail,
                    userPhoneNumber = userProfile?.userPhoneNumber
                )
            }
        }
    )
}