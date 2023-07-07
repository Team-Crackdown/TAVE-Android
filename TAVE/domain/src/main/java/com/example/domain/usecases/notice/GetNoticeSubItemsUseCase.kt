package com.example.domain.usecases.notice

import com.example.domain.entity.notice.NoticeDetailEntity
import com.example.domain.repository.TaveAPIRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class GetNoticeSubItemsUseCase @Inject constructor(
    private val taveAPIRepository: TaveAPIRepository
) {
    operator fun invoke(
        accessToken: String
    ): Flow<List<NoticeDetailEntity>?> = taveAPIRepository.getNoticeAll(accessToken).transform {
        val result = it?.filter { item ->
            item.id != it.last().id
        }?.sortedBy { items ->
            items.id
        }?.reversed()

        emit(result)
    }
}