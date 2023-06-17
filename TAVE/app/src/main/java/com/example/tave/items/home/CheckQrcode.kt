package com.example.tave.items.home

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.tave.common.Constants
import com.example.tave.common.Constants.BLACK
import com.example.tave.common.Constants.WHITE
import com.google.zxing.*
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter

@Composable
fun CheckQrcode(onDismiss: ()-> Unit){
    Dialog(
        onDismissRequest = onDismiss,
        content = { DialogContents() }
    )
}

@Composable
fun DialogContents() {
    Box(
        modifier = Modifier.width(312.dp).height(310.dp).background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            generateQRCode(Constants.TEST_QR_URL)?.apply {
                Image(
                    modifier = Modifier.width(200.dp).height(200.dp),
                    bitmap = this.asImageBitmap(),
                    contentDescription = "QR Code"
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            IconButton(
                onClick = { /*TODO*/ },
                colors = IconButtonDefaults.filledIconButtonColors(Color.Gray),
                content = {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "refresh",
                        tint = Color.White
                    )
                }
            )
        }
    }
}


/***
 * generateQRCode
 *  - QR Code의 생성을 담당하는 함수
 */
fun generateQRCode(contents: String?): Bitmap? {
    var bitmap: Bitmap? = null
    try {
        bitmap = qrCodeToBitmap(
            QRCodeWriter().encode(
                contents,
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