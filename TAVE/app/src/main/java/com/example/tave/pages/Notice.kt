package com.example.tave.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
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
import com.example.tave.items.notice.NoticeSubItemsColumns
import com.example.tave.items.notice.NoticeTechLetterRow
import com.example.tave.ui.font.NotoSansKr
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
                    navController = navController,
                    noticeMainCard = noticeMainCard.value
                )
                Divider(
                    color = Color.Black.copy(alpha = 0.2f),
                    modifier = modifier.fillMaxWidth()
                )
                NoticeSubItemsColumns(
                    modifier = modifier,
                    navController = navController,
                    noticeSubItems = noticeSubItems.value
                )
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
                NoticeTechLetterRow(modifier = modifier)
            }
        )
    }
}

@Preview
@Composable
fun PreviewNoticePage() {
    NoticePage(modifier = Modifier, navController = rememberNavController())
}