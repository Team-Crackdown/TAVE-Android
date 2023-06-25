package com.example.tave.items.noticeDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tave.R
import com.example.tave.ui.theme.Shape

@Composable
fun NoticeDetailLazyGridsPics(
    itemCount: Int,
    painterResourceId: Int
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        content = {
            items(itemCount) {
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(5.dp)),
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(shape = Shape.large),
                        painter = painterResource(id = painterResourceId),
                        contentScale = ContentScale.Fit,
                        contentDescription = "Detail Pictures"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
    )
}

@Composable
@Preview
fun PreviewNoticeDetailLazyGridsPics() {
    NoticeDetailLazyGridsPics(itemCount = 12, painterResourceId = R.drawable.tech_letter)
}