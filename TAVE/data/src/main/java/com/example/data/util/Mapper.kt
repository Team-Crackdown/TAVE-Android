package com.example.data.util

import com.example.data.model.notice.NoticeDetailModel
import com.example.data.model.score.TeamScoreModel
import com.example.data.model.profile.UserProfileModel
import com.example.data.model.schedule.ScheduleModel
import com.example.data.model.score.UserScoreModel
import com.example.domain.entity.notice.NoticeDetailEntity
import com.example.domain.entity.score.TeamScoreEntity
import com.example.domain.entity.profile.UserProfileEntity
import com.example.domain.entity.schedule.ScheduleEntity
import com.example.domain.entity.score.UserScoreEntity

fun toUserProfileEntityMapper(
    item: UserProfileModel
): UserProfileEntity = UserProfileEntity(
    userUID = item.id,
    userEmail = item.email,
    userName = item.name,
    userProfileImage = item.profileImage,
    userRadix = item.rad,
    userPhoneNumber = item.phoneNumber,
    userTech = item.techField,
    userTeamID = item.teamID,
    userUniv = item.university,
    userType = item.memberType
)

fun toUserScoreEntityMapper(
    item: UserScoreModel
): UserScoreEntity = UserScoreEntity(personalScore = item.score)

fun toTeamScoreEntityMapper(
    item: TeamScoreModel
): TeamScoreEntity = TeamScoreEntity(teamScore = item.score)

fun toNoticeDetailEntityMapper(
    item: NoticeDetailModel
): NoticeDetailEntity = NoticeDetailEntity(content = item.content)

fun toScheduleEntityMapper(
    item: ScheduleModel
): ScheduleEntity = ScheduleEntity(
    place = item.place,
    title = item.title,
    date = item.date
)