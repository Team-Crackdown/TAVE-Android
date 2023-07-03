package com.example.tave

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
        setContent {
            TAVETheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("login") {
                        LoginPage(modifier = Modifier)
                    }
                    composable("home"){
                        HomePage(
                            modifier = Modifier,
                            name = stringResource(id = R.string.name_tavy),
                            radix = stringResource(id = R.string.radix_11),
                            generation = stringResource(id = R.string.generation),
                            navController =  navController
                        )
                    }
                    composable("profile"){
                        ProfilePage(modifier = Modifier)
                    }
                    composable("updateProfile"){
                        UpdateProfilePage(modifier = Modifier, navController = navController)
                    }
                }
            }
        }
    }

}