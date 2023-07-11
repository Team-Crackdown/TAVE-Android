package com.example.tave.items.noticeDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tave.R
import com.example.tave.items.glide.ShimmerEffectItem
import com.example.tave.ui.font.NotoSansKr
import com.example.tave.ui.theme.Shape

@Composable
fun NoticeDetailPublisherBar(
    modifier: Modifier,
    publisherTxt: String,
    upDateTime: String,
    isLoading: Boolean,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier
                .size(30.dp)
                .clip(shape = Shape.large),
            painter = painterResource(id = R.drawable.tave_profile),
            contentDescription = ""
        )
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            content = {
                PublisherTxt(modifier = modifier, publisherTxt = publisherTxt, isLoading = isLoading)
                PublishDateTime(modifier = modifier, upDateTime = upDateTime, isLoading = isLoading)
            }
        )
    }
}

@Composable
fun PublisherTxt(
    modifier: Modifier,
    publisherTxt: String,
    isLoading: Boolean
) {
    ShimmerEffectItem(
        isLoading = isLoading,
        contentLoading = {
            Box(
                modifier = modifier
                    .padding(start = 10.dp)
                    .fillMaxWidth()
            )
        },
        contentAfterLoading = {
            Text(
                text = publisherTxt,
                modifier = modifier.padding(start = 10.dp),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = NotoSansKr,
                    fontWeight = FontWeight.Medium,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
        },
        modifier = modifier
    )
}

@Composable
fun PublishDateTime(
    modifier: Modifier,
    upDateTime: String,
    isLoading: Boolean
) {
    ShimmerEffectItem(
        isLoading = isLoading,
        contentLoading = {
            Box(modifier = modifier
                .padding(start = 10.dp)
                .fillMaxWidth())
        },
        contentAfterLoading = {
            Text(
                text = upDateTime,
                modifier = modifier.padding(start = 10.dp),
                style = TextStyle(
                    fontSize = 11.sp,
                    fontFamily = NotoSansKr,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )
        },
        modifier = modifier
    )
}