package com.example.domain.entity.notice

import java.util.Date

data class NoticeDetailEntity(
    val id: Int,
    val title: String?,
    val content: String?,
    val noticeType: String,
    val images: List<String?>,
    val adminId: Int,
    val createdTime: Date?,
    val modifiedTime: Date?
)