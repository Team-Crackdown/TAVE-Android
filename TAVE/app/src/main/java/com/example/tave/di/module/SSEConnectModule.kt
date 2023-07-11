package com.example.tave.di.module

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.tave.R
import com.example.tave.common.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SSEConnectModule {
    @Provides
    @Singleton
    fun provideEventSourceListener(@ApplicationContext context: Context): EventSourceListener {
        val eventSourceListener = object : EventSourceListener() {
            override fun onEvent(
                eventSource: EventSource,
                id: String?,
                type: String?,
                data: String
            ) {
                super.onEvent(eventSource, id, type, data)
                if (data != Constants.SSE_ACK_FLAG) {
                    Handler(Looper.getMainLooper()).postDelayed(
                        {
                            Toast.makeText(
                                context,
                                context.getText(R.string.New_Notice_Message),
                                Toast.LENGTH_SHORT
                            ).show()
                        }, 0
                    )
                }
            }
        }

        return eventSourceListener
    }

    @Provides
    @Singleton
    fun provideEventSource(okHttpClient: OkHttpClient): EventSource.Factory =
        EventSources.createFactory(okHttpClient)


}