package com.example.tave.di.module

import com.example.data.api.TaveAPIService
import com.example.data.repositoryImpl.TaveAPIRepositoryImpl
import com.example.domain.repository.TaveAPIRepository
import com.example.tave.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

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
    fun provideBaseURL(): String = BuildConfig.TAVE_API_URL

    @Provides
    @Singleton
    fun provideOKHttpClient(
        sslSocketFactory: SSLSocketFactory,
        trustManagerFactory: TrustManagerFactory
    ): OkHttpClient = OkHttpClient()
        .newBuilder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .callTimeout(10, TimeUnit.SECONDS)
        .sslSocketFactory(sslSocketFactory, trustManagerFactory.trustManagers[0] as X509TrustManager)
        .build()

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