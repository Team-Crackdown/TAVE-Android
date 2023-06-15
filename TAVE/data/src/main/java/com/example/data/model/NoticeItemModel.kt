package com.example.data.model

data class NoticeItemModel(
    val notice_ID: Long,
    val notice_Content: String,
    val notice_Photos: List<String>
)
