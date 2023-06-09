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
import com.example.tave.R
import com.example.tave.items.glide.GlideImageView
import com.example.tave.ui.font.NotoSansKr
import com.example.tave.ui.theme.Shape

@Composable
fun NoticeSubItems(
    modifier: Modifier,
    subItemTitle: String,
    subItemWriter: String,
    subItemTimeStamp: String,
    imageUrl: List<String?>,
    onClick: (Int) -> Unit,
    index: Int
) {
    val imageItems: String = if (imageUrl.isEmpty()) { "" } else { "${imageUrl.first()}" }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(66.dp)
            .padding(10.dp)
            .clickable { onClick.invoke(index) },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NoticeSubItemImage(
            imageUrl = imageItems,
            modifier = modifier
        )
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            content = {
                NoticeSubItemTitle(modifier = Modifier, titleTxt = subItemTitle)
                Row(
                    modifier = modifier.fillMaxWidth(),
                    content = {
                        NoticeSubItemWriter(modifier = Modifier, writer = subItemWriter)
                        NoticeSubItemTimeStamp(modifier = Modifier, uploadTime = subItemTimeStamp)
                    }
                )
            }
        )
    }
}

@Composable
fun NoticeSubItemImage(
    modifier: Modifier,
    imageUrl: String
) {
    GlideImageView(
        modifier = modifier.size(30.dp).clip(Shape.large),
        imageUrl = imageUrl,
        painterResource = R.drawable.tave_profile
    )
}

@Composable
fun NoticeSubItemTitle(
    modifier: Modifier,
    titleTxt: String
) {
    Text(
        text = titleTxt,
        modifier = modifier.padding(start = 5.dp),
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = NotoSansKr,
            fontWeight = FontWeight.Bold,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        )
    )
}

@Composable
fun NoticeSubItemWriter(modifier: Modifier, writer: String) {
    Text(
        text = writer,
        modifier = modifier.padding(start = 5.dp),
        style = TextStyle(
            fontSize = 12.sp,
            fontFamily = NotoSansKr,
            fontWeight = FontWeight.Medium,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        )
    )
}

@Composable
fun NoticeSubItemTimeStamp(modifier: Modifier, uploadTime: String) {
    Text(
        text = uploadTime,
        modifier = modifier.padding(start = 10.dp),
        style = TextStyle(
            color = Color.DarkGray,
            fontSize = 12.sp,
            fontFamily = NotoSansKr,
            fontWeight = FontWeight.Medium,
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        )
    )
}