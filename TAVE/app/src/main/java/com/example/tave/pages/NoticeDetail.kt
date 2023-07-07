package com.example.tave.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tave.items.glide.ShimmerEffectItem
import com.example.tave.items.noticeDetail.NoticeDetailCard
import com.example.tave.items.noticeDetail.NoticeDetailLazyGridsPics
import com.example.tave.items.noticeDetail.NoticeDetailPublisherBar
import com.example.tave.items.noticeDetail.NoticeDetailTopBar
import com.example.tave.ui.font.NotoSansKr
import kotlinx.coroutines.delay

@Composable
fun NoticeDetailPage(
    modifier: Modifier,
    mainTitle: String,
    publisherText: String,
    upDateTime: String,
    itemCount: Int,
) {
    var isLoading by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(key1 = true) {
        delay(1000)
        isLoading = false
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            NoticeDetailTopBar(
                modifier = modifier,
                publisher = publisherText
            )
        }
    ) { contentPadding ->
        Column(
            modifier
                .padding(contentPadding)
                .padding(start = 10.dp, end = 10.dp)
        ) {
            NoticeDetailCard(
                modifier = modifier,
                cardTitle = mainTitle,
                isLoading = isLoading,
            )
            NoticeDetailPublisherBar(
                modifier = modifier,
                publisherTxt = publisherText,
                upDateTime = upDateTime,
                isLoading = isLoading
            )
            ShimmerEffectItem(
                isLoading = isLoading,
                contentLoading = {
                    Box(modifier = modifier.fillMaxWidth())
                },
                contentAfterLoading = {
                    Text(
                        text = "테하~!\n" +
                                "안녕하세요! 홍보처 000 입니다!\n" +
                                "후반기 프로젝트 팀 소개 part 1! \n" +
                                "김건우의 팀 단속 팀을 소개합니다!",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = NotoSansKr,
                            fontWeight = FontWeight.Medium,
                            platformStyle = PlatformTextStyle(includeFontPadding = false)
                        )
                    )
                },
                modifier = modifier
            )

            Spacer(modifier = modifier.size(5.dp))
            Divider(modifier = modifier.fillMaxWidth(), thickness = 0.5.dp, Color.Gray)
            Spacer(modifier = modifier.size(5.dp))
            /* TODO: Image Grid View : n*5 Grid */
            NoticeDetailLazyGridsPics(
                itemCount = itemCount,
                imageUrl = { /*TODO*/ },
                modifier = modifier
            )
        }
    }
}

@Composable
@Preview("Notice Detail Page", showBackground = true)
fun PreviewNoticeDetailPage() {
    NoticeDetailPage(
        modifier = Modifier,
        mainTitle = "후반기 프로젝트 팀 소개 Part1. 김건우의 팀 단속",
        publisherText = "TAVE 운영진",
        upDateTime = "6시간 전",
        itemCount = 12,
    )
}