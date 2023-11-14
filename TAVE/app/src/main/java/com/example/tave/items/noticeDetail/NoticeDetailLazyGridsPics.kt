package com.example.tave.items.noticeDetail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tave.items.glide.GlideImageView
import com.example.tave.ui.theme.Shape
import com.example.tave.R
import com.example.tave.common.Constants
import com.example.tave.items.glide.ShimmerEffectItem
import com.example.tave.viewmodel.NoticeDetailViewModel
import com.skydoves.landscapist.glide.GlideImage

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
                    LazyGridItems(
                        modifier = modifier,
                        imageItem = imageList[item]!!
                    )
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
    val showGal = remember { mutableStateOf(false) }
    if (showGal.value) { ImageDialog(onDismiss = { showGal.value = false }) }

    Box(
        modifier = modifier
            .size(64.dp)
            .aspectRatio(1f)
            .clip(shape = Shape.large)
            .clickable { showGal.value = true },
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


@Composable
fun ImageDialog(
    onDismiss: () -> Unit,
    noticeDetailViewModel: NoticeDetailViewModel = hiltViewModel()
) {
    val noticeImage = noticeDetailViewModel.noticeData.observeAsState()

    Dialog(
        onDismissRequest = onDismiss,
        content = { HorizontalImagePager(imageList = noticeImage.value?.images) }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalImagePager(
    imageList: List<String?>?
) {
    val imageCount = if (imageList?.isNotEmpty() == true) { imageList.size } else { 0 }
    val pagerState = rememberPagerState(initialPage = imageCount)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        HorizontalPager(
            pageCount = imageCount,
            state = pagerState,
            //key = { imageList?.get(it) ?: 0 }
        ) {
            GlideImage(
                imageModel = { "${imageList?.get(it)}" } ,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                loading = {
                    ShimmerEffectItem(
                        isLoading = true,
                        contentLoading = {  },
                        contentAfterLoading = { },
                        modifier = Modifier
                    )
                },
                success = { imageState, _ ->
                    imageState.imageBitmap?.let { image ->
                        Image(
                            bitmap = image,
                            contentScale = ContentScale.Fit,
                            contentDescription = Constants.IMAGE_LOAD_SUCCESS_CONTENT_DESC
                        )
                    }
                },
                failure = {
                    Image(
                        modifier = Modifier,
                        painter = painterResource(id = R.drawable.tave_profile),
                        contentScale = ContentScale.Fit,
                        contentDescription = Constants.IMAGE_LOAD_FAILED_CONTENT_DESC
                    )
                }
            )
        }
    }
}