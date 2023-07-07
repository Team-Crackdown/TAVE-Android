package com.example.tave.items.glide

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.tave.ui.theme.Shape
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun GlideImageView(
    modifier: Modifier,
    imageUrl: String,
    contentDescription: String,
    painterResource: Int,
) {
    GlideImage(
        imageModel = { imageUrl } ,
        modifier = modifier,
        loading = {
            ShimmerEffectItem(
                isLoading = true,
                contentLoading = {  },
                contentAfterLoading = { },
                modifier = modifier
            )
        },
        success = { imageState, _ ->
            imageState.imageBitmap?.let { Image(bitmap = it, contentDescription = contentDescription) }
        },
        failure = {
            Image(
                modifier = modifier
                    .fillMaxSize()
                    .clip(Shape.large),
                painter = painterResource(id = painterResource),
                contentScale = ContentScale.FillBounds,
                contentDescription = "Failure Image"
            )
        }
    )
}

@Composable
@Preview
fun PreviewNoticeDetailLazyGridsPics() {
    GlideImage(
        imageModel = { "https://tave-bucket.s3.ap-northeast-2.amazonaws.com/96cd7eb5-519f-4716-b0a8-a5e468af5279.jpg" }
    )
}