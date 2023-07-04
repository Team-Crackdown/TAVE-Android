package com.example.tave.items.noticeDetail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tave.R
import com.example.tave.items.glide.GlideImageView
import com.example.tave.items.glide.ShimmerEffectItem
import com.example.tave.ui.font.NotoSansKr
import com.example.tave.ui.theme.Shape

@Composable
fun NoticeDetailCard(
    modifier: Modifier,
    cardTitle: String,
    isLoading: Boolean
) {
    ShimmerEffectItem(
        isLoading = isLoading,
        contentLoading = {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(Shape.extraLarge)
                )
                Spacer(modifier = modifier.size(10.dp))
                Box(
                    modifier = modifier
                        .height(10.dp)
                        .clip(Shape.extraLarge)
                )
            }
        },
        contentAfterLoading = {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                GlideImageView(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(Shape.extraLarge),
                    imageUrl = { /*TODO*/ },
                    contentDescription = "Main Card Image",
                    painterResource = R.drawable.tave_cover
                )
                Spacer(modifier = modifier.size(10.dp))
                Text(
                    text = cardTitle,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = NotoSansKr,
                        fontWeight = FontWeight.Bold,
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    )
                )
            }
        },
        modifier = modifier
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewNoticeDetailCard() {
    NoticeDetailCard(modifier = Modifier, cardTitle = "후반기 프로젝트 팀 소개 Part1. 김건우의 팀 단속", isLoading = true)
}