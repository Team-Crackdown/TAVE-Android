package com.example.tave.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.login.LogInBodyEntity
import com.example.domain.usecases.login.LogInUserUseCase
import com.example.domain.usecases.profile.GetCheckedSMSUseCase
import com.example.tave.TaveApplication
import com.example.tave.common.Constants
import com.example.tave.common.util.state.LogInUserState
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
class LogInViewModel @Inject constructor(
    private val logInUserUseCase: LogInUserUseCase,
    private val getCheckedSMSUseCase: GetCheckedSMSUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _logInState = MutableStateFlow<LogInUserState>(LogInUserState.Idle)
    val logInState: StateFlow<LogInUserState> = _logInState.asStateFlow()

    fun userLogInAccount(
        userEmail: String,
        userPassword: String
    ): Job = viewModelScope.launch(ioDispatcher) {
        _logInState.value = LogInUserState.IsLoading

        logInUserUseCase(LogInBodyEntity(userEmail, userPassword)).collect { token ->
            if (token != null) {
                TaveApplication.authPrefs.setTokenValue(Constants.ACCESS_TOKEN_TITLE, "Bearer $token")
                getCheckSMSField("Bearer $token")
            } else {
                _logInState.value = LogInUserState.IsFailed(Result.failure(Exception()))
                delay(2000L)
                _logInState.value = LogInUserState.Idle
            }
        }
    }

    private fun getCheckSMSField(accessToken: String): Job = viewModelScope.launch(ioDispatcher) {
        getCheckedSMSUseCase(accessToken).collect {
            if (it) {
                _logInState.value = LogInUserState.IsSuccess(Result.success(Unit))
            } else {
                _logInState.value = LogInUserState.IsSMSCheckNeeded(Result.success(Unit))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}