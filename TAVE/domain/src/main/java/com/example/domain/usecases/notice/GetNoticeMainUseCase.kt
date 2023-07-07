package com.example.domain.usecases.notice

import com.example.domain.entity.notice.NoticeDetailEntity
import com.example.domain.repository.TaveAPIRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class GetNoticeMainUseCase @Inject constructor(
    private val taveAPIRepository: TaveAPIRepository
) {
    operator fun invoke(
        accessToken: String
    ): Flow<NoticeDetailEntity?> = taveAPIRepository.getNoticeAll(accessToken).transform {
        emit(it?.last())
    }
}