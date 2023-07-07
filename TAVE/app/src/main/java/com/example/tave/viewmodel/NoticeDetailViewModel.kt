package com.example.tave.viewmodel

import android.icu.text.SimpleDateFormat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.notice.NoticeDetailEntity
import com.example.domain.usecases.notice.GetNoticeDetailUseCase
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
class NoticeDetailViewModel @Inject constructor(
    private val getNoticeDetailUseCase: GetNoticeDetailUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val accessToken: String = TaveApplication.authPrefs.getTokenValue("accessToken", "")
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.KOREAN)
    private val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREAN)

    private val _noticeData = MutableLiveData<NoticeDetailEntity?>()

    val noticeData: LiveData<NoticeDetailEntity?> get() = _noticeData

    fun getNoticeDetail(noticeID: Int?): Job = viewModelScope.launch(ioDispatcher) {
        getNoticeDetailUseCase(accessToken, noticeID!!).collect { item ->
            when(item?.noticeType) {
                "NEWS" -> { item.title = "[뉴스] ${item.title}" }
                "GENERAL" -> { item.title = "[공지] ${item.title}" }
                "SCHEDULE" -> { item.title = "[일정] ${item.title}" }
                "REVIEW" -> { item.title = "[리뷰] ${item.title}" }
            }

            item?.createdTime = convertTimeFormat(item?.createdTime)
            item?.modifiedTime = convertTimeFormat(item?.modifiedTime)

            _noticeData.postValue(item)
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