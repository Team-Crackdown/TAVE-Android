package com.example.data.model.login

import com.google.gson.annotations.SerializedName

data class LogInBodyModel(
    @SerializedName("username")
    val userName: String,
    @SerializedName("password")
    val password: String
)