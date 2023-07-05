package com.example.tave

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tave.pages.*
import com.example.tave.ui.theme.TAVETheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startService(Intent(this, SSEService::class.java))

        setContent {
            TAVETheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {
                    composable("login") {
                        LoginPage(modifier = Modifier)
                    }
                    composable("home"){
                        HomePage(modifier = Modifier, navController =  navController)
                    }
                    composable("profile"){
                        ProfilePage(modifier = Modifier)
                    }
                    composable("updateProfile"){
                        UpdateProfilePage(modifier = Modifier, navController = navController)
                    }
                    composable("notice"){
                        NoticePage(modifier = Modifier, navController = navController)
                    }
                    composable("notice_detail"){
                        NoticeDetailPage(
                            modifier = Modifier,
                            mainTitle = "테런데이",
                            publisherText = "음하하",
                            upDateTime = "1시간 전",
                            itemCount = 3
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