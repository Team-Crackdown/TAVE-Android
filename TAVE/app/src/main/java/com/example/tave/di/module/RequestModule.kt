package com.example.tave.di.module

import com.example.tave.common.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Request
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RequestModule {
    @Provides
    @Singleton
    fun provideSSERequestInstance(): Request.Builder = Request
        .Builder()
        .url(Constants.TAVE_SSE_URL)
        .addHeader("accept", "text/event-stream")
}