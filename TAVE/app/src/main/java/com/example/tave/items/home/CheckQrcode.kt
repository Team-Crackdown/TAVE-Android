package com.example.tave.items.home

import android.graphics.Bitmap
import androidx.compose.foundation.Image
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
import com.google.zxing.*
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter


@Composable
fun CheckQrcode(onDismiss: ()-> Unit){
    Dialog(onDismissRequest = onDismiss) {
        DialogContent()
    }
}

@Composable
fun DialogContent(){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        generateQRCode("https://www.naver.com")?.let {
                it -> Image(bitmap = it.asImageBitmap(), contentDescription = "qrcode")
        }
        Spacer(modifier = Modifier.height(10.dp))
        IconButton(onClick = { /*TODO*/ }, colors = IconButtonDefaults.filledIconButtonColors(Color.Gray)) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "refresh")
        }
    }
}

const val WHITE: Int = 0xFFFFFFFF.toInt()
const val BLACK: Int = 0x00000000

//데이터 들어가야할 곳
fun generateQRCode(contents: String?): Bitmap? {
    var bitmap: Bitmap? = null
    try {
        val qrCodeWriter = QRCodeWriter()
        bitmap = toBitmap(qrCodeWriter.encode(contents, BarcodeFormat.QR_CODE, 512, 512))
    } catch (e: WriterException) {
        e.printStackTrace()
    }
    return bitmap
}

//QR코드 색상과, 크기 지정하는 곳
private fun toBitmap(matrix: BitMatrix): Bitmap? {
    val height: Int = matrix.getHeight()
    val width: Int = matrix.getWidth()
    val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
    for (x in 0 until width) {
        for (y in 0 until height) {
            bmp.setPixel(x, y, if (matrix.get(x, y)) BLACK else WHITE)
        }
    }
    return bmp
}

@Composable
@Preview
fun preview(){
    CheckQrcode(onDismiss = {})
}
