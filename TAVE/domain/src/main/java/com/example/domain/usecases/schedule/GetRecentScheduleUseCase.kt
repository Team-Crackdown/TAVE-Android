package com.example.domain.usecases.schedule

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import com.example.domain.entity.schedule.ScheduleEntity
import com.example.domain.repository.TaveAPIRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class GetRecentScheduleUseCase @Inject constructor(
    private val taveAPIRepository: TaveAPIRepository
) {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN)
    private val todayDate: Long = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time.time

    operator fun invoke(
        accessToken: String
    ): Flow<List<ScheduleEntity>> = taveAPIRepository.getScheduleAll(accessToken).filter {
            it?.removeAll(it.filter { item -> filteringSchedule(dateFormat.parse(item.date)) })!!
        }.map { it?.sortedBy { item -> dateFormat.parse(item.date).time } ?: listOf() }

    private fun filteringSchedule(scheduleTime: Date): Boolean {
        return ((scheduleTime.time - todayDate)/(24*60*60/1000)) < 0
    }
}