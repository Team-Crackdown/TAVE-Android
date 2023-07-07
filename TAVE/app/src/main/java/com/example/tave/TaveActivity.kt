package com.example.tave

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tave.service.SSEService
import com.example.tave.ui.theme.TAVETheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaveActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            TAVETheme {
                val navController: NavHostController = rememberNavController()

                TaveNavHost(navController = navController)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, SSEService::class.java))
    }
}