package com.example.domain.usecases.notice

import com.example.domain.entity.notice.NoticeDetailEntity
import com.example.domain.repository.TaveAPIRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNoticeAllUseCase @Inject constructor(
    private val taveAPIRepository: TaveAPIRepository
) {
    operator fun invoke(): Flow<List<NoticeDetailEntity>?> = taveAPIRepository.getNoticeAll()
}