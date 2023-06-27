package com.example.tave.items.noticeDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tave.ui.theme.Shape
import com.skydoves.landscapist.glide.GlideImage

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
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clip(shape = Shape.large),
                    contentAlignment = Alignment.Center
                ){
                    GlideImage(
                        imageModel = imageUrl,
                        modifier = modifier,
                        loading = {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                content = {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(30.dp),
                                        strokeWidth = 4.dp
                                    )
                                }
                            )
                        },
                        success = {
                                imageState, painter ->
                            imageState.imageBitmap?.let {
                                Image(
                                    bitmap = it,
                                    contentDescription = "tech letter",
                                    modifier = Modifier
                                        .size(64.dp)
                                )
                            }
                        },
                        failure = {
                            Box(
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(shape = Shape.large),
                                content = {
                                    Text(text = "이미지 없음")
                                    Spacer(modifier = Modifier.width(5.dp))
                                }
                            )
                        }
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