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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.entity.notice.NoticeDetailEntity
import com.example.tave.common.Constants
import com.example.tave.items.glide.ShimmerEffectItem
import com.example.tave.items.noticeDetail.NoticeDetailCard
import com.example.tave.items.noticeDetail.NoticeDetailLazyGridsPics
import com.example.tave.items.noticeDetail.NoticeDetailPublisherBar
import com.example.tave.items.noticeDetail.NoticeDetailTopBar
import com.example.tave.ui.font.NotoSansKr
import kotlinx.coroutines.delay

@Composable
fun NoticeDetailPage(
    noticeID: Int?,
    noticeDetailInfo: NoticeDetailEntity?,
    getNoticeDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var isLoading by remember { mutableStateOf(true) }
    LaunchedEffect(
        key1 = true,
        block = {
            delay(1000)
            isLoading = false
        }
    )
    LaunchedEffect(key1 = Unit, block = { getNoticeDetail(noticeID!!) })
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            NoticeDetailTopBar(
                modifier = modifier,
                publisher = if (noticeDetailInfo != null) { Constants.TAVE_ADMIN } else { "" })
        }
    ) { contentPadding ->
        Column(
            modifier = modifier
                .padding(contentPadding)
                .padding(start = 10.dp, end = 10.dp),
            content = {
                NoticeDetailCard(
                    modifier = modifier,
                    cardTitle = "${noticeDetailInfo?.title}",
                    noticeImage = noticeDetailInfo?.images,
                    isLoading = isLoading,
                )
                NoticeDetailPublisherBar(
                    modifier = modifier,
                    publisherTxt = if (noticeDetailInfo != null) { Constants.TAVE_ADMIN } else { "" },
                    upDateTime = "${noticeDetailInfo?.createdTime}",
                    isLoading = isLoading
                )
                ShimmerEffectItem(
                    isLoading = isLoading,
                    contentLoading = { Box(modifier = modifier.fillMaxWidth()) },
                    contentAfterLoading = {
                        Text(
                            text = "${noticeDetailInfo?.content}",
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
                NoticeDetailLazyGridsPics(
                    modifier = modifier,
                    imageList = noticeDetailInfo?.images
                )
            }
        )
    }
}