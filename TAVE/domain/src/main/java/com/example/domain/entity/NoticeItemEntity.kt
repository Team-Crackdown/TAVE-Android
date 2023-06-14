package com.example.domain.entity

data class NoticeItemEntity(
    val notice_ID: Long,
    val notice_Content: String,
    val notice_Photos: List<String>
)
