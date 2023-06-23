package com.example.tave.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tave.R
import com.example.tave.items.notice.MainNoticeCard
import com.example.tave.ui.font.NotoSansKr

@Composable
fun NoticePage(
    modifier: Modifier,
    mainTitle: String,
    mainWriter: String,
    mainUploadTime: String
) {
    Scaffold(modifier = modifier.padding(10.dp)) { contentPadding ->
        Column(
            modifier = modifier.padding(contentPadding),
            content = {
                Text(
                    text = stringResource(id = R.string.Notice),
                    modifier = modifier.padding(bottom = 10.dp),
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = NotoSansKr,
                        fontWeight = FontWeight.Bold,
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    )
                )
                MainNoticeCard(
                    modifier = modifier,
                    titleTxt = mainTitle,
                    writer = mainWriter,
                    uploadTime = mainUploadTime
                )
                LazyColumn(
                    modifier = modifier.padding(bottom = 10.dp),
                    content = { /* TODO: Implement NoticeSubItems into LazyColumn */ }
                )
                Text(
                    text = stringResource(id = R.string.Tech_Letter),
                    modifier = modifier.padding(bottom = 10.dp),
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = NotoSansKr,
                        fontWeight = FontWeight.Bold,
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    )
                )
                LazyRow(content = {  /* TODO: Implement TechLetterItems into LazyRow */  })
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewNoticePage(){
    NoticePage(
        modifier = Modifier,
        mainTitle = "후반기 프로젝트 팀 소개 part1 김건우의 팀 단속",
        mainWriter = "TAVE 운영진",
        mainUploadTime = "1분 전"
    )
}