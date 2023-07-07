package com.example.domain.usecases.schedule

import android.icu.text.SimpleDateFormat
import com.example.domain.entity.schedule.ScheduleEntity
import com.example.domain.repository.TaveAPIRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class GetRecentScheduleUseCase @Inject constructor(
    private val taveAPIRepository: TaveAPIRepository
) {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN)
    private val todayDate: Date = dateFormat.parse(dateFormat.format(System.currentTimeMillis()))

    operator fun invoke(
        accessToken: String
    ): Flow<ScheduleEntity> = taveAPIRepository.getScheduleAll(accessToken).filter {
        it?.removeAll(it.filter { item ->  filteringSchedule(dateFormat.parse(item.date)) })!!
    }.map {
        it?.sortedBy { item -> dateFormat.parse(item.date).time } ?: listOf()
    }.transform {
        emit(it.first())
    }

    private fun filteringSchedule(
        scheduleTime: Date
    ): Boolean = (todayDate.time - scheduleTime.time >= 0)

}