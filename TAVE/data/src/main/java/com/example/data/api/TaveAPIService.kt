package com.example.data.api

import com.example.data.model.notice.NoticeDetailModel
import com.example.data.model.score.TeamScoreModel
import com.example.data.model.profile.UserProfileModel
import com.example.data.model.schedule.ScheduleModel
import com.example.data.model.score.UserScoreModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query

interface TaveAPIService {
    @GET("member/getmember")
    suspend fun getProfileInfo(@Query("memberId") userUID: Int): Response<UserProfileModel>

    @PATCH("member/modifyprofileimage")
    suspend fun updateProfileImage(
        @Query("memberId") userUID: Int,
        @Body profileImage: String
    ): Response<Result<Unit>>

    @GET("memberScoreNote/getmemberscorenote")
    suspend fun getUserScore(@Query("memberScoreNoteId") userUID: Int): Response<UserScoreModel>

    @GET("teamScoreNote/getteamscorenote")
    suspend fun getTeamScore(@Query("teamScoreNoteId") teamID: Int): Response<TeamScoreModel>

    @GET("schedule/getschedule")
    suspend fun getSchedule(@Query("scheduleId") scheduleID: Int): Response<ScheduleModel>

    @GET("notice/getnotice")
    suspend fun getNoticeDetail(@Query("noticeId") noticeID: Int): Response<NoticeDetailModel>

}