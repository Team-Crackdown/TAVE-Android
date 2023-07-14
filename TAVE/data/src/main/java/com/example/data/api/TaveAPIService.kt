package com.example.data.api

import com.example.data.model.login.LogInBodyModel
import com.example.data.model.login.ModifyPasswordModel
import com.example.data.model.notice.NoticeDetailModel
import com.example.data.model.profile.UserProfileModel
import com.example.data.model.schedule.ScheduleModel
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface TaveAPIService {
    @POST("login")
    suspend fun userLogIn(@Body logInBody: LogInBodyModel): Response<Void>

    @POST("coolSms/get")
    suspend fun sendSMSCode(
        @Header("Authorization") accessToken: String,
        @Header("apiKey") coolSmsKey: String,
        @Query("phoneNumber") phoneNumber: String
    ): Response<Void>

    @POST("coolSms/check")
    suspend fun checkOTPCode(
        @Header("Authorization") accessToken: String,
        @Header("apiKey") coolSmsKey: String,
        @Query("phoneNumber") phoneNumber: String,
        @Query("certificationNumber") OTPCode: String
    ): Response<Boolean>

    @GET("memberRole/member/getMember")
    suspend fun getProfileInfo(@Header("Authorization") accessToken: String): Response<UserProfileModel>

    @PATCH("memberRole/member/modifyMember")
    suspend fun updateMemberPassword(
        @Header("Authorization") accessToken: String,
        @Body passwordModifyModel: ModifyPasswordModel
    ): Response<Void>

    @Multipart
    @PATCH("memberRole/member/modifyProfileImage")
    suspend fun updateProfileImage(
        @Header("Authorization") accessToken: String,
        @Part profileImage: MultipartBody.Part
    ): Response<Void>

    @GET("memberRole/member/getMemberScore")
    suspend fun getPersonalScore(
        @Header("Authorization") accessToken: String,
        @Query("memberId") memberID: Int
    ): Response<Int>

    @GET("memberRole/team/getTeamScore")
    suspend fun getTeamScore(
        @Header("Authorization") accessToken: String,
        @Query("teamId") teamID: Int
    ): Response<Int>

    @GET("memberRole/schedule/getAllSchedule")
    suspend fun getScheduleAll(
        @Header("Authorization") accessToken: String
    ): Response<List<ScheduleModel>>

    @GET("/memberRole/notice/getAllNotice")
    suspend fun getNoticeAll(
        @Header("Authorization") accessToken: String
    ): Response<List<NoticeDetailModel>>

    @GET("/memberRole/notice/getNotice")
    suspend fun getNoticeDetail(
        @Header("Authorization") accessToken: String,
        @Query("noticeId") noticeID: Int
    ): Response<NoticeDetailModel>

}