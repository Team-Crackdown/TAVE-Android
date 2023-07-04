package com.example.domain.entity.notice

import java.sql.Timestamp

data class NoticeDetailEntity(
    val id: Int,
    val content: String,
    val images: List<String>,
    val adminId: Int,
    val createdTime: Timestamp,
    val modifiedTime: Timestamp
)