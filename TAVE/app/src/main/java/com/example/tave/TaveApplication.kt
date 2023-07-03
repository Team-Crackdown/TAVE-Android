package com.example.tave

import android.app.Application
import com.example.tave.common.util.PreferenceUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TaveApplication: Application() {
    companion object {
        lateinit var authPrefs: PreferenceUtil
    }

    override fun onCreate() {
        super.onCreate()
        authPrefs = PreferenceUtil(applicationContext)
    }
}