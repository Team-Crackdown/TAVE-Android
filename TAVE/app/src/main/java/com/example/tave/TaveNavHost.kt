package com.example.tave

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tave.pages.HomePage
import com.example.tave.pages.InitPasswordPage
import com.example.tave.pages.LoginPage
import com.example.tave.pages.NoticeDetailPage
import com.example.tave.pages.NoticePage
import com.example.tave.pages.OTPCodePage
import com.example.tave.pages.ProfilePage
import com.example.tave.pages.SendSMSCodePage

@Composable
fun TaveNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = LogInPage.route
    ) {
        composable(route = LogInPage.route) {
            LoginPage(modifier = Modifier, navController = navController)
        }
        composable(route = SendSMSCodePage.route) {
            SendSMSCodePage(modifier = Modifier, navController = navController)
        }
        composable(route = InputOTPCodePage.route) {
            OTPCodePage(modifier = Modifier, navController = navController)
        }
        composable(route = InitPasswordPage.route) {
            InitPasswordPage(modifier = Modifier, navController = navController)
        }
        composable(route = HomePage.route) {
            HomePage(modifier = Modifier, navController =  navController)
        }
        composable(route = ProfilePage.route) {
            ProfilePage(modifier = Modifier)
        }
        composable(route = NoticePage.route) {
            NoticePage(
                modifier = Modifier,
                onMainItemClick = { noticeID -> navController.navigateToNoticeDetail(noticeID) },
                onSubItemClick = { noticeID -> navController.navigateToNoticeDetail(noticeID) }
            )
        }
        composable(
            route = NoticeDetailPage.routeWithNoticeID,
            arguments = NoticeDetailPage.arguments
        ) { navBackStackEntry ->
            val noticeID = navBackStackEntry.arguments?.getInt(NoticeDetailPage.noticeID)
            NoticeDetailPage(modifier = Modifier, noticeID = noticeID)
        }
    }
}

private fun NavHostController.navigateToNoticeDetail(noticeID: Int) {
    this.navigate("${NoticeDetailPage.route}/$noticeID")

}