package com.example.tave.di.module

import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Zxing (Generate QR Code) Module
 *  - provideBarcodeFormat() : QR Code 바코드 형식을 제공한다.
 *  - provideQRCodeWriter() : QRCodeWriter 객체 제공
 *  - provideQRCodeInstance() : content를 QRCodeWriter로 인코딩한 결과(BitMatrix)를 반환한다.
 */
@Module
@InstallIn(SingletonComponent::class)
object ZxingModule {
    @Provides
    @Singleton
    fun provideBarcodeFormat(): BarcodeFormat = BarcodeFormat.QR_CODE

    @Provides
    @Singleton
    fun provideQRCodeWriter(): QRCodeWriter = QRCodeWriter()
}