package com.example.tave.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.login.LogInBodyEntity
import com.example.domain.usecases.login.LogInUserUseCase
import com.example.tave.di.coroutineDispatcher.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val logInUserUseCase: LogInUserUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    init {
        viewModelScope.launch {
            logInUserUseCase(
                logInBodyEntity = LogInBodyEntity(
                    "member1",
                    "member1")
            ).collect {
                val result = "Barer $it"
                Log.d("result", "result")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}