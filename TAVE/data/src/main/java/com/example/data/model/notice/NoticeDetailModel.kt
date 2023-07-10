package com.example.data.model.notice

import com.google.gson.annotations.SerializedName

data class NoticeDetailModel(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("content") val content: String?,
    @SerializedName("noticeType") val noticeType: String,
    @SerializedName("images") val images: List<String?>,
    @SerializedName("adminId") val adminId: Int,
    @SerializedName("createAt") val createdTime: String,
    @SerializedName("modifiedAt") val modifiedTime: String
)