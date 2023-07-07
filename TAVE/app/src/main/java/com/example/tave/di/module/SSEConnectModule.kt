package com.example.tave.di.module

import android.util.Log
import com.example.tave.common.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources
import javax.inject.Singleton

/** SSE Connect Module (작성 중)
 *  - Server Sent Event를 처리하는 모듈입니다.
 *  - fun provideSSERequestHeader(): Request
 *    : SSE와 관련된 Request 객체를 제공합니다.
 *  - fun provideEventSourceListener(): EventSourceListener
 *    : EventSourceListener 객체를 제공합니다.
 *  - fun provideEventSource(
 *        request: Request,
 *        okHttpClient: OkHttpClient,
 *        eventSourceListener: EventSourceListener
 *        ): EventSource
 *    : EventSource 객체를 제공합니다.
 */

@Module
@InstallIn(SingletonComponent::class)
object SSEConnectModule {
    @Provides
    @Singleton
    fun provideSSERequestHeader(): Request = Request.Builder()
        .url(Constants.TAVE_SSE_URL)
        .addHeader("accept", "text/event-stream")
        .build()

    @Provides
    @Singleton
    fun provideEventSourceListener(): EventSourceListener {
        val eventSourceListener = object : EventSourceListener() {
            override fun onOpen(
                eventSource: EventSource,
                response: Response
            ) {
                super.onOpen(eventSource, response)
                Log.d("로그 SSE", "isOpen")
            }

            override fun onEvent(
                eventSource: EventSource,
                id: String?,
                type: String?,
                data: String
            ) {
                super.onEvent(eventSource, id, type, data)
                Log.d("로그 SSE", "onEvent")
                Log.d("로그 SSE", data)
            }

            override fun onFailure(
                eventSource: EventSource,
                t: Throwable?,
                response: Response?
            ) {
                super.onFailure(eventSource, t, response)
                Log.d("로그 SSE", "onFailed")
                Log.d("로그 SSE", "${t?.message}")
            }

            override fun onClosed(eventSource: EventSource) {
                super.onClosed(eventSource)
                Log.d("로그 SSE", "onClosed")
            }
        }

        return eventSourceListener
    }

    @Provides
    @Singleton
    fun provideEventSource(okHttpClient: OkHttpClient): EventSource.Factory =
        EventSources.createFactory(okHttpClient)
}