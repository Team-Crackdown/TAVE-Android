package com.example.tave.di.module

import com.example.tave.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Request
import javax.inject.Singleton

/** SSE Connect Module (작성 중)
 *  - Server Sent Event 사용을 위해 만들어진 모듈입니다.
 */
@Module
@InstallIn(SingletonComponent::class)
object SSEConnectModule {
    @Provides
    @Singleton
    fun provideSSERequestHeader(): Request = Request.Builder()
        .url(BuildConfig.TAVE_API_URL)
        .header("Accept", "text/event-stream")
        .build()
}