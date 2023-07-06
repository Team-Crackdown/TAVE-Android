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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tave.R
import com.example.tave.items.notice.MainNoticeCard
import com.example.tave.items.notice.NoticeSubItems
import com.example.tave.ui.font.NotoSansKr
import com.example.tave.ui.theme.Shape

class Notice(val url: String, val title: String, val content: String, val writer: String, val timeStamp: String)
val notices = mutableListOf(
    Notice(
        url = "https://t1.daumcdn.net/cfile/tistory/99CEA0485C4947381E", title = "후반기 프로젝트 보증금 안내", content = "테-하! 안녕하세요, 경영처 회계부 진수빈입니다.\n" +
                "후반기 프로젝트 보증금 안내를 드리고자합니다.", writer = "TAVE 운영진", timeStamp = "1시간 전"
    ),
    Notice(
        url = "https://t1.daumcdn.net/cfile/tistory/99CEA0485C4947381E", title = "후반기 만남의 장 회원 소개 part2", content = "테-하! 안녕하세요, 경영처장 박정재입니다.\n" +
                "이어서 금일은 회원 소개 2탄으로 돌아왔습니다.", writer = "TAVE 운영진", timeStamp = "30분 전"
    ),
    Notice(
        url = "https://t1.daumcdn.net/cfile/tistory/99CEA0485C4947381E", title = "후반기 만남의 장 회원 소개 part1", content = "테-하! 안녕하세요, 경영처 기획부 김태건입니다.\n" +
                "이번주 토요일에 예정된 만남의 장에서는 새로운 회원분들과 소통하며 후반기 프로젝트를 기획하는...", writer = "TAVE 운영진", timeStamp = "1분 전"
    )
)

class TechLetter(val url: String)

val techLetters = mutableListOf(
    TechLetter(url = "https://is4-ssl.mzstatic.com/image/thumb/Purple124/v4/6a/7b/d8/6a7bd847-25e1-c062-db00-5299ba803f69/source/512x512bb.jpg"),
    TechLetter(url = "https://t1.daumcdn.net/cfile/tistory/99CEA0485C4947381E"),
    TechLetter(url = "https://is4-ssl.mzstatic.com/image/thumb/Purple124/v4/6a/7b/d8/6a7bd847-25e1-c062-db00-5299ba803f69/source/512x512bb.jpg")
)


@Composable
fun NoticePage(
    modifier: Modifier,
    navController: NavController
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
                    titleTxt = "후반기 프로젝트 팀 소개 part1 김건우의 팀 단속",
                    writer = "TAVE 운영진",
                    uploadTime = "1분 전",
                    imageUrl = { /*TODO*/ }
                )
                Divider(
                    color = Color.Black.copy(alpha = 0.2f),
                    modifier = modifier.fillMaxWidth()
                )
                LazyColumn {
                    items(notices.size) { index ->
                        NoticeSubItems(
                            modifier = modifier.height(66.dp),
                            subItemTitle = notices[index].title,
                            subItemWriter = notices[index].writer,
                            subItemTimeStamp = notices[index].timeStamp,
                            imageUrl = { notices[index].url },
                            onClick = { index ->
                                print("$index")
                                navController.navigate("NoticeDetailPage")
                            },
                            index = index
                        )
                        Spacer(modifier = modifier.height(5.dp))
                        Divider(
                            color = Color.Black.copy(alpha = 0.2f),
                            modifier = modifier.fillMaxWidth()
                        )
                    }
                }
                Spacer(modifier = modifier.height(10.dp))
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
                Spacer(modifier = modifier.height(10.dp))
                LazyRow {
                    items(count = techLetters.size) { index ->
                        NoticeTechLetterItems(
                            modifier = modifier
                                .size(150.dp)
                                .clip(shape = Shape.large),
                            imageUrl = { techLetters[index].url }
                        )
                        Spacer(modifier = modifier.width(5.dp))
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewNoticePage() {
    NoticePage(modifier = Modifier, navController = rememberNavController())
}