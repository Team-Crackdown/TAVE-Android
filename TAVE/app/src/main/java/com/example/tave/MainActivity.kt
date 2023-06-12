package com.example.tave

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tave.pages.HomePage
import com.example.tave.pages.LoginPage
import com.example.tave.pages.ProfilePage
import com.example.tave.pages.UpdateProfilePage
import com.example.tave.ui.theme.TAVETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TAVETheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("login") {
                        LoginPage(applicationContext, navController)
                    }
                    composable("home"){
                        HomePage("김테비", "11기", "OB", navController)
                    }
                    composable("profile"){
                        ProfilePage(applicationContext, navController)
                    }
                    composable("updateProfile"){
                        UpdateProfilePage(applicationContext, navController)
                    }
                }
            }
        }
    }

}