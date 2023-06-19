package com.example.tave.di.module

import com.example.data.api.TaveAPIService
import com.example.tave.common.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/***
 * TAVE API Module
 *  - Retrofit, OkHttp3 Instance를 생성하고 Data Layer의 RepositoryImpl에 의존성을 주입합니다.
 *  - provideBaseURL() : Backend Server의 URL이 들어가야하나 현재 완성되지 않아 naver로 대체
 */
@Module
@InstallIn(SingletonComponent::class)
object TaveAPIModule {
    private fun provideBaseURL(): String = Constants.TEST_QR_URL

    @Provides
    @Singleton
    fun provideOKHttpClient(): OkHttpClient =
        OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .callTimeout(10, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofitClient(httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(provideBaseURL())
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit): TaveAPIService =
        retrofit.create(TaveAPIService::class.java)
}