package com.example.domain.usecases.schedule

import com.example.domain.entity.schedule.ScheduleEntity
import com.example.domain.repository.TaveAPIRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecentScheduleUseCase @Inject constructor(
    private val taveAPIRepository: TaveAPIRepository
) {
    operator fun invoke(accessToken: String): Flow<List<ScheduleEntity>?> =
        taveAPIRepository.getScheduleAll(accessToken)
}