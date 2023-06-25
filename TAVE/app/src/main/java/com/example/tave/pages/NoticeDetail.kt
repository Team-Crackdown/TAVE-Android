package com.example.tave.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tave.R
import com.example.tave.items.noticeDetail.NoticeDetailCard
import com.example.tave.items.noticeDetail.NoticeDetailLazyGridsPics
import com.example.tave.items.noticeDetail.NoticeDetailPublisherBar
import com.example.tave.items.noticeDetail.NoticeDetailTopBar
import com.example.tave.ui.font.NotoSansKr

@Composable
fun NoticeDetailPage(
    modifier: Modifier,
    mainTitle: String,
    publisherText: String,
    upDateTime: String,
    itemCount: Int,
    painterResourceId: Int
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            NoticeDetailTopBar(
                modifier = modifier,
                publisher = publisherText,
                iconButtonOnClick = { /* TODO */ }
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
                cardTitle = mainTitle
            )
            NoticeDetailPublisherBar(
                modifier = modifier,
                publisherTxt = publisherText,
                upDateTime = upDateTime
            )
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
            Spacer(modifier = modifier.size(5.dp))
            Divider(modifier = modifier.fillMaxWidth(), thickness = 0.5.dp, Color.Gray)
            Spacer(modifier = modifier.size(5.dp))
            /* TODO: Image Grid View : n*5 Grid */
            NoticeDetailLazyGridsPics(itemCount = itemCount, painterResourceId = painterResourceId)
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
        painterResourceId = R.drawable.tech_letter
    )
}