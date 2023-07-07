package com.example.tave.items.notice

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.domain.entity.notice.NoticeDetailEntity
import com.example.tave.R
import com.example.tave.items.glide.GlideImageView
import com.example.tave.ui.font.NotoSansKr
import com.example.tave.ui.theme.Shape

@Composable
fun MainNoticeCard(
    modifier: Modifier,
    navController: NavController,
    noticeMainCard: NoticeDetailEntity?
) {
    val admin: String = if (noticeMainCard != null) { "테이브 운영진" } else { "" }
    val noticeCardImage: () -> Unit = if (noticeMainCard?.images == null) {
        {  }
    } else {
        { noticeMainCard.images }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(277.dp)
            .clickable { navController.navigate("NoticeDetailPage") },
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        MainNoticeImage(
            imageUrl = noticeCardImage,
            modifier = Modifier
        )
        Spacer(modifier = modifier.size(5.dp))
        MainNoticeTitle(modifier = modifier, titleTxt = "${noticeMainCard?.title}",)
        Spacer(modifier = modifier.size(5.dp))
        MainNoticeWriter(modifier = modifier, writer = admin)
        Spacer(modifier = modifier.size(5.dp))
        MainNoticeTimeStamp(modifier = modifier, uploadTime = "${noticeMainCard?.createdTime}")
    }
}

@Composable
fun MainNoticeImage(
    imageUrl: () -> Unit,
    modifier: Modifier
) {
    GlideImageView(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(Shape.extraLarge),
        imageUrl = imageUrl,
        contentDescription = "tech letter",
        painterResource = R.drawable.tave_cover
    )
}

@Composable
fun MainNoticeTitle(
    modifier: Modifier,
    titleTxt: String
) {
    Text(
        text = titleTxt,
        modifier = modifier.padding(start = 10.dp, end = 10.dp),
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = NotoSansKr,
            fontWeight = FontWeight.Bold,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        )
    )
}

@Composable
fun MainNoticeWriter(
    modifier: Modifier,
    writer: String
) {
    Text(
        text = writer,
        modifier = modifier.padding(start = 10.dp, end = 10.dp),
        style = TextStyle(
            fontSize = 15.sp,
            fontFamily = NotoSansKr,
            fontWeight = FontWeight.Medium,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        )
    )
}

@Composable
fun MainNoticeTimeStamp(
    modifier: Modifier,
    uploadTime: String
) {
    Text(
        text = "${uploadTime}분 경 업로드",
        modifier = modifier.padding(start = 10.dp, end = 10.dp),
        style = TextStyle(
            color = Color.DarkGray,
            fontSize = 15.sp,
            fontFamily = NotoSansKr,
            fontWeight = FontWeight.Medium,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        )
    )
}