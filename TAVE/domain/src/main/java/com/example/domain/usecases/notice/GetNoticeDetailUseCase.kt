package com.example.domain.usecases.notice

import com.example.domain.entity.notice.NoticeDetailEntity
import com.example.domain.repository.TaveAPIRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNoticeDetailUseCase @Inject constructor(
    private val taveAPIRepository: TaveAPIRepository
) {
    operator fun invoke(
        accessToken: String,
        noticeID: Int
    ): Flow<NoticeDetailEntity?> = taveAPIRepository.getNoticeDetail(accessToken, noticeID)
}