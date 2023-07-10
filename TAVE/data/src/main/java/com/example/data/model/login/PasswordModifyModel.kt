package com.example.data.model.login

import com.google.gson.annotations.SerializedName

data class PasswordModifyModel(
    @SerializedName("password") val password: String,
    @SerializedName("checkSms") val checkSMS: Boolean
)