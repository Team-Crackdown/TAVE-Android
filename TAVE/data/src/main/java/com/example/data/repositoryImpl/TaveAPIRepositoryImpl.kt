package com.example.data.repositoryImpl

import com.example.data.api.TaveAPIService
import com.example.data.model.login.LogInBodyModel
import com.example.data.model.login.PasswordModifyModel
import com.example.data.model.notice.NoticeDetailModel
import com.example.data.model.profile.UserProfileModel
import com.example.data.model.schedule.ScheduleModel
import com.example.data.util.common.Common
import com.example.data.util.toLogInModelMapper
import com.example.data.util.toNoticeDetailEntityListMapper
import com.example.data.util.toNoticeDetailEntityMapper
import com.example.data.util.toPasswordModifyModelMapper
import com.example.data.util.toScheduleEntityListMapper
import com.example.data.util.toUserProfileEntityMapper
import com.example.domain.entity.login.LogInBodyEntity
import com.example.domain.entity.login.PasswordModifyEntity
import com.example.domain.entity.notice.NoticeDetailEntity
import com.example.domain.entity.profile.UserProfileEntity
import com.example.domain.entity.schedule.ScheduleEntity
import com.example.domain.repository.TaveAPIRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.security.InvalidKeyException
import javax.inject.Inject

class TaveAPIRepositoryImpl @Inject constructor(
    private val taveAPIService: TaveAPIService
): TaveAPIRepository {
    override fun userLogIn(logInBody: LogInBodyEntity): Flow<String?> = flow {
        val logInBodyModel: LogInBodyModel = toLogInModelMapper(logInBody)
        val authToken = taveAPIService.userLogIn(logInBodyModel).headers().get("Authorization")
        emit(authToken)
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(null)
            is HttpException -> emit(null)
            is IllegalStateException -> emit(null)
            else -> throw exception
        }
    }

    override fun sendSMSCode(
        accessToken: String,
        phoneNumber: String
    ): Flow<Result<Unit>> = flow {
        try{
            taveAPIService.sendSMSCode(accessToken, "", phoneNumber)
            emit(Result.success(Unit))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun checkOTPCode(
        accessToken: String,
        otpCode: String
    ): Flow<Result<Unit>> = flow {
        try {
            taveAPIService.checkOTPCode(accessToken, "", otpCode)
            emit(Result.success(Unit))
        } catch (e: InvalidKeyException) {
            emit(Result.failure(e))
        }
    }

    override fun updateMemberPassword(
        accessToken: String,
        passwordModifyEntity: PasswordModifyEntity
    ): Flow<Result<Unit>> = flow {
        val modifyModel: PasswordModifyModel = toPasswordModifyModelMapper(passwordModifyEntity)

        try {
            taveAPIService.updateMemberPassword(accessToken, modifyModel)
            emit(Result.success(Unit))
        } catch (e: InvalidKeyException) {
            emit(Result.failure(e))
        }
    }

    override fun getProfileInfo(accessToken: String): Flow<UserProfileEntity?> = flow<UserProfileEntity?> {
        val response: Response<UserProfileModel> = taveAPIService.getProfileInfo(accessToken)

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
            (cause is IOException && attempt < Common.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Common.DELAY_TIME_MILLIS)
                true
            }
            (cause is HttpException && attempt < Common.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Common.DELAY_TIME_MILLIS)
                true
            }
            else -> false
        }
    }

    override fun updateProfileImage(accessToken: String, profileImage: String): Flow<Result<Unit>> = flow {
        taveAPIService.updateProfileImage(accessToken, profileImage)
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
            (cause is IOException && attempt < Common.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Common.DELAY_TIME_MILLIS)
                true
            }
            (cause is HttpException && attempt < Common.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Common.DELAY_TIME_MILLIS)
                true
            }
            else -> false
        }
    }

    override fun getPersonalScore(accessToken: String, memberId: Int): Flow<Int> = flow {
        val response = taveAPIService.getPersonalScore(accessToken, memberId)

        if (response.isSuccessful && response.body() != null) {
            val result = response.body()
            emit(result!!)
        } else {
            when {
                (!response.isSuccessful) -> throw HttpException(response)
                (response.body() == null) -> throw NullPointerException()
            }
        }
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(0)
            is HttpException -> emit(0)
            is IllegalStateException -> emit(0)
            else -> throw exception
        }
    }.retryWhen { cause, attempt ->
        when {
            (cause is IOException && attempt < Common.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Common.DELAY_TIME_MILLIS)
                true
            }
            (cause is HttpException && attempt < Common.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Common.DELAY_TIME_MILLIS)
                true
            }
            else -> false
        }
    }

    override fun getTeamScore(accessToken: String, teamID: Int): Flow<Int> = flow {
        val response = taveAPIService.getTeamScore(accessToken, teamID)

        if (response.isSuccessful && response.body() != null) {
            val result: Int = response.body()!!
            emit(result)
        } else {
            when {
                (!response.isSuccessful) -> throw HttpException(response)
                (response.body() == null) -> throw NullPointerException()
            }
        }
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(0)
            is HttpException -> emit(0)
            is IllegalStateException -> emit(0)
            else -> throw exception
        }
    }.retryWhen { cause, attempt ->
        when {
            (cause is IOException && attempt < Common.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Common.DELAY_TIME_MILLIS)
                true
            }
            (cause is HttpException && attempt < Common.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Common.DELAY_TIME_MILLIS)
                true
            }
            else -> false
        }
    }

    override fun getNoticeAll(accessToken: String): Flow<List<NoticeDetailEntity>?> = flow<List<NoticeDetailEntity>?> {
        val response: Response<List<NoticeDetailModel>> = taveAPIService.getNoticeAll(accessToken)

        if (response.isSuccessful && response.body() != null) {
            val result: List<NoticeDetailEntity> = toNoticeDetailEntityListMapper(response.body()!!)
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
            (cause is IOException && attempt < Common.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Common.DELAY_TIME_MILLIS)
                true
            }
            (cause is HttpException && attempt < Common.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Common.DELAY_TIME_MILLIS)
                true
            }
            else -> false
        }
    }

    override fun getNoticeDetail(accessToken: String, noticeID: Int): Flow<NoticeDetailEntity?> = flow<NoticeDetailEntity?> {
        val response: Response<NoticeDetailModel> = taveAPIService.getNoticeDetail(accessToken, noticeID)

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
            (cause is IOException && attempt < Common.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Common.DELAY_TIME_MILLIS)
                true
            }
            (cause is HttpException && attempt < Common.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Common.DELAY_TIME_MILLIS)
                true
            }
            else -> false
        }
    }

    override fun getScheduleAll(accessToken: String): Flow<List<ScheduleEntity>?> = flow<List<ScheduleEntity>?> {
        val response: Response<List<ScheduleModel>> = taveAPIService.getScheduleAll(accessToken)

        if (response.isSuccessful && response.body() != null) {
            val result: List<ScheduleEntity> = toScheduleEntityListMapper(response.body()!!)
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
            (cause is IOException && attempt < Common.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Common.DELAY_TIME_MILLIS)
                true
            }
            (cause is HttpException && attempt < Common.FLOW_RETRY_MAX_ATTEMPTS) -> {
                delay(Common.DELAY_TIME_MILLIS)
                true
            }
            else -> false
        }
    }
}