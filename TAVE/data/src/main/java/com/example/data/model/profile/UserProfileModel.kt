package com.example.data.model.profile

data class UserProfileModel(
    val id: Int,
    val email: String,
    val name: String,
    val profileImage: String?,
    val rad: Int,
    val phoneNumber: String,
    val techField: String,
    val teamID: String?,
    val university: String,
    val memberType: String
)
