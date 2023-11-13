package com.example.tave.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.login.ModifyPasswordEntity
import com.example.domain.usecases.login.ModifyPasswordUseCase
import com.example.tave.TaveApplication
import com.example.tave.common.Constants
import com.example.tave.common.util.state.InitPasswordState
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
class InitPWDViewModel @Inject constructor(
    private val updateMemberPasswordUseCase: ModifyPasswordUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _isPasswordChanged = MutableStateFlow<InitPasswordState>(InitPasswordState.Idle)
    val isPasswordChanged: StateFlow<InitPasswordState> = _isPasswordChanged.asStateFlow()

    private val accessToken: String =
        TaveApplication.authPrefs.getTokenValue(Constants.ACCESS_TOKEN_TITLE, "")

    fun validatePWD(
        password: String,
        confirmPassword: String
    ) {
        when {
            password.length < 8 -> {
                _isPasswordChanged.value = InitPasswordState.IsFailed(Result.failure(Exception()))
            }
            password.length > 15 -> {
                _isPasswordChanged.value = InitPasswordState.IsFailed(Result.failure(Exception()))
            }
            password != confirmPassword -> {
                _isPasswordChanged.value = InitPasswordState.IsFailed(Result.failure(Exception()))
            }
            else -> {
                changePassword(confirmPassword)
            }
        }
    }

    private fun changePassword(password: String): Job = viewModelScope.launch(ioDispatcher) {
        _isPasswordChanged.value = InitPasswordState.IsLoading

        updateMemberPasswordUseCase(
            accessToken,
            ModifyPasswordEntity(password, true)
        ).collect {
            if (it.isSuccess) {
                _isPasswordChanged.value = InitPasswordState.IsComplete(Result.success(Unit))
            } else {
                _isPasswordChanged.value = InitPasswordState.IsFailed(Result.failure(Exception()))
                delay(2000)
                _isPasswordChanged.value = InitPasswordState.Idle
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}