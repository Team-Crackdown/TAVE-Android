package com.example.data.util

import com.example.data.model.HomeItemModel
import com.example.data.model.NoticeItemModel
import com.example.data.model.UserProfileModel
import com.example.domain.entity.HomeItemEntity
import com.example.domain.entity.NoticeItemEntity
import com.example.domain.entity.UserProfileEntity

fun toHomeItemEntityMapper(
    item: HomeItemModel
): HomeItemEntity = HomeItemEntity(
    userRadix = item.userRadix,
    personalScore = item.personalScore,
    teamScore = item.teamScore,
    eventDateTime = item.eventDateTime
)

fun toUserProfileEntityMapper(
    item: UserProfileModel
): UserProfileEntity = UserProfileEntity(
    userRadix = item.userRadix,
    userName = item.userName,
    userEmail = item.userEmail,
    userDept = item.userDept,
    userUniversity = item.userUniversity,
    userPhoneNumber = item.userPhoneNumber,
    userIntro = item.userIntro
)

fun toNoticeItemEntityMapper(
    items: List<NoticeItemModel>
): List<NoticeItemEntity> = items.map {
    NoticeItemEntity(
        notice_ID = it.notice_ID,
        notice_Content = it.notice_Content,
        notice_Photos = it.notice_Photos
    )
}