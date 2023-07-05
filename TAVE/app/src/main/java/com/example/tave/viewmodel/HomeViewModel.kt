package com.example.tave.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.profile.UserProfileEntity
import com.example.domain.entity.score.TeamScoreEntity
import com.example.domain.entity.score.UserScoreEntity
import com.example.domain.usecases.profile.GetMemberIdUseCase
import com.example.domain.usecases.profile.GetTeamIdUseCase
import com.example.domain.usecases.profile.GetUserProfileUseCase
import com.example.domain.usecases.schedule.GetScheduleAllUseCase
import com.example.domain.usecases.score.GetPersonalScoreUseCase
import com.example.domain.usecases.score.GetTeamScoreUseCase
import com.example.tave.common.Constants
import com.example.tave.di.coroutineDispatcher.IoDispatcher
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getMemberIdUseCase: GetMemberIdUseCase,
    private val getTeamIdUseCase: GetTeamIdUseCase,
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
    private var userUID: Int = 0
    private var teamID: Int = 0

    val userProfile: LiveData<UserProfileEntity> get() = _userProfile
    val personalScore: LiveData<UserScoreEntity> get() = _personalScore
    val teamScore: LiveData<TeamScoreEntity> get() = _teamScore

    init {
        getUserProfile()
    }

    private fun getUserProfile(): Job = viewModelScope.launch(ioDispatcher) {
        getUserProfileUseCase().collect {
            Log.d("Profile", "${it}")
            _userProfile.postValue(it)
        }
    }

    private fun getMemberID(): Job = viewModelScope.launch(ioDispatcher) {
        getMemberIdUseCase().collect { userUID = it }
    }

    private fun getTeamID(): Job = viewModelScope.launch(ioDispatcher) {
        getTeamIdUseCase().collect { teamID = it }
    }

    private fun getPersonalScore(): Job = viewModelScope.launch(ioDispatcher) {
        getPersonalScoreUseCase(userUID).collect { _personalScore.postValue(it) }
    }

    private fun getTeamScore(): Job = viewModelScope.launch(ioDispatcher) {
        getTeamScoreUseCase(teamID).collect { _teamScore.postValue(it) }
    }

    fun generateQRCode(content: String?): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            bitmap = qrCodeToBitmap(
                qrCodeWriter.encode(
                    content,
                    qrCodeFormat,
                    Constants.QR_WIDTH,
                    Constants.QR_HEIGHT
                )
            )
        } catch (e: WriterException) {
            e.printStackTrace()
        }
        return bitmap
    }

    private fun qrCodeToBitmap(matrix: BitMatrix): Bitmap? {
        val bmp = Bitmap.createBitmap(matrix.width, matrix.height, Bitmap.Config.RGB_565)
        for (x in 0 until matrix.width) {
            for (y in 0 until matrix.height) {
                bmp.setPixel(x, y, if (matrix.get(x, y)) Constants.BLACK else Constants.WHITE)
            }
        }
        return bmp
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}