package com.example.domain.entity.notice


data class NoticeDetailEntity(
    val id: Int,
    var title: String?,
    val content: String?,
    val noticeType: String,
    val images: List<String?>,
    val adminId: Int,
    var createdTime: String,
    var modifiedTime: String
)