package com.example.data.model.profile

import com.google.gson.annotations.SerializedName

data class UserProfileModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val userName: String?,
    @SerializedName("email") val userEmail: String?,
    @SerializedName("profileImage") val profileImage: String?,
    @SerializedName("rad") val userRadix: Int?,
    @SerializedName("phoneNumber") val phoneNumber: String?,
    @SerializedName("techField") val userTech: String?,
    @SerializedName("teamId") val teamId: Int?,
    @SerializedName("university") val userUniversity: String?,
    @SerializedName("memberType") val userType: String?,
    @SerializedName("checkSms") val checkSMS: Boolean
)
