package com.example.tave.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.notice.NoticeDetailEntity
import com.example.domain.usecases.notice.GetNoticeSubItemsUseCase
import com.example.domain.usecases.notice.GetNoticeMainUseCase
import com.example.tave.TaveApplication
import com.example.tave.di.qualifier.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoticeViewModel @Inject constructor(
    private val getNoticeMainUseCase: GetNoticeMainUseCase,
    private val getNoticeSubItemsUseCase: GetNoticeSubItemsUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
): ViewModel() {
    private val accessToken: String =
        TaveApplication.authPrefs.getTokenValue("accessToken", "")

    private val _noticeMainData = MutableLiveData<NoticeDetailEntity?>()
    private val _noticeSubDate = MutableLiveData<List<NoticeDetailEntity>?>()

    val noticeMainData: LiveData<NoticeDetailEntity?> get() = _noticeMainData
    val noticeSubDate: LiveData<List<NoticeDetailEntity>?> get() = _noticeSubDate

    init {
        getNoticeMainCard()
        getNoticeSubItems()
    }

    private fun getNoticeMainCard(): Job = viewModelScope.launch(ioDispatcher) {
        getNoticeMainUseCase(accessToken).collect { _noticeMainData.postValue(it) }
    }

    private fun getNoticeSubItems(): Job = viewModelScope.launch(ioDispatcher) {
        getNoticeSubItemsUseCase(accessToken).collect {
            _noticeSubDate.postValue(it?.sortedBy { items -> items.id })
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}