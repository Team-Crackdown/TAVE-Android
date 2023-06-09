package com.example.domain.entity.profile

data class UserProfileEntity(
    val userUID: Int,
    val userEmail: String?,
    val userName: String?,
    val userProfileImage: String?,
    val userRadix: Int?,
    val userPhoneNumber: String?,
    val userTech: String?,
    val userTeamID: Int?,
    val userUniv: String?,
    val userType: String?,
    val isCheckSMS: Boolean
)
