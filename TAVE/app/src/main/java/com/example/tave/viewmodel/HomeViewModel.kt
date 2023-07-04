package com.example.tave.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.profile.UserProfileEntity
import com.example.domain.entity.score.TeamScoreEntity
import com.example.domain.entity.score.UserScoreEntity
import com.example.domain.usecases.profile.GetUserProfileUseCase
import com.example.domain.usecases.schedule.GetScheduleAllUseCase
import com.example.domain.usecases.score.GetPersonalScoreUseCase
import com.example.domain.usecases.score.GetTeamScoreUseCase
import com.example.tave.di.coroutineDispatcher.IoDispatcher
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userProfileUseCase: GetUserProfileUseCase,
    private val getPersonalScoreUseCase: GetPersonalScoreUseCase,
    private val getTeamScoreUseCase: GetTeamScoreUseCase,
    private val getScheduleAllUseCase: GetScheduleAllUseCase,
    private val qrCodeFormat: BarcodeFormat,
    private val qrCodeWriter: QRCodeWriter,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _userProfile = MutableLiveData<UserProfileEntity>()
    private val _personalScore = MutableLiveData<UserScoreEntity>()
    private val _teamScore = MutableLiveData<TeamScoreEntity>()

    val userProfile: LiveData<UserProfileEntity> get() = _userProfile
    val personalScore: LiveData<UserScoreEntity> get() = _personalScore
    val teamScore: LiveData<TeamScoreEntity> get() = _teamScore

    init {
        viewModelScope.launch(ioDispatcher) {
            userProfileUseCase().collect { _userProfile.postValue(it) }
        }
    }

    private fun getPersonalScore(userUID: Int): Job = viewModelScope.launch(ioDispatcher) {
        getPersonalScoreUseCase(userUID).collect { _personalScore.postValue(it) }
    }

    private fun getTeamScore(teamID: Int): Job = viewModelScope.launch(ioDispatcher) {
        getTeamScoreUseCase(teamID).collect { _teamScore.postValue(it) }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}