package com.example.tave

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tave.pages.*
import com.example.tave.service.SSEService
import com.example.tave.ui.theme.TAVETheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startService(Intent(this, SSEService::class.java))

        setContent {
            val navController: NavHostController = rememberNavController()

            TAVETheme {
                NavHost(
                    navController = navController,
                    startDestination = "LoginPage"
                ) {
                    composable("LoginPage") {
                        LoginPage(modifier = Modifier, navController = navController)
                    }
                    composable("SendSMSCodePage") {
                        SendSMSCodePage(modifier = Modifier, navController = navController)
                    }
                    composable("InputOTPCodePage") {
                        OTPCodePage(modifier = Modifier, navController = navController)
                    }
                    composable("InitPasswordPage") {
                        InitPasswordPage(modifier = Modifier, navController = navController)
                    }
                    composable("HomePage"){
                        HomePage(modifier = Modifier, navController =  navController)
                    }
                    composable("ProfilePage"){
                        ProfilePage(modifier = Modifier)
                    }
                    composable("NoticePage"){
                        NoticePage(modifier = Modifier, navController = navController)
                    }
                    composable("NoticeDetailPage"){
                        NoticeDetailPage(
                            modifier = Modifier,
                            mainTitle = "테런데이",
                            publisherText = "음하하",
                            upDateTime = "1시간 전",
                            itemCount = 3,
                            navController = navController
                        )
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, SSEService::class.java))
    }
}