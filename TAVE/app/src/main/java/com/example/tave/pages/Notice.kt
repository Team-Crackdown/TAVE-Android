package com.example.tave.pages

import NoticeTechLetterItems
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tave.R
import com.example.tave.items.notice.MainNoticeCard
import com.example.tave.items.notice.NoticeSubItems
import com.example.tave.ui.font.NotoSansKr
import com.example.tave.ui.theme.Shape
import com.example.tave.viewmodel.NoticeViewModel

class TechLetter(val url: String)

val techLetters = mutableListOf(
    TechLetter(url = "https://is4-ssl.mzstatic.com/image/thumb/Purple124/v4/6a/7b/d8/6a7bd847-25e1-c062-db00-5299ba803f69/source/512x512bb.jpg"),
    TechLetter(url = "https://t1.daumcdn.net/cfile/tistory/99CEA0485C4947381E"),
    TechLetter(url = "https://is4-ssl.mzstatic.com/image/thumb/Purple124/v4/6a/7b/d8/6a7bd847-25e1-c062-db00-5299ba803f69/source/512x512bb.jpg")
)


@Composable
fun NoticePage(
    modifier: Modifier,
    navController: NavController,
    noticeViewModel: NoticeViewModel = hiltViewModel()
) {
    val noticeMainCard = noticeViewModel.noticeMainData.observeAsState()
    val noticeSubItems = noticeViewModel.noticeSubDate.observeAsState()

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
                    titleTxt = "${noticeMainCard.value?.title}",
                    writer = "${noticeMainCard.value?.adminId}",
                    uploadTime = "${noticeMainCard.value?.createdTime}",
                    navController = navController,
                    imageUrl = {  }
                )
                Divider(
                    color = Color.Black.copy(alpha = 0.2f),
                    modifier = modifier.fillMaxWidth()
                )
                LazyColumn {
                    noticeSubItems.value?.size?.let {
                        items(it) { index ->
                            NoticeSubItems(
                                modifier = modifier,
                                subItemTitle = "${noticeSubItems.value?.get(index)?.title}",
                                subItemWriter = "${noticeSubItems.value?.get(index)?.adminId}",
                                subItemTimeStamp = "${noticeSubItems.value?.get(index)?.createdTime}",
                                imageUrl = {  },
                                onClick = { _ -> navController.navigate("NoticeDetailPage") },
                                index = index
                            )
                            Spacer(modifier = modifier.height(5.dp))
                            Divider(
                                color = Color.Black.copy(alpha = 0.2f),
                                modifier = modifier.fillMaxWidth()
                            )
                        }
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