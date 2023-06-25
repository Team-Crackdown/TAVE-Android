package com.example.tave.pages

import NoticeTechLetterItems
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tave.R
import com.example.tave.items.notice.MainNoticeCard
import com.example.tave.items.notice.NoticeSubItems
import com.example.tave.ui.font.NotoSansKr

@Composable
fun NoticePage(
    modifier: Modifier,
    mainTitle: String,
    mainWriter: String,
    mainUploadTime: String,
    subItemTitle: String,
    subItemWriter: String,
    subItemTimeStamp: String,
    painterResourceId: Int,
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
                Divider(
                    color = Color.Black.copy(alpha = 0.2f),
                    modifier = Modifier.fillMaxWidth()
                )
                LazyColumn{
                    items(count = 3) {
                        NoticeSubItems(
                            modifier = modifier.height(66.dp),
                            subItemTitle = subItemTitle,
                            subItemWriter = subItemWriter,
                            subItemTimeStamp = subItemTimeStamp
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Divider(
                            color = Color.Black.copy(alpha = 0.2f),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
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
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow{
                    items(count = 3){
                        NoticeTechLetterItems(painterResourceId = painterResourceId)
                    }
                }
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewNoticePage() {
    NoticePage(
        modifier = Modifier,
        mainTitle = "후반기 프로젝트 팀 소개 part1 김건우의 팀 단속",
        mainWriter = "TAVE 운영진",
        mainUploadTime = "1분 전",
        subItemTitle = "후반기 프로젝트 보증금 안내",
        subItemWriter = "TAVE 운영진",
        subItemTimeStamp = "1시간 전",
        painterResourceId = R.drawable.tech_letter
    )
}