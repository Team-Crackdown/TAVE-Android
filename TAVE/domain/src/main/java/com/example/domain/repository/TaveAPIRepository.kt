package com.example.domain.repository

import com.example.domain.entity.login.LogInBodyEntity
import com.example.domain.entity.login.PasswordModifyEntity
import com.example.domain.entity.notice.NoticeDetailEntity
import com.example.domain.entity.score.TeamScoreEntity
import com.example.domain.entity.profile.UserProfileEntity
import com.example.domain.entity.schedule.ScheduleEntity
import com.example.domain.entity.score.UserScoreEntity
import kotlinx.coroutines.flow.Flow

interface TaveAPIRepository {
    fun userLogIn(logInBody: LogInBodyEntity): Flow<String?>

    fun sendSMSCode(
        accessToken: String,
        phoneNumber: String
    ): Flow<Result<Unit>>

    fun checkOTPCode(
        accessToken: String,
        otpCode: String
    ): Flow<Result<Unit>>

    fun getProfileInfo(accessToken: String): Flow<UserProfileEntity?>

    fun updateMemberPassword(
        accessToken: String,
        passwordModifyEntity: PasswordModifyEntity
    ): Flow<Result<Unit>>

    fun updateProfileImage(
        accessToken: String,
        profileImage: String
    ): Flow<Result<Unit>>

    fun getPersonalScore(
        accessToken: String,
        memberId: Int
    ): Flow<UserScoreEntity?>

    fun getTeamScore(
        accessToken: String,
        teamID: Int
    ): Flow<TeamScoreEntity?>

    fun getScheduleAll(accessToken: String): Flow<List<ScheduleEntity>?>

    fun getNoticeAll(accessToken: String): Flow<List<NoticeDetailEntity>?>

    fun getNoticeDetail(
        accessToken: String,
        noticeID: Int
    ): Flow<NoticeDetailEntity?>
}