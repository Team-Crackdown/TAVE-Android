package com.example.tave

interface TaveDestination { val route: String }

object LogInPage: TaveDestination { override val route: String = "LoginPage" }

object SendSMSCodePage: TaveDestination { override val route: String = "SendSMSCodePage" }

object InputOTPCodePage: TaveDestination { override val route: String = "InputOTPCodePage" }

object InitPasswordPage: TaveDestination { override val route: String = "InitPasswordPage" }

object HomePage: TaveDestination { override val route: String = "HomePage" }

object ProfilePage: TaveDestination { override val route: String = "ProfilePage" }

object NoticePage: TaveDestination { override val route: String = "NoticePage" }

object NoticeDetailPage: TaveDestination { override val route: String = "NoticeDetailPage" }