package com.example.domain.repository

import com.example.domain.entity.HomeItemEntity
import com.example.domain.entity.NoticeItemEntity
import com.example.domain.entity.UserProfileEntity
import kotlinx.coroutines.flow.Flow

interface TaveAPIRepository {
    fun getHomeItems(userUID: String): Flow<HomeItemEntity?>

    fun getUserProfile(userUID: String): Flow<UserProfileEntity?>

    fun getNotice(): Flow<List<NoticeItemEntity>>
}