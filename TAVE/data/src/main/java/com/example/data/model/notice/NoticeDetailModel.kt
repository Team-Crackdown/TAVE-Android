package com.example.data.model.notice

import java.util.Date

data class NoticeDetailModel(
    val id: Int,
    val title: String?,
    val content: String?,
    val noticeType: String,
    val images: List<String?>,
    val adminId: Int,
    val createdTime: Date?,
    val modifiedTime: Date?
)