package com.example.tave.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.profile.UserProfileEntity
import com.example.domain.entity.score.TeamScoreEntity
import com.example.domain.usecases.profile.GetUserProfileUseCase
import com.example.domain.usecases.schedule.GetScheduleAllUseCase
import com.example.domain.usecases.score.GetPersonalScoreUseCase
import com.example.domain.usecases.score.GetTeamScoreUseCase
import com.example.tave.di.coroutineDispatcher.IoDispatcher
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userProfileUseCase: GetUserProfileUseCase,
    private val personalScoreUseCase: GetPersonalScoreUseCase,
    private val teamScoreUseCase: GetTeamScoreUseCase,
    private val scheduleUseCase: GetScheduleAllUseCase,
    private val qrCodeFormat: BarcodeFormat,
    private val qrCodeWriter: QRCodeWriter,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _userProfile = MutableLiveData<UserProfileEntity>()
    private val _teamScore = MutableLiveData<TeamScoreEntity>()

    val userProfile: LiveData<UserProfileEntity> get() = _userProfile
    val teamScore: LiveData<TeamScoreEntity> get() = _teamScore

    init {
        viewModelScope.launch(ioDispatcher) {
            launch { userProfileUseCase().collect { _userProfile.postValue(it) } }.join()
            Log.d("teamID", "${_userProfile.value?.userTeamID}")
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}