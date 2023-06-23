package com.example.tave.items.noticeDetail

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
fun NoticeDetailPublisherBar(
    modifier: Modifier,
    publisherTxt: String,
    upDateTime: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier.size(30.dp).clip(shape = Shape.large),
            painter = painterResource(id = R.drawable.tave_profile),
            contentScale = ContentScale.Fit,
            contentDescription = "Publisher Profile Image"
        )
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            content = {
                PublisherTxt(modifier = modifier, publisherTxt = publisherTxt)
                PublishDateTime(modifier = modifier, upDateTime = upDateTime)
            }
        )
    }
}

@Composable
fun PublisherTxt(
    modifier: Modifier,
    publisherTxt: String
) {
    Text(
        text = publisherTxt,
        modifier = modifier.padding(start = 10.dp),
        style = TextStyle(
            fontSize = 12.sp,
            fontFamily = NotoSansKr,
            fontWeight = FontWeight.Medium,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        )
    )
}

@Composable
fun PublishDateTime(
    modifier: Modifier,
    upDateTime: String
) {
    Text(
        text = upDateTime,
        modifier = modifier.padding(start = 10.dp),
        color = Color.DarkGray,
        style = TextStyle(
            fontSize = 11.sp,
            fontFamily = NotoSansKr,
            fontWeight = FontWeight.Medium,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        )
    )
}

@Composable
@Preview(group = "items", showBackground = true)
fun PreviewPublishDateTime() {
    PublishDateTime(modifier = Modifier, upDateTime = "6시간 전")
}

@Composable
@Preview(group = "items", showBackground = true)
fun PreviewPublisherTxt() {
    PublisherTxt(modifier = Modifier, publisherTxt = "TAVE 운영진")
}

@Composable
@Preview(showBackground = true)
fun PreviewNoticeDetailPublisherBar() {
    NoticeDetailPublisherBar(
        modifier = Modifier,
        publisherTxt = "TAVE 운영진",
        upDateTime = "6시간 전"
    )
}