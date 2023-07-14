package com.example.tave.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.sms.CheckOTPUseCase
import com.example.tave.TaveApplication
import com.example.tave.common.Constants
import com.example.tave.common.util.state.CheckOTPCodeState
import com.example.tave.di.qualifier.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputOTPViewModel @Inject constructor(
    private val checkOTPUseCase: CheckOTPUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _isOTPCodeChecked = MutableStateFlow<CheckOTPCodeState>(CheckOTPCodeState.Idle)
    val isOTPCodeChecked: StateFlow<CheckOTPCodeState> = _isOTPCodeChecked.asStateFlow()

    private val accessToken: String =
        TaveApplication.authPrefs.getTokenValue(Constants.ACCESS_TOKEN_TITLE, "")

    fun checkOTPCode(
        phoneNumber: String,
        smsInputCode: String
    ): Job = viewModelScope.launch(ioDispatcher) {
        _isOTPCodeChecked.value = CheckOTPCodeState.IsLoading

        checkOTPUseCase(accessToken, phoneNumber, smsInputCode).collect { result ->
            if (result.isSuccess) {
                _isOTPCodeChecked.value = CheckOTPCodeState.IsComplete(Result.success(Unit))
            } else {
                _isOTPCodeChecked.value = CheckOTPCodeState.IsFailed(Result.failure(Exception()))
                delay(2000)
                _isOTPCodeChecked.value = CheckOTPCodeState.Idle
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}