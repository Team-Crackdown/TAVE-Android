package com.example.tave.items.noticeDetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.tave.items.glide.GlideImageView
import com.example.tave.ui.theme.Shape
import com.example.tave.R

@Composable
fun NoticeDetailLazyGridsPics(
    modifier: Modifier,
    imageList: List<String?>?
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        content = {
            imageList?.size?.let {
                items(it) { item ->
                    LazyGridItems(modifier = modifier, imageItem = imageList[item]!!)
                    Spacer(modifier = modifier.width(5.dp))
                }
            }
        }
    )
}

@Composable
fun LazyGridItems(
    modifier: Modifier,
    imageItem: String
) {
    Box(
        modifier = modifier
            .size(64.dp)
            .aspectRatio(1f)
            .clip(shape = Shape.large),
        contentAlignment = Alignment.Center,
        content = {
            GlideImageView(
                modifier = modifier.fillMaxSize(),
                imageUrl = imageItem,
                painterResource = R.drawable.tave_profile
            )
        }
    )
}