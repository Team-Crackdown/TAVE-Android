package com.example.tave.items.notice

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tave.R
import com.example.tave.ui.font.NotoSansKr
import com.example.tave.ui.theme.Shape

@Composable
fun MainNoticeCard(
    modifier: Modifier,
    titleTxt: String,
    writer: String,
    uploadTime: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(277.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(Shape.extraLarge),
            painter = painterResource(id = R.drawable.tave_cover),
            contentScale = ContentScale.FillWidth,
            contentDescription = "MainNoticeCard Image"
        )
        Spacer(modifier = modifier.size(5.dp))
        MainNoticeTitle(titleTxt = titleTxt)
        Spacer(modifier = modifier.size(5.dp))
        MainNoticeWriter(writer = writer)
        Spacer(modifier = modifier.size(5.dp))
        MainNoticeTimeStamp(uploadTime = uploadTime)
    }
}

@Composable
fun MainNoticeTitle(
    titleTxt: String
) {
    Text(
        text = titleTxt,
        modifier = Modifier.padding(start = 10.dp, end = 10.dp),
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = NotoSansKr,
            fontWeight = FontWeight.Bold,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        )
    )
}

@Composable
fun MainNoticeWriter(writer: String) {
    Text(
        text = writer,
        modifier = Modifier.padding(start = 10.dp, end = 10.dp),
        style = TextStyle(
            fontSize = 15.sp,
            fontFamily = NotoSansKr,
            fontWeight = FontWeight.Medium,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        )
    )
}

@Composable
fun MainNoticeTimeStamp(uploadTime: String) {
    Text(
        text = uploadTime,
        modifier = Modifier.padding(start = 10.dp, end = 10.dp),
        style = TextStyle(
            color = Color.DarkGray,
            fontSize = 15.sp,
            fontFamily = NotoSansKr,
            fontWeight = FontWeight.Medium,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        )
    )
}

@Composable
@Preview("Main Notice Card", "Main Notice Card", showBackground = true)
fun PreviewMainNoticeCard() {
    MainNoticeCard(
        modifier = Modifier,
        titleTxt = "후반기 프로젝트 팀 소개 part1 김건우의 팀 단속",
        writer = "TAVE 운영진",
        uploadTime = "1분 전"
    )
}