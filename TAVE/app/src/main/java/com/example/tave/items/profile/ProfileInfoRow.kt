package com.example.tave.items.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tave.R
import com.example.tave.ui.font.NotoSansKr
import com.example.tave.ui.theme.Shape

@Composable
fun ProfileInfoRow(
    modifier: Modifier,
    userRadix: Int?,
    userDivision: String?,
    userTechDept: String?
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .shadow(2.dp)
            .clip(Shape.extraLarge),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        content = {
            Column(
                modifier = modifier.padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_radix),
                        modifier = modifier.size(24.dp),
                        contentDescription = ""
                    )
                    Text(
                        text = "$userRadix 기",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = NotoSansKr,
                            fontWeight = FontWeight.SemiBold,
                            platformStyle = PlatformTextStyle(includeFontPadding = false)
                        )
                    )
                }
            )
            Divider(modifier = modifier.width(0.2.dp))
            Column(
                modifier = modifier.padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        modifier = modifier.size(24.dp),
                        contentDescription = ""
                    )
                    Text(
                        text = userDivision ?: "",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = NotoSansKr,
                            fontWeight = FontWeight.SemiBold,
                            platformStyle = PlatformTextStyle(includeFontPadding = false)
                        )
                    )
                }
            )
            Divider(modifier = modifier.width(0.2.dp))
            Column(
                modifier = modifier.padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_dept),
                        modifier = modifier.size(24.dp),
                        contentDescription = ""
                    )
                    Text(
                        text = userTechDept ?: "",
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