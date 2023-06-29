package com.example.tave.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.score.TeamScoreEntity
import com.example.domain.entity.score.UserScoreEntity
import com.example.domain.usecases.schedule.GetScheduleUseCase
import com.example.domain.usecases.score.GetTeamScoreUseCase
import com.example.domain.usecases.score.GetUserScoreUseCase
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
    private val personalScoreUseCase: GetUserScoreUseCase,
    private val teamScoreUseCase: GetTeamScoreUseCase,
    private val scheduleUseCase: GetScheduleUseCase,
    private val qrCodeFormat: BarcodeFormat,
    private val qrCodeWriter: QRCodeWriter,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _personalScore = MutableLiveData<UserScoreEntity>()
    private val _teamScore = MutableLiveData<TeamScoreEntity>()
    val personalScore: LiveData<UserScoreEntity> get() = _personalScore
    val teamScore: LiveData<TeamScoreEntity> get() = _teamScore

    fun getPersonalScore(userUID: Int): Job = viewModelScope.launch(ioDispatcher) {
        personalScoreUseCase(userUID).collect { _personalScore.postValue(it) }
    }

    fun getTeamScore(teamID: Int): Job = viewModelScope.launch(ioDispatcher) {
        teamScoreUseCase(teamID).collect { _teamScore.postValue(it) }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}