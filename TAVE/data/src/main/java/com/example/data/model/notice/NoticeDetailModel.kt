package com.example.data.model.notice

import java.sql.Timestamp

data class NoticeDetailModel(
    val id: Int,
    val content: String,
    val images: List<String>,
    val adminId: Int,
    val createdTime: Timestamp,
    val modifiedTime: Timestamp
)