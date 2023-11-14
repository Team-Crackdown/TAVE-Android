package com.example.tave

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.example.tave.viewmodel.HomeViewModel
import com.example.tave.viewmodel.InitPWDViewModel
import com.example.tave.viewmodel.InputOTPViewModel
import com.example.tave.viewmodel.LogInViewModel
import com.example.tave.viewmodel.NoticeDetailViewModel
import com.example.tave.viewmodel.NoticeViewModel
import com.example.tave.viewmodel.ProfileViewModel
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
            val homeViewModel = hiltViewModel<HomeViewModel>()
            val userProfile by homeViewModel.userProfile.observeAsState()
            val personalScore by homeViewModel.personalScore.observeAsState()
            val teamScore by homeViewModel.teamScore.observeAsState()
            val scheduleTitle by homeViewModel.scheduleTitle.observeAsState()
            val scheduleRemainDay by homeViewModel.scheduleRemainDay.observeAsState()

            HomePage(
                navController =  navController,
                userProfile = userProfile,
                personalScore = personalScore,
                teamScore = teamScore,
                scheduleTitle = scheduleTitle,
                scheduleRemainDay = scheduleRemainDay,
                getPersonalScore = homeViewModel::getPersonalScore,
                getTeamScore = homeViewModel::getTeamScore
            )
        }
        composable(route = ProfilePage.route) {
            val profileViewModel = hiltViewModel<ProfileViewModel>()
            val userProfile by profileViewModel.userProfile.observeAsState()

            ProfilePage(userProfile = userProfile)
        }
        composable(route = NoticePage.route) {
            val noticeViewModel = hiltViewModel<NoticeViewModel>()
            val noticeMainCard by noticeViewModel.noticeMainData.observeAsState()
            val noticeNewsList by noticeViewModel.noticeSubDate.observeAsState()

            NoticePage(
                noticeMainCard = noticeMainCard,
                noticeNewsList = noticeNewsList,
                onMainItemClick = { noticeID -> navController.navigateToNoticeDetail(noticeID) },
                onSubItemClick = { noticeID -> navController.navigateToNoticeDetail(noticeID) }
            )
        }
        composable(
            route = NoticeDetailPage.routeWithNoticeID,
            arguments = NoticeDetailPage.arguments
        ) { navBackStackEntry ->
            val noticeID = navBackStackEntry.arguments?.getInt(NoticeDetailPage.noticeID)
            val noticeDetailViewModel = hiltViewModel<NoticeDetailViewModel>()
            val noticeDetailInfo by noticeDetailViewModel.noticeData.observeAsState()

            NoticeDetailPage(
                noticeID = noticeID,
                noticeDetailInfo = noticeDetailInfo,
                getNoticeDetail = noticeDetailViewModel::getNoticeDetail
            )
        }
    }
}

private fun NavHostController.navigateToOTPPage(phoneNumber: String) {
    this.navigate("${InputOTPCodePage.route}/$phoneNumber")
}

private fun NavHostController.navigateToNoticeDetail(noticeID: Int) {
    this.navigate("${NoticeDetailPage.route}/$noticeID")
}