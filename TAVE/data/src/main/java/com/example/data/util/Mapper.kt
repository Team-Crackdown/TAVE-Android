package com.example.data.util

import com.example.data.model.login.LogInBodyModel
import com.example.data.model.login.PasswordModifyModel
import com.example.data.model.notice.NoticeDetailModel
import com.example.data.model.profile.UserProfileModel
import com.example.data.model.schedule.ScheduleModel
import com.example.domain.entity.login.LogInBodyEntity
import com.example.domain.entity.login.PasswordModifyEntity
import com.example.domain.entity.notice.NoticeDetailEntity
import com.example.domain.entity.profile.UserProfileEntity
import com.example.domain.entity.schedule.ScheduleEntity

fun toLogInModelMapper(
    item: LogInBodyEntity
): LogInBodyModel = LogInBodyModel(
    userName = item.userName,
    password = item.password
)

fun toPasswordModifyModelMapper(
    item: PasswordModifyEntity
): PasswordModifyModel = PasswordModifyModel(
    password = item.password,
    checkSMS = item.checkSMS
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
    userType = item.userType,
    isCheckSMS = item.checkSMS
)

fun toNoticeDetailEntityMapper(
    item: NoticeDetailModel
): NoticeDetailEntity = NoticeDetailEntity(
    id = item.id,
    title = item.title,
    content = item.content,
    noticeType = item.noticeType,
    images = item.images,
    adminId = item.adminId,
    createdTime = item.createdTime,
    modifiedTime = item.modifiedTime
)

fun toNoticeDetailEntityListMapper(
    item: List<NoticeDetailModel>
): List<NoticeDetailEntity> = item.map {
    NoticeDetailEntity(
        id = it.id,
        title = it.title,
        noticeType = it.noticeType,
        content = it.content,
        images = it.images,
        adminId = it.adminId,
        createdTime = it.createdTime,
        modifiedTime = it.modifiedTime
    )
}

fun toScheduleEntityListMapper(
    item: List<ScheduleModel>
): List<ScheduleEntity> = item.map {
    ScheduleEntity(
        it.place,
        it.title,
        it.date
    )
}