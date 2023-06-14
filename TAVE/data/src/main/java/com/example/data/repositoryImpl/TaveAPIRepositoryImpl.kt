package com.example.data.repositoryImpl

import com.example.data.util.Constant
import com.example.domain.entity.HomeItemEntity
import com.example.domain.entity.NoticeItemEntity
import com.example.domain.entity.UserProfileEntity
import com.example.domain.repository.TaveAPIRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import java.io.IOException
import javax.inject.Inject

class TaveAPIRepositoryImpl @Inject constructor(
    //TODO(Inject Something) Inject 필요
): TaveAPIRepository {
    override fun getHomeItems(
        userUID: String
    ): Flow<HomeItemEntity?> = flow<HomeItemEntity?> {
        // TODO(emit Something) 지금은 emit(null)로 표현
        emit(null)
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(null)
            is IllegalStateException -> emit(null)
            else -> throw exception
        }
    }. retryWhen { cause, attempt ->
        when {
            cause is IOException && attempt < Constant.FLOW_RETRY_MAX_ATTEMPTS -> {
                delay(Constant.DELAY_TIME_MILLIS)
                true
            }
            else -> false
        }
    }

    override fun getUserProfile(
        userUID: String
    ): Flow<UserProfileEntity?> = flow {
        // TODO(emit Something) 지금은 emit(null)로 표현
        emit(null)
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(null)
            is IllegalStateException -> emit(null)
            else -> throw exception
        }
    }. retryWhen { cause, attempt ->
        when {
            (cause is IOException && attempt < Constant.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Constant.DELAY_TIME_MILLIS)
                true
            }
            else -> false
        }
    }

    override fun getNotice(): Flow<List<NoticeItemEntity>> = flow<List<NoticeItemEntity>> {
        // TODO(emit Something) 지금은 emit(listOf())로 표현
        emit(listOf())
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(listOf())
            is IllegalStateException -> emit(listOf())
            else -> throw exception
        }
    }.retryWhen { cause, attempt ->
        when {
            (cause is IOException && attempt < Constant.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Constant.DELAY_TIME_MILLIS)
                true
            }
            else -> false
        }
    }
}