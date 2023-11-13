package com.example.tave

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tave.common.Constants
import com.example.tave.pages.HomePage
import com.example.tave.pages.InitPasswordPage
import com.example.tave.pages.LoginPage
import com.example.tave.pages.NoticeDetailPage
import com.example.tave.pages.NoticePage
import com.example.tave.pages.OTPCodePage
import com.example.tave.pages.ProfilePage
import com.example.tave.pages.SendSMSCodePage
import com.example.tave.viewmodel.InitPWDViewModel
import com.example.tave.viewmodel.InputOTPViewModel
import com.example.tave.viewmodel.LogInViewModel
import com.example.tave.viewmodel.SendSMSViewModel

@Composable
fun TaveNavHost(navController: NavHostController) {
    val startPoint: String = if (
        TaveApplication.authPrefs
            .getTokenValue(Constants.ACCESS_TOKEN_TITLE, "")
            .isNotEmpty()
        ) {
        LogInPage.route
    } else {
        HomePage.route
    }

    NavHost(
        navController = navController,
        startDestination = startPoint
    ) {
        composable(route = LogInPage.route) {
            val logInViewModel = hiltViewModel<LogInViewModel>()
            val loginState by logInViewModel.logInState.collectAsState()

            LoginPage(
                navController = navController,
                loginState = loginState,
                onLogIn = logInViewModel::userLogInAccount
            )
        }
        composable(route = SendSMSCodePage.route) {
            val sendSMSViewModel = hiltViewModel<SendSMSViewModel>()
            val sendSMSCodeState by sendSMSViewModel.isSendSMSCode.collectAsState()

            SendSMSCodePage(
                sendSMSCodeState = sendSMSCodeState,
                sendSMSCode = sendSMSViewModel::sendSMSCode,
                onNavigateOTP = { phoneNumber -> navController.navigateToOTPPage(phoneNumber) }
            )
        }
        composable(
            route = InputOTPCodePage.routeWithPhoneNumber,
            arguments = InputOTPCodePage.argument
        ) { navBackStackEntry ->
            val phoneNumber = navBackStackEntry.arguments?.getString(InputOTPCodePage.phone_Number)
            val inputOTPViewModel = hiltViewModel<InputOTPViewModel>()
            val isOTPChecked by inputOTPViewModel.isOTPCodeChecked.collectAsState()

            OTPCodePage(
                phoneNumber = phoneNumber,
                navController = navController,
                isOTPChecked = isOTPChecked,
                checkOTPCode = inputOTPViewModel::checkOTPCode
            )
        }
        composable(route = InitPasswordPage.route) {
            val initPWDViewMode = hiltViewModel<InitPWDViewModel>()
            val isPWDChanged by initPWDViewMode.isPasswordChanged.collectAsState()

            InitPasswordPage(
                navController = navController,
                isPWDChanged = isPWDChanged,
                validatePWD = initPWDViewMode::validatePWD
            )
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

private fun NavHostController.navigateToOTPPage(phoneNumber: String) {
    this.navigate("${InputOTPCodePage.route}/$phoneNumber")
}

private fun NavHostController.navigateToNoticeDetail(noticeID: Int) {
    this.navigate("${NoticeDetailPage.route}/$noticeID")
}