package com.example.domain.repository

import com.example.domain.entity.notice.NoticeDetailEntity
import com.example.domain.entity.score.TeamScoreEntity
import com.example.domain.entity.profile.UserProfileEntity
import com.example.domain.entity.schedule.ScheduleEntity
import com.example.domain.entity.score.UserScoreEntity
import kotlinx.coroutines.flow.Flow

interface TaveAPIRepository {
    fun getUserProfile(userUID: Int): Flow<UserProfileEntity?>

    fun updateUserProfile(userUID: Int, profileImage: String): Flow<Result<Unit>>

    fun getUserScore(userUID: Int): Flow<UserScoreEntity>

    fun getTeamScore(teamID: Int): Flow<TeamScoreEntity>

    fun getNoticeDetail(noticeID: Int): Flow<NoticeDetailEntity>

    fun getSchedule(scheduleID: Int): Flow<ScheduleEntity>
}