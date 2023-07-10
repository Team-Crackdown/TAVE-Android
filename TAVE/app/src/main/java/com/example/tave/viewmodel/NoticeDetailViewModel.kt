package com.example.tave.viewmodel

import android.icu.text.SimpleDateFormat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.notice.NoticeDetailEntity
import com.example.domain.entity.notice.NoticeTypeEnumClass
import com.example.domain.entity.notice.noticeTypeMap
import com.example.domain.usecases.notice.GetNoticeDetailUseCase
import com.example.tave.TaveApplication
import com.example.tave.common.Constants
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
    private val accessToken: String =
        TaveApplication.authPrefs.getTokenValue("accessToken", "")
    private val dateFormat = SimpleDateFormat(Constants.SERVER_DATE_TIME_FORMAT, Locale.KOREAN)
    private val outputFormat = SimpleDateFormat(Constants.OUTPUT_DATE_TIME_FORMAT, Locale.KOREAN)

    private val _noticeData = MutableLiveData<NoticeDetailEntity?>()

    val noticeData: LiveData<NoticeDetailEntity?> get() = _noticeData

    fun getNoticeDetail(noticeID: Int?): Job = viewModelScope.launch(ioDispatcher) {
        getNoticeDetailUseCase(accessToken, noticeID!!).collect { item ->
            when(item?.noticeType) {
                NoticeTypeEnumClass.NEWS.name ->
                    item.title = noticeTypeMap[NoticeTypeEnumClass.NEWS.name] + item.title

                NoticeTypeEnumClass.GENERAL.name ->
                    item.title = noticeTypeMap[NoticeTypeEnumClass.GENERAL.name] + item.title

                NoticeTypeEnumClass.SCHEDULE.name ->
                    item.title = noticeTypeMap[NoticeTypeEnumClass.SCHEDULE.name] + item.title

                NoticeTypeEnumClass.REVIEW.name ->
                    item.title = noticeTypeMap[NoticeTypeEnumClass.REVIEW.name] + item.title

                NoticeTypeEnumClass.TECH.name ->
                    item.title = noticeTypeMap[NoticeTypeEnumClass.TECH.name] + item.title
            }
            item?.images = if (item?.images?.isEmpty() == true) { item.images } else { item?.images!! }
            item.createdTime = convertTimeFormat(item.createdTime)
            item.modifiedTime = convertTimeFormat(item.modifiedTime)

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