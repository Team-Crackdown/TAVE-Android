package com.example.tave.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class SSEService: Service() {
    override fun onBind(p0: Intent?): IBinder? {
        TODO("")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("Service", "서비스가 시작되었습니다.")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Service", "서비스가 종료되었습니다.")
    }
}