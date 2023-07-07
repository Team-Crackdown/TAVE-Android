package com.example.data.model.schedule

import com.google.gson.annotations.SerializedName

data class ScheduleModel(
    @SerializedName("title") val title: String,
    @SerializedName("date") val date: String
)