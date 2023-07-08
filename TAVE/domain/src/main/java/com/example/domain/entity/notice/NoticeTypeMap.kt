package com.example.domain.entity.notice


private const val NOTICE_TYPE_SCHEDULE: String = "[뉴스] "
private const val NOTICE_TYPE_GENERAL: String = "[공지] "
private const val NOTICE_TYPE_REVIEW: String = "[리뷰] "
private const val NOTICE_TYPE_NEWS: String = "[뉴스] "
private const val NOTICE_TYPE_TECH: String = "[기술 레터] "

val noticeTypeMap = mapOf(
    NoticeTypeEnumClass.SCHEDULE.name to NOTICE_TYPE_SCHEDULE,
    NoticeTypeEnumClass.GENERAL.name to NOTICE_TYPE_GENERAL,
    NoticeTypeEnumClass.REVIEW.name to NOTICE_TYPE_REVIEW,
    NoticeTypeEnumClass.NEWS.name to NOTICE_TYPE_NEWS,
    NoticeTypeEnumClass.TECH.name to NOTICE_TYPE_TECH
)
