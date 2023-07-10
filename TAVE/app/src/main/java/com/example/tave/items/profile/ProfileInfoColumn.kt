package com.example.tave.items.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tave.R
import com.example.tave.ui.font.NotoSansKr

@Composable
fun ProfileInfoColumn(
    modifier: Modifier,
    userUniv: String?,
    userEmail: String?,
    userPhoneNumber: String?
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start,
        content = {
            Row(
                modifier = modifier.padding(25.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_businees),
                        modifier = modifier.size(24.dp),
                        contentDescription = ""
                    )
                    Spacer(modifier = modifier.size(10.dp))
                    Text(
                        text = userUniv ?: "",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = NotoSansKr,
                            fontWeight = FontWeight.SemiBold,
                            platformStyle = PlatformTextStyle(includeFontPadding = false)
                        )
                    )
                }
            )
            Row(
                modifier = modifier.padding(25.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        modifier = modifier.size(24.dp),
                        contentDescription = ""
                    )
                    Spacer(modifier = modifier.size(10.dp))
                    Text(
                        text = userEmail ?: "",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = NotoSansKr,
                            fontWeight = FontWeight.SemiBold,
                            platformStyle = PlatformTextStyle(includeFontPadding = false)
                        )
                    )
                }
            )
            Row(
                modifier = modifier.padding(25.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    Icon(
                        imageVector = Icons.Outlined.Phone,
                        modifier = modifier.size(24.dp),
                        contentDescription = ""
                    )
                    Spacer(modifier = modifier.size(10.dp))
                    Text(
                        text = userPhoneNumber ?: "",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = NotoSansKr,
                            fontWeight = FontWeight.SemiBold,
                            platformStyle = PlatformTextStyle(includeFontPadding = false)
                        )
                    )
                }
            )
        }
    )
}