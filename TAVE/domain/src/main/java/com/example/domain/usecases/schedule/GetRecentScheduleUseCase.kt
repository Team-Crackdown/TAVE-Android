package com.example.domain.usecases.schedule

import android.icu.text.SimpleDateFormat
import com.example.domain.entity.schedule.ScheduleEntity
import com.example.domain.repository.TaveAPIRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
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
    ): Flow<MutableList<ScheduleEntity>?> = taveAPIRepository
        .getScheduleAll(accessToken)
        .filter {
            it?.removeAll(
                it.filter { item ->
                    todayDate.time - dateFormat.parse(item.date).time >= 0
                }
            )!!
        }
}