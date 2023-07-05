package com.example.tave.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.login.LogInBodyEntity
import com.example.domain.usecases.login.LogInUserUseCase
import com.example.domain.usecases.profile.GetCheckedSMSUseCase
import com.example.tave.TaveApplication
import com.example.tave.di.qualifier.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val logInUserUseCase: LogInUserUseCase,
    private val getCheckedSMSUseCase: GetCheckedSMSUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _logInResult = MutableLiveData<Result<Unit>>()
    private val _isCheckedSMS = MutableLiveData<Boolean>()
    val logInResult: LiveData<Result<Unit>> get() = _logInResult
    val isCheckedSMS: LiveData<Boolean> get() = _isCheckedSMS

    fun userLogInAccount(
        userEmail: String,
        userPassword: String
    ): Job = viewModelScope.launch(ioDispatcher) {
        logInUserUseCase(LogInBodyEntity(userEmail, userPassword)).collect {
            if (it != null) {
                TaveApplication.authPrefs.setTokenValue("accessToken", "Bearer $it")
                getCheckSMSField("Bearer $it")
                _logInResult.postValue(Result.success(Unit))
            } else {
                _logInResult.postValue(Result.failure(Exception()))
            }
        }
    }

    private fun getCheckSMSField(accessToken: String): Job = viewModelScope.launch(ioDispatcher) {
        getCheckedSMSUseCase(accessToken).collect { _isCheckedSMS.postValue(it) }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}