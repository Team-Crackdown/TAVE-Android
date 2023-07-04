package com.example.tave.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.sms.CheckOTPUseCase
import com.example.domain.usecases.sms.SendSMSUseCase
import com.example.tave.di.coroutineDispatcher.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SMSViewModel @Inject constructor(
    private val sendSMSUseCase: SendSMSUseCase,
    private val checkOTPUseCase: CheckOTPUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _isSendComplete = MutableLiveData<Result<Unit>>()
    private val _isCheckComplete = MutableLiveData<Result<Unit>>()

    val isSendComplete: LiveData<Result<Unit>> get() = _isSendComplete
    val isCheckComplete: LiveData<Result<Unit>> get() = _isCheckComplete

    fun sendSMSCode(phoneNumber: String): Job = viewModelScope.launch(ioDispatcher) {
        sendSMSUseCase(phoneNumber).collect { result -> _isSendComplete.postValue(result) }
    }

    fun checkOTPCode(otpCode: String): Job = viewModelScope.launch(ioDispatcher) {
        checkOTPUseCase(otpCode).collect { result -> _isCheckComplete.postValue(result) }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}