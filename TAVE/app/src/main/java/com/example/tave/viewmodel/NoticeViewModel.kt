package com.example.tave.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.notice.GetNoticeAllUseCase
import com.example.tave.di.coroutineDispatcher.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.cancel
import javax.inject.Inject

@HiltViewModel
class NoticeViewModel @Inject constructor(
    private val noticeAllUseCase: GetNoticeAllUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {



    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}