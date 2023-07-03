package com.example.domain.repository

import com.example.domain.entity.login.LogInBodyEntity
import com.example.domain.entity.notice.NoticeDetailEntity
import com.example.domain.entity.score.TeamScoreEntity
import com.example.domain.entity.profile.UserProfileEntity
import com.example.domain.entity.schedule.ScheduleEntity
import com.example.domain.entity.score.UserScoreEntity
import kotlinx.coroutines.flow.Flow

interface TaveAPIRepository {
    fun userLogIn(logInBody: LogInBodyEntity): Flow<String?>

    fun getProfileInfo(): Flow<UserProfileEntity?>

    fun updateProfileImage(profileImage: String): Flow<Result<Unit>>

    fun getTeamScore(teamID: Int): Flow<TeamScoreEntity?>

    fun getScheduleAll(): Flow<List<ScheduleEntity>?>

    fun getNoticeAll(): Flow<List<NoticeDetailEntity>?>

    fun getNoticeDetail(noticeID: Int): Flow<NoticeDetailEntity?>
}