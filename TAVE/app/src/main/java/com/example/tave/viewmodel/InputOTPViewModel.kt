package com.example.tave.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.sms.CheckOTPUseCase
import com.example.tave.TaveApplication
import com.example.tave.di.qualifier.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputOTPViewModel @Inject constructor(
    private val checkOTPUseCase: CheckOTPUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _isOTPSuccess = MutableLiveData<Result<Unit>>()
    val isOTPSuccess: LiveData<Result<Unit>> get() = _isOTPSuccess

    private val accessToken: String = TaveApplication.authPrefs.getTokenValue("accessToken", "")

    fun checkOTPCode(smsInputCode: String): Job = viewModelScope.launch(ioDispatcher) {
        checkOTPUseCase(accessToken, smsInputCode).collect { result -> _isOTPSuccess.postValue(result) }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}