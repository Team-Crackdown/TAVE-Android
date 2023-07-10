package com.example.tave.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
object OKHttpModule {
    @Provides
    @Singleton
    fun provideOKHttpClient(
        sslSocketFactory: SSLSocketFactory,
        trustManagerFactory: TrustManagerFactory,
    ): OkHttpClient = OkHttpClient()
        .newBuilder()
        .readTimeout(1, TimeUnit.HOURS)
        .sslSocketFactory(
            sslSocketFactory,
            trustManagerFactory.trustManagers[0] as X509TrustManager
        )
        .build()
}