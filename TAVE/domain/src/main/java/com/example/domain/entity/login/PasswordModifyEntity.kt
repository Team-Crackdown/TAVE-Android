package com.example.domain.entity.login

data class PasswordModifyEntity(
    val password: String,
    val checkSMS: Boolean
)