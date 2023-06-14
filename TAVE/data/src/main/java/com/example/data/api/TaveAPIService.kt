package com.example.data.api

import com.example.data.model.HomeItemModel
import com.example.data.model.NoticeItemModel
import com.example.data.model.UserProfileModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TaveAPIService {
    @GET("member/json")
    suspend fun getHomeItemInfo(
        @Query("user")
        userUID: String
    ): Response<HomeItemModel>

    @GET("profile/json")
    suspend fun getUserProfileInfo(
        @Query("user")
        userUID: String
    ): Response<UserProfileModel>

    @GET("notice/json")
    suspend fun getNoticeInfo(): Response<NoticeItemModel>
}