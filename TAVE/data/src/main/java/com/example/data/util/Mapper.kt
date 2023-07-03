package com.example.data.util

import com.example.data.model.login.LogInBodyModel
import com.example.data.model.notice.NoticeDetailModel
import com.example.data.model.score.TeamScoreModel
import com.example.data.model.profile.UserProfileModel
import com.example.data.model.schedule.ScheduleModel
import com.example.data.model.score.UserScoreModel
import com.example.domain.entity.login.LogInBodyEntity
import com.example.domain.entity.notice.NoticeDetailEntity
import com.example.domain.entity.score.TeamScoreEntity
import com.example.domain.entity.profile.UserProfileEntity
import com.example.domain.entity.schedule.ScheduleEntity
import com.example.domain.entity.score.UserScoreEntity

fun toLogInModelMapper(
    item: LogInBodyEntity
): LogInBodyModel = LogInBodyModel(
    userName = item.userName,
    password = item.password
)

fun toUserProfileEntityMapper(
    item: UserProfileModel
): UserProfileEntity = UserProfileEntity(
    userUID = item.id,
    userEmail = item.userEmail,
    userName = item.userName,
    userProfileImage = item.profileImage,
    userRadix = item.userRadix,
    userPhoneNumber = item.phoneNumber,
    userTech = item.userTech,
    userTeamID = item.teamId,
    userUniv = item.userUniversity,
    userType = item.userType
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

fun toScheduleEntityListMapper(
    item: List<ScheduleModel>
): List<ScheduleEntity> = item.map {
    ScheduleEntity(
        it.place,
        it.title,
        it.date
    )
}