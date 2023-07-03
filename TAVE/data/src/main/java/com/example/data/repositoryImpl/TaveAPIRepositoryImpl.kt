package com.example.data.repositoryImpl

import com.example.data.api.TaveAPIService
import com.example.data.model.login.LogInBodyModel
import com.example.data.model.notice.NoticeDetailModel
import com.example.data.model.score.TeamScoreModel
import com.example.data.model.profile.UserProfileModel
import com.example.data.model.schedule.ScheduleModel
import com.example.data.model.score.UserScoreModel
import com.example.data.util.common.Constant
import com.example.data.util.toLogInEntityMapper
import com.example.data.util.toLogInModelMapper
import com.example.data.util.toNoticeDetailEntityMapper
import com.example.data.util.toScheduleEntityMapper
import com.example.data.util.toTeamScoreEntityMapper
import com.example.data.util.toUserProfileEntityMapper
import com.example.data.util.toUserScoreEntityMapper
import com.example.domain.entity.login.LogInBodyEntity
import com.example.domain.entity.notice.NoticeDetailEntity
import com.example.domain.entity.score.TeamScoreEntity
import com.example.domain.entity.profile.UserProfileEntity
import com.example.domain.entity.schedule.ScheduleEntity
import com.example.domain.entity.score.UserScoreEntity
import com.example.domain.repository.TaveAPIRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class TaveAPIRepositoryImpl @Inject constructor(
    private val taveAPIService: TaveAPIService
): TaveAPIRepository {

    override fun userLogIn(
        logInBody: LogInBodyEntity
    ): Flow<String> = flow {
        val logInBodyModel: LogInBodyModel = toLogInModelMapper(logInBody)
        val authToken = taveAPIService.userLogIn(logInBodyModel).headers().get("Authorization")
        emit(authToken!!)
    }

    override fun getUserProfile(
        userUID: Int
    ): Flow<UserProfileEntity?> = flow<UserProfileEntity?> {
        val response: Response<UserProfileModel> = taveAPIService.getProfileInfo(userUID)

        if (response.isSuccessful && response.body() != null) {
            val result: UserProfileEntity = toUserProfileEntityMapper(response.body()!!)
            emit(result)
        } else {
            when {
                (!response.isSuccessful) -> throw HttpException(response)
                (response.body() == null) -> throw NullPointerException()
            }
        }
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(null)
            is HttpException -> emit(null)
            is IllegalStateException -> emit(null)
            else -> throw exception
        }
    }. retryWhen { cause, attempt ->
        when {
            (cause is IOException && attempt < Constant.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Constant.DELAY_TIME_MILLIS)
                true
            }
            (cause is HttpException && attempt < Constant.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Constant.DELAY_TIME_MILLIS)
                true
            }
            else -> false
        }
    }

    override fun updateUserProfile(
        userUID: Int,
        profileImage: String
    ): Flow<Result<Unit>>  = flow {
        taveAPIService.updateProfileImage(userUID, profileImage)
        emit(Result.success(Unit))
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(Result.failure(exception))
            is HttpException -> emit(Result.failure(exception))
            is IllegalStateException -> emit(Result.failure(exception))
            else -> throw exception
        }
    }.retryWhen { cause, attempt ->
        when {
            (cause is IOException && attempt < Constant.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Constant.DELAY_TIME_MILLIS)
                true
            }
            (cause is HttpException && attempt < Constant.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Constant.DELAY_TIME_MILLIS)
                true
            }
            else -> false
        }
    }

    override fun getUserScore(
        userUID: Int
    ): Flow<UserScoreEntity?> = flow<UserScoreEntity?> {
        val response: Response<UserScoreModel> = taveAPIService.getUserScore(userUID)

        if (response.isSuccessful && response.body() != null) {
            val result: UserScoreEntity = toUserScoreEntityMapper(response.body()!!)
            emit(result)
        } else {
            when {
                (!response.isSuccessful) -> throw HttpException(response)
                (response.body() == null) -> throw NullPointerException()
            }
        }
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(null)
            is HttpException -> emit(null)
            is IllegalStateException -> emit(null)
            else -> throw exception
        }
    }.retryWhen { cause, attempt ->
        when {
            (cause is IOException && attempt < Constant.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Constant.DELAY_TIME_MILLIS)
                true
            }
            (cause is HttpException && attempt < Constant.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Constant.DELAY_TIME_MILLIS)
                true
            }
            else -> false
        }
    }

    override fun getTeamScore(
        teamID: Int
    ): Flow<TeamScoreEntity?> = flow<TeamScoreEntity?> {
        val response: Response<TeamScoreModel> = taveAPIService.getTeamScore(teamID)

        if (response.isSuccessful && response.body() != null) {
            val result: TeamScoreEntity = toTeamScoreEntityMapper(response.body()!!)
            emit(result)
        } else {
            when {
                (!response.isSuccessful) -> throw HttpException(response)
                (response.body() == null) -> throw NullPointerException()
            }
        }
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(null)
            is HttpException -> emit(null)
            is IllegalStateException -> emit(null)
            else -> throw exception
        }
    }.retryWhen { cause, attempt ->
        when {
            (cause is IOException && attempt < Constant.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Constant.DELAY_TIME_MILLIS)
                true
            }
            (cause is HttpException && attempt < Constant.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Constant.DELAY_TIME_MILLIS)
                true
            }
            else -> false
        }
    }

    override fun getNoticeDetail(
        noticeID: Int
    ): Flow<NoticeDetailEntity?> = flow<NoticeDetailEntity?> {
        val response: Response<NoticeDetailModel> = taveAPIService.getNoticeDetail(noticeID)

        if (response.isSuccessful && response.body() != null) {
            val result: NoticeDetailEntity = toNoticeDetailEntityMapper(response.body()!!)
            emit(result)
        } else {
            when {
                (!response.isSuccessful) -> throw HttpException(response)
                (response.body() == null) -> throw NullPointerException()
            }
        }
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(null)
            is HttpException -> emit(null)
            is IllegalStateException -> emit(null)
            else -> throw exception
        }
    }.retryWhen { cause, attempt ->
        when {
            (cause is IOException && attempt < Constant.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Constant.DELAY_TIME_MILLIS)
                true
            }
            (cause is HttpException && attempt < Constant.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Constant.DELAY_TIME_MILLIS)
                true
            }
            else -> false
        }
    }

    override fun getSchedule(
        scheduleID: Int
    ): Flow<ScheduleEntity?> = flow<ScheduleEntity?> {
        val response: Response<ScheduleModel> = taveAPIService.getSchedule(scheduleID)

        if (response.isSuccessful && response.body() != null) {
            val result: ScheduleEntity = toScheduleEntityMapper(response.body()!!)
            emit(result)
        } else {
            when {
                (!response.isSuccessful) -> throw HttpException(response)
                (response.body() == null) -> throw NullPointerException()
            }
        }
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(null)
            is HttpException -> emit(null)
            is IllegalStateException -> emit(null)
            else -> throw exception
        }
    }.retryWhen { cause, attempt ->
        when {
            (cause is IOException && attempt < Constant.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Constant.DELAY_TIME_MILLIS)
                true
            }
            (cause is HttpException && attempt < Constant.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Constant.DELAY_TIME_MILLIS)
                true
            }
            else -> false
        }
    }

}