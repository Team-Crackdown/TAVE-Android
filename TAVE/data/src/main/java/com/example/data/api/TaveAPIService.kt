package com.example.data.api

import com.example.data.model.login.LogInBodyModel
import com.example.data.model.notice.NoticeDetailModel
import com.example.data.model.score.TeamScoreModel
import com.example.data.model.profile.UserProfileModel
import com.example.data.model.schedule.ScheduleModel
import com.example.data.model.score.UserScoreModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface TaveAPIService {
    @POST("login")
    suspend fun userLogIn(@Body logInBody: LogInBodyModel): Response<Void>

    @GET("memberRole/member/getMember")
    suspend fun getProfileInfo(): Response<UserProfileModel>

    @PATCH("memberRole/member/modifyProfileImage")
    suspend fun updateProfileImage(@Body profileImage: String): Response<Void>

    @GET("memberRole/team/getTeamScore")
    suspend fun getTeamScore(@Query("teamScoreNoteId") teamID: Int): Response<TeamScoreModel>

    @GET("memberRole/schedule/getAllSchedule")
    suspend fun getScheduleAll(): Response<List<ScheduleModel>>

    @GET("/memberRole/notice/getAllNotice")
    suspend fun getNoticeAll(): Response<List<NoticeDetailModel>>

    @GET("notice/getnotice")
    suspend fun getNoticeDetail(@Query("noticeId") noticeID: Int): Response<NoticeDetailModel>

}