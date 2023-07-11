package com.example.domain.entity.notice

import com.example.domain.util.Constants.NOTICE_TYPE_GENERAL
import com.example.domain.util.Constants.NOTICE_TYPE_NEWS
import com.example.domain.util.Constants.NOTICE_TYPE_REVIEW
import com.example.domain.util.Constants.NOTICE_TYPE_SCHEDULE
import com.example.domain.util.Constants.NOTICE_TYPE_TECH

val noticeTypeMap = mapOf(
    NoticeTypeEnumClass.SCHEDULE.name to NOTICE_TYPE_SCHEDULE,
    NoticeTypeEnumClass.GENERAL.name to NOTICE_TYPE_GENERAL,
    NoticeTypeEnumClass.REVIEW.name to NOTICE_TYPE_REVIEW,
    NoticeTypeEnumClass.NEWS.name to NOTICE_TYPE_NEWS,
    NoticeTypeEnumClass.TECH.name to NOTICE_TYPE_TECH
)
