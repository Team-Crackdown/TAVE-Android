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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tave.R
import com.example.tave.items.notice.MainNoticeCard
import com.example.tave.items.notice.NoticeSubItemsColumns
import com.example.tave.ui.font.NotoSansKr
import com.example.tave.viewmodel.NoticeViewModel

@Composable
fun NoticePage(
    modifier: Modifier,
    onMainItemClick: (Int) -> Unit,
    onSubItemClick: (Int) -> Unit,
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
                    onItemClick = onMainItemClick,
                    noticeMainCard = noticeMainCard.value
                )
                Divider(
                    color = Color.Black.copy(alpha = 0.2f),
                    modifier = modifier.fillMaxWidth()
                )
                NoticeSubItemsColumns(
                    modifier = modifier,
                    onItemClick = onSubItemClick,
                    noticeSubItems = noticeSubItems.value
                )
            }
        )
    }
}