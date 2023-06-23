package com.example.tave.items.notice

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
fun NoticeSubItems(
    modifier: Modifier,
    subItemTitle: String,
    subItemWriter: String,
    subItemTimeStamp: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(66.dp)
            .padding(10.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier
                .size(30.dp)
                .clip(shape = Shape.large),
            painter = painterResource(R.drawable.tave_profile),
            contentScale = ContentScale.Fit,
            contentDescription = "Sub Items Image View"
        )
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            content = {
                NoticeSubItemTitle(titleTxt = subItemTitle)
                Row(
                    modifier = modifier.fillMaxWidth(),
                    content =  {
                        NoticeSubItemWriter(writer = subItemWriter)
                        NoticeSubItemTimeStamp(uploadTime = subItemTimeStamp)
                    }
                )
            }
        )
    }
}

@Composable
fun NoticeSubItemTitle(
    titleTxt: String
) {
    Text(
        text = titleTxt,
        modifier = Modifier.padding(start = 5.dp),
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = NotoSansKr,
            fontWeight = FontWeight.Bold,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        )
    )
}

@Composable
fun NoticeSubItemWriter(writer: String) {
    Text(
        text = writer,
        modifier = Modifier.padding(start = 5.dp),
        style = TextStyle(
            fontSize = 12.sp,
            fontFamily = NotoSansKr,
            fontWeight = FontWeight.Medium,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        )
    )
}

@Composable
fun NoticeSubItemTimeStamp(uploadTime: String) {
    Text(
        text = uploadTime,
        modifier = Modifier.padding(start = 10.dp),
        style = TextStyle(
            color = Color.DarkGray,
            fontSize = 12.sp,
            fontFamily = NotoSansKr,
            fontWeight = FontWeight.Medium,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        )
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewNoticeSubItems() {
    NoticeSubItems(
        modifier = Modifier,
        subItemTitle = "후반기 만남의 장 회원 소개 Part1",
        subItemWriter = "TAVE 운영진",
        subItemTimeStamp = "6시간 전"
    )
}