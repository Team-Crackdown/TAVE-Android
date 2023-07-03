package com.example.tave.common.util

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("accessToken", Context.MODE_PRIVATE)

    fun getTokenValue(
        tokenKey: String,
        tokenValue: String
    ): String = prefs.getString(tokenKey, tokenValue).toString()

    fun setTokenValue(
        tokenKey: String,
        tokenValue: String
    ) {
        prefs.edit().putString(tokenKey, tokenValue).apply()
    }
}