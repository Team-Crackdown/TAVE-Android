package com.example.tave

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface TaveDestination { val route: String }

object LogInPage: TaveDestination { override val route: String = "LoginPage" }

object SendSMSCodePage: TaveDestination { override val route: String = "SendSMSCodePage" }

object InputOTPCodePage: TaveDestination {
    override val route: String = "InputOTPCodePage"
    const val phone_Number = "phone_number"
    val routeWithPhoneNumber: String = "$route/{$phone_Number}"
    val argument: List<NamedNavArgument> = listOf(navArgument(phone_Number) { type = NavType.StringType })
}

object InitPasswordPage: TaveDestination { override val route: String = "InitPasswordPage" }

object HomePage: TaveDestination { override val route: String = "HomePage" }

object ProfilePage: TaveDestination { override val route: String = "ProfilePage" }

object NoticePage: TaveDestination { override val route: String = "NoticePage" }

object NoticeDetailPage: TaveDestination {
    override val route: String = "NoticeDetailPage"
    const val noticeID = "notice_id"
    val routeWithNoticeID: String = "$route/{$noticeID}"
    val arguments: List<NamedNavArgument> = listOf(navArgument(noticeID) { type = NavType.IntType })
}