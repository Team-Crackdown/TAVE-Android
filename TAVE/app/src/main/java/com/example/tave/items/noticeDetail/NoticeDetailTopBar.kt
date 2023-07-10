package com.example.tave.items.noticeDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tave.R
import com.example.tave.ui.font.NotoSansKr
import com.example.tave.ui.theme.Shape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoticeDetailTopBar(
    modifier: Modifier,
    publisher: String
) {
    TopAppBar(
        title = {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = modifier
                        .size(30.dp)
                        .clip(shape = Shape.large),
                    painter = painterResource(id = R.drawable.tave_profile),
                    contentDescription = "Publisher Profile Image"
                )
                Spacer(modifier = modifier.size(10.dp))
                Text(
                    text = "Published in: $publisher",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = NotoSansKr,
                        fontWeight = FontWeight.Medium,
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    )
                )
            }
        }
    )
}