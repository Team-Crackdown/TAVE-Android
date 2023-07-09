package com.example.tave.di.module

import com.example.data.api.TaveAPIService
import com.example.data.repositoryImpl.TaveAPIRepositoryImpl
import com.example.domain.repository.TaveAPIRepository
import com.example.tave.common.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/***
 * TAVE API Module
 *  - Retrofit, OkHttp3 Instance를 생성하고 Data Layer의 RepositoryImpl에 의존성을 주입합니다.
 *  - provideBaseURL() : Backend Server의 URL 을 제공합니다
 *  - provideOKHttpClient(sslSocketFactory: SSLSocketFactory, trustManagerFactory: TrustManagerFactory)
 *    : OkHttpClient 객체를 Retrofit에 제공합니다.
 *      이 과정에서 SSL 인증서를 OkHttp3 클라이언트에 등록합니다.
 *  - provideRetrofitClient(taveAPIBaseURL: String, httpClient: OkHttpClient)
 *    : Retrofit 객체를 생성하여 서비스 합니다.
 *  - provideAPIService(retrofit: Retrofit): TaveAPIService
 *    : Retrofit 객체를 활용하여 TaveAPIService 를 등록합니다.
 *  - provideTaveApiRepoImpl(taveAPIService: TaveAPIService)
 *    : TaveApiRepoImpl에 TaveAPIService 의존성을 제공합니다.
 */
@Module
@InstallIn(SingletonComponent::class)
object TaveAPIModule {
    @Provides
    @Singleton
    fun provideBaseURL(): String = Constants.TAVE_URL

    @Provides
    @Singleton
    fun provideRetrofitClient(
        taveAPIBaseURL: String,
        httpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(taveAPIBaseURL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit): TaveAPIService =
        retrofit.create(TaveAPIService::class.java)

    @Provides
    @Singleton
    fun provideTaveApiRepoImpl(taveAPIService: TaveAPIService): TaveAPIRepository =
        TaveAPIRepositoryImpl(taveAPIService)
}