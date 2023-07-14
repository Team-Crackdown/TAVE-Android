package com.example.data.model.schedule

import com.google.gson.annotations.SerializedName

data class ScheduleModel(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("date") val date: String,
    @SerializedName("place") val place: String,
    @SerializedName("adminId") val adminId: Int,
    @SerializedName("attendanceMemberId") val attendanceMemberId: List<Int?>,
    @SerializedName("createAt") val createdTime: String,
    @SerializedName("modifiedAt") val modifiedTime: String
)