package com.example.tave.pages

import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.entity.notice.NoticeDetailEntity
import com.example.tave.R
import com.example.tave.items.notice.MainNoticeCard
import com.example.tave.items.notice.NoticeSubItemsColumns
import com.example.tave.ui.font.NotoSansKr

@Composable
fun NoticePage(
    noticeMainCard: NoticeDetailEntity?,
    noticeNewsList: List<NoticeDetailEntity>?,
    onMainItemClick: (Int) -> Unit,
    onSubItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.padding(10.dp),
        content = { contentPadding ->
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
                        noticeMainCard = noticeMainCard
                    )
                    Divider(
                        color = Color.Black.copy(alpha = 0.2f),
                        modifier = modifier.fillMaxWidth()
                    )
                    NoticeSubItemsColumns(
                        modifier = modifier,
                        onItemClick = onSubItemClick,
                        noticeSubItems = noticeNewsList
                    )
                }
            )
        }
    )
}