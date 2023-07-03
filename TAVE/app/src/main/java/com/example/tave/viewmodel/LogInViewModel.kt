package com.example.tave.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.login.LogInBodyEntity
import com.example.domain.usecases.login.LogInUserUseCase
import com.example.tave.TaveApplication
import com.example.tave.di.coroutineDispatcher.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val logInUserUseCase: LogInUserUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _logInResult = MutableLiveData<Result<Unit>>()
    val logInResult: LiveData<Result<Unit>> get() = _logInResult

    fun userLogInAccount(
        userEmail: String,
        userPassword: String
    ): Job = viewModelScope.launch(ioDispatcher) {
        logInUserUseCase(LogInBodyEntity(userEmail, userPassword)).collect {
            if (it == null) {
                _logInResult.postValue(Result.failure(Exception()))
            } else {
                _logInResult.postValue(Result.success(Unit))
                Log.d("Token", "Bearer $it")
                TaveApplication.authPrefs.getTokenValue("accessToken", "Bearer $it")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}