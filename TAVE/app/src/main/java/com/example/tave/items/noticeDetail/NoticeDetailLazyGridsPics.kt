package com.example.tave.items.noticeDetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tave.items.glide.GlideImageView
import com.example.tave.ui.theme.Shape
import com.example.tave.R

@Composable
fun NoticeDetailLazyGridsPics(
    itemCount: Int,
    imageUrl: () -> Unit,
    modifier: Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        content = {
            items(itemCount) {
                Box(
                    modifier = modifier
                        .aspectRatio(1f)
                        .clip(shape = Shape.large),
                    contentAlignment = Alignment.Center
                ){
                    GlideImageView(
                        modifier = modifier.size(64.dp),
                        imageUrl = imageUrl,
                        contentDescription = "tech letter",
                        painterResource = R.drawable.tave_profile
                    )
                }
            }
        }
    )
}

@Composable
@Preview
fun PreviewNoticeDetailLazyGridsPics() {
    NoticeDetailLazyGridsPics(itemCount = 12, imageUrl = {}, modifier = Modifier)
}