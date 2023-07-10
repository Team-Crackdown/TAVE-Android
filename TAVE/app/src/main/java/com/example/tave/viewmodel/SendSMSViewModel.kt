package com.example.tave.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.sms.SendSMSUseCase
import com.example.tave.TaveApplication
import com.example.tave.common.util.state.SendSMSCodeState
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
class SendSMSViewModel @Inject constructor(
    private val sendSMSUseCase: SendSMSUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _isSendSMSCode = MutableStateFlow<SendSMSCodeState>(SendSMSCodeState.Idle)
    val isSendSMSCode: StateFlow<SendSMSCodeState> get() = _isSendSMSCode.asStateFlow()

    private val accessToken: String = TaveApplication.authPrefs.getTokenValue("accessToken", "")

    fun sendSMSCode(phoneNumber: String): Job = viewModelScope.launch(ioDispatcher) {
        _isSendSMSCode.value = SendSMSCodeState.IsLoading

        sendSMSUseCase(accessToken, phoneNumber).collect { result ->
            if (result.isSuccess) {
                _isSendSMSCode.value = SendSMSCodeState.IsComplete(Result.success(Unit))
            } else {
                _isSendSMSCode.value = SendSMSCodeState.IsFailed(Result.failure(Exception()))
                delay(2000)
                _isSendSMSCode.value = SendSMSCodeState.Idle
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}