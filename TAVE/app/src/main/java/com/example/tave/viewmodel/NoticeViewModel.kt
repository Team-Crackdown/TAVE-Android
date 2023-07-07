package com.example.tave.viewmodel

import android.icu.text.SimpleDateFormat
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
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class NoticeViewModel @Inject constructor(
    private val getNoticeMainUseCase: GetNoticeMainUseCase,
    private val getNoticeSubItemsUseCase: GetNoticeSubItemsUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
): ViewModel() {
    private val accessToken: String =
        TaveApplication.authPrefs.getTokenValue("accessToken", "")
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.KOREAN)
    private val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREAN)

    private val _noticeMainData = MutableLiveData<NoticeDetailEntity?>()
    private val _noticeSubDate = MutableLiveData<List<NoticeDetailEntity>?>()

    val noticeMainData: LiveData<NoticeDetailEntity?> get() = _noticeMainData
    val noticeSubDate: LiveData<List<NoticeDetailEntity>?> get() = _noticeSubDate

    init {
        getNoticeMainCard()
        getNoticeSubItems()
    }

    private fun getNoticeMainCard(): Job = viewModelScope.launch(ioDispatcher) {
        getNoticeMainUseCase(accessToken).collect { item ->
            when(item?.noticeType) {
                "NEWS" -> { item.title = "[뉴스] ${item.title}" }
                "GENERAL" -> { item.title = "[공지] ${item.title}" }
                "SCHEDULE" -> { item.title = "[일정] ${item.title}" }
                "REVIEW" -> { item.title = "[리뷰] ${item.title}" }
            }

            item?.createdTime = convertTimeFormat(item?.createdTime)
            item?.modifiedTime = convertTimeFormat(item?.modifiedTime)



            _noticeMainData.postValue(item)
        }
    }

    private fun getNoticeSubItems(): Job = viewModelScope.launch(ioDispatcher) {
        getNoticeSubItemsUseCase(accessToken).collect {
            it?.forEach { items ->
                when(items.noticeType) {
                    "NEWS" -> { items.title = "[뉴스] ${items.title}" }
                    "GENERAL" -> { items.title = "[공지] ${items.title}" }
                    "SCHEDULE" -> { items.title = "[일정] ${items.title}" }
                    "REVIEW" -> { items.title = "[리뷰] ${items.title}" }
                }

                items.createdTime = convertTimeFormat(items.createdTime)
                items.modifiedTime = convertTimeFormat(items.modifiedTime)
            }

            _noticeSubDate.postValue(it?.sortedBy { items -> items.id }?.reversed())
        }
    }

    private fun convertTimeFormat(
        time: String?
    ): String = outputFormat.format(dateFormat.parse(time))

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}