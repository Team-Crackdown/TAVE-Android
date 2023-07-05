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
class SendSMSViewModel @Inject constructor(
    private val sendSMSUseCase: SendSMSUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _isSendComplete = MutableLiveData<Result<Unit>>()
    val isSendComplete: LiveData<Result<Unit>> get() = _isSendComplete

    fun sendSMSCode(phoneNumber: String): Job = viewModelScope.launch(ioDispatcher) {
        sendSMSUseCase(phoneNumber).collect { result -> _isSendComplete.postValue(result) }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}