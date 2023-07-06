package com.example.tave.viewmodel

import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.TaveAPIService
import com.example.domain.entity.profile.UserProfileEntity
import com.example.domain.usecases.profile.GetUserProfileUseCase
import com.example.domain.usecases.schedule.GetRecentScheduleUseCase
import com.example.domain.usecases.score.GetPersonalScoreUseCase
import com.example.domain.usecases.score.GetTeamScoreUseCase
import com.example.tave.TaveApplication
import com.example.tave.di.qualifier.DefaultDispatcher
import com.example.tave.di.qualifier.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val taveAPIService: TaveAPIService,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getPersonalScoreUseCase: GetPersonalScoreUseCase,
    private val getTeamScoreUseCase: GetTeamScoreUseCase,
    private val getScheduleAllUseCase: GetRecentScheduleUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _userProfile = MutableLiveData<UserProfileEntity>()
    private val _personalScore = MutableLiveData<Int>()
    private val _teamScore = MutableLiveData<Int>()

    val userProfile: LiveData<UserProfileEntity> get() = _userProfile
    val personalScore: LiveData<Int> get() = _personalScore
    val teamScore: LiveData<Int> get() = _teamScore

    private val accessToken: String =
        TaveApplication.authPrefs.getTokenValue("accessToken", "")
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN)
    private val todayDate: String = dateFormat.format(System.currentTimeMillis())

    init {
        getUserProfile()
        getPersonalScore()
        getTeamScore()
        getScheduleAll()
    }

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
        getScheduleAllUseCase(accessToken).collect {  }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}