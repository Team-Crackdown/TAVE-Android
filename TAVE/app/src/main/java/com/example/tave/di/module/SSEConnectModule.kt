package com.example.tave.di.module

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources
import javax.inject.Singleton

/** SSE Connect Module
 *  - Server Sent Event를 처리하는 모듈입니다.
 *  - fun provideSSERequestHeader(): Request
 *    : SSE와 관련된 Request 객체를 제공합니다.
 *  - fun provideEventSourceListener(): EventSourceListener
 *    : EventSourceListener 객체를 제공합니다.
 *  - fun provideEventSource(okHttpClient: OkHttpClient): EventSource.Factory
 *    : EventSource.Factory 객체를 제공합니다.
 */

@Module
@InstallIn(SingletonComponent::class)
object SSEConnectModule {
    @Provides
    @Singleton
    fun provideEventSourceListener(): EventSourceListener {
        val eventSourceListener = object : EventSourceListener() {
            override fun onEvent(
                eventSource: EventSource,
                id: String?,
                type: String?,
                data: String
            ) {
                super.onEvent(eventSource, id, type, data)
                Log.d("로그 SSE onEvent", data)
            }

            override fun onFailure(
                eventSource: EventSource,
                t: Throwable?,
                response: Response?
            ) {
                super.onFailure(eventSource, t, response)
                Log.d("로그 SSE onFailed", "${t?.message}")
            }
        }

        return eventSourceListener
    }

    @Provides
    @Singleton
    fun provideEventSource(okHttpClient: OkHttpClient): EventSource.Factory =
        EventSources.createFactory(okHttpClient)
}