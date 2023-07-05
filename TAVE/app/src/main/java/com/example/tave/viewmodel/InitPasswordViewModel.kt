package com.example.tave.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.login.PasswordModifyEntity
import com.example.domain.usecases.login.UpdateMemberPasswordUseCase
import com.example.tave.TaveApplication
import com.example.tave.di.qualifier.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InitPasswordViewModel @Inject constructor(
    private val updateMemberPasswordUseCase: UpdateMemberPasswordUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _isNotMatchPassword = MutableLiveData<Boolean>()
    private val _isChangedComplete = MutableLiveData<Result<Unit>>()
    val isNotMatchPassword: LiveData<Boolean> get() = _isNotMatchPassword
    val isChangedComplete: LiveData<Result<Unit>> get() = _isChangedComplete

    private val accessToken: String = TaveApplication.authPrefs.getTokenValue("accessToken", "")

    fun validateConfirmPassword(
        password: String,
        confirmPassword: String
    ) {
        if(password != confirmPassword) {
            _isNotMatchPassword.value = false
        } else {
            _isNotMatchPassword.value = true
            changePassword(password)
        }
    }

    private fun changePassword(password: String): Job = viewModelScope.launch(ioDispatcher) {
        updateMemberPasswordUseCase(
            accessToken,
            PasswordModifyEntity(password, true)
        ).collect { _isChangedComplete.postValue(it) }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}