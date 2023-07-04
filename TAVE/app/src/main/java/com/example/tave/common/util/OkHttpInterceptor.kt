package com.example.tave.common.util

import com.example.tave.TaveApplication
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import kotlin.jvm.Throws

class OkHttpInterceptor: Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        val accessToken = TaveApplication.authPrefs.getTokenValue("accessToken", "")
        val withAuthHeaderRequest: Request = request().newBuilder()
            .addHeader("Authorization", accessToken)
            .build()

        proceed(withAuthHeaderRequest)
    }
}