package com.example.tave.items.home

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tave.common.Constants
import com.example.tave.common.Constants.BLACK
import com.example.tave.common.Constants.WHITE
import com.example.tave.ui.theme.Shape
import com.example.tave.viewmodel.HomeViewModel
import com.google.zxing.*
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter

@Composable
fun CheckQrcode(
    onDismiss: ()-> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val userUID = homeViewModel.userProfile.observeAsState()

    Dialog(
        onDismissRequest = onDismiss,
        content = {
            QRDialogView(
                modifier = Modifier,
                baseURL = userUID.value?.userUID.toString(),
                onDismiss = onDismiss
            )
        }
    )
}

@Composable
fun QRDialogView(
    modifier: Modifier,
    baseURL: String,
    onDismiss: ()-> Unit
) {
    Surface(
        modifier = modifier
            .width(312.dp)
            .height(310.dp)
            .clip(Shape.extraLarge)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            generateQRCode(baseURL)?.let { QRCode ->
                Image(
                    modifier = modifier.size(200.dp).padding(bottom = 10.dp),
                    bitmap = QRCode.asImageBitmap(),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = ""
                )
            }
            IconButton(
                onClick = onDismiss,
                colors = IconButtonDefaults.filledIconButtonColors(MaterialTheme.colorScheme.tertiary),
                content = { Icon(imageVector = Icons.Default.Close, contentDescription = "") }
            )
        }
    }
}


/***
 * generateQRCode
 *  - QR Code의 생성을 담당하는 함수
 */
private fun generateQRCode(content: String?): Bitmap? {
    var bitmap: Bitmap? = null
    try {
        bitmap = qrCodeToBitmap(
            QRCodeWriter().encode(
                content,
                BarcodeFormat.QR_CODE,
                Constants.QR_WIDTH,
                Constants.QR_HEIGHT
            )
        )
    } catch (e: WriterException) {
        e.printStackTrace()
    }
    return bitmap
}

/**
 * qrCodeToBitMap
 *  - QR Code의 크기와 색상을 지정하는 함수
 *  - QR Code의 형식인 BitMatrix를 Bitmap으로 변경해주는 함수
 */
private fun qrCodeToBitmap(matrix: BitMatrix): Bitmap? {
    val bmp = Bitmap.createBitmap(matrix.width, matrix.height, Bitmap.Config.RGB_565)
    for (x in 0 until matrix.width) {
        for (y in 0 until matrix.height) {
            bmp.setPixel(x, y, if (matrix.get(x, y)) BLACK else WHITE)
        }
    }
    return bmp
}