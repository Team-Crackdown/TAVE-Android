package com.example.tave.items.glide

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.tave.ui.theme.Shape
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun GlideImageView(
    modifier: Modifier,
    imageUrl: () -> Any,
    contentDescription: String,
    painterResource: Int,
) {
    GlideImage(
        imageModel = imageUrl,
        modifier = modifier,
        loading = {
            ShimmerEffectItem(
                isLoading = true,
                contentLoading = {/*TODO*/},
                contentAfterLoading = {/*TODO*/},
                modifier = modifier
            )
        },
        success = { imageState, _ ->
            imageState.imageBitmap?.let {
                Image(
                    bitmap = it,
                    contentDescription = contentDescription,
                )
            }
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