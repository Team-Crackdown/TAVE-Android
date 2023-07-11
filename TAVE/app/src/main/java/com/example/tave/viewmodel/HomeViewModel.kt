package com.example.tave.viewmodel

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.TaveAPIService
import com.example.domain.entity.profile.UserProfileEntity
import com.example.domain.entity.schedule.ScheduleEntity
import com.example.domain.usecases.profile.GetUserProfileUseCase
import com.example.domain.usecases.schedule.GetRecentScheduleUseCase
import com.example.domain.usecases.score.GetPersonalScoreUseCase
import com.example.domain.usecases.score.GetTeamScoreUseCase
import com.example.tave.BuildConfig
import com.example.tave.TaveApplication
import com.example.tave.common.Constants
import com.example.tave.di.qualifier.DefaultDispatcher
import com.example.tave.di.qualifier.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Request
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val taveAPIService: TaveAPIService,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getPersonalScoreUseCase: GetPersonalScoreUseCase,
    private val getTeamScoreUseCase: GetTeamScoreUseCase,
    private val getScheduleAllUseCase: GetRecentScheduleUseCase,
    private val sseEventListener: EventSource.Factory,
    private val sseEventSourceListener: EventSourceListener,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _userProfile = MutableLiveData<UserProfileEntity>()
    private val _personalScore = MutableLiveData<Int>()
    private val _teamScore = MutableLiveData<Int>()
    private val _scheduleTitle = MutableLiveData<String>()
    private val _scheduleRemainDay = MutableLiveData<String>()

    val userProfile: LiveData<UserProfileEntity> get() = _userProfile
    val personalScore: LiveData<Int> get() = _personalScore
    val teamScore: LiveData<Int> get() = _teamScore
    val scheduleTitle: LiveData<String> get() = _scheduleTitle
    val scheduleRemainDay: LiveData<String> get() = _scheduleRemainDay

    private val accessToken: String =
        TaveApplication.authPrefs.getTokenValue(Constants.ACCESS_TOKEN_TITLE, "")
    private val dateFormat = SimpleDateFormat(Constants.SCHEDULE_DATE_TIME_FORMAT, Locale.KOREAN)
    private val todayDate: Date = Calendar.getInstance().time

    init {
        getUserProfile()
        getPersonalScore()
        getTeamScore()
        getScheduleAll()
        sseEventSource().request()
    }

    private fun setSSERequestInstance(): Request = Request.Builder()
        .url(BuildConfig.TAVE_SSE_URL)
        .addHeader(Constants.AUTHORIZATION_HEADER_TITLE, accessToken)
        .addHeader(Constants.SSE_HEADER_TITLE, Constants.SSE_HEADER_VALUE)
        .build()

    private fun sseEventSource(): EventSource =
        sseEventListener.newEventSource(setSSERequestInstance(), sseEventSourceListener)


    private fun getUserProfile(): Job = viewModelScope.launch(ioDispatcher) {
        getUserProfileUseCase(accessToken).collect { _userProfile.postValue(it) }
    }

    private fun getPersonalScore(): Job = viewModelScope.launch(ioDispatcher) {
        val userUID: Int? = withContext(defaultDispatcher) {
            taveAPIService.getProfileInfo(accessToken).body()?.id
        }

        launch(ioDispatcher) {
            getPersonalScoreUseCase(accessToken, userUID!!).collect { _personalScore.postValue(it) }
        }
    }

    private fun getTeamScore(): Job = viewModelScope.launch(ioDispatcher) {
        val teamID: Int? = withContext(defaultDispatcher) {
            taveAPIService.getProfileInfo(accessToken).body()?.teamId
        }

        launch(ioDispatcher) {
            getTeamScoreUseCase(accessToken, teamID!!).collect { _teamScore.postValue(it) }
        }
    }

    private fun getScheduleAll(): Job = viewModelScope.launch(ioDispatcher) {
        getScheduleAllUseCase(accessToken).collect { item ->
            if (item.isEmpty()) {
                _scheduleTitle.postValue(Constants.IS_SCHEDULE_EMPTY_TITLE)
                _scheduleRemainDay.postValue(Constants.IS_SCHEDULE_EMPTY_DAY)
            } else {
                val recentSchedule: ScheduleEntity = item.first()
                val scheduleDate: Date = dateFormat.parse(recentSchedule.date)
                val remainDate: Int = calculateDDay(scheduleDate.time)

                when {
                    remainDate > 0 -> {
                        _scheduleTitle.postValue(recentSchedule.title)
                        _scheduleRemainDay.postValue(remainDate.toString())
                    }
                    remainDate == 0 -> {
                        _scheduleTitle.postValue(recentSchedule.title)
                        _scheduleRemainDay.postValue(Constants.IS_SCHEDULE_D_DAY)
                    }
                }
            }
        }
    }

    private fun calculateDDay(scheduleDate: Long): Int =
        ((scheduleDate - todayDate.time) / (60 * 60 * 24 * 1000)).toInt()

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}