package com.example.tave.viewmodel

import android.icu.text.SimpleDateFormat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.notice.NoticeDetailEntity
import com.example.domain.entity.notice.NoticeTypeEnumClass
import com.example.domain.entity.notice.noticeTypeMap
import com.example.domain.usecases.notice.GetNoticeSubItemsUseCase
import com.example.domain.usecases.notice.GetNoticeMainUseCase
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
class NoticeViewModel @Inject constructor(
    private val getNoticeMainUseCase: GetNoticeMainUseCase,
    private val getNoticeSubItemsUseCase: GetNoticeSubItemsUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
): ViewModel() {
    private val accessToken: String =
        TaveApplication.authPrefs.getTokenValue(Constants.ACCESS_TOKEN_TITLE, "")
    private val dateFormat = SimpleDateFormat(Constants.SERVER_DATE_TIME_FORMAT, Locale.KOREAN)
    private val outputFormat = SimpleDateFormat(Constants.OUTPUT_DATE_TIME_FORMAT, Locale.KOREAN)

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

            _noticeMainData.postValue(item)
        }
    }

    private fun getNoticeSubItems(): Job = viewModelScope.launch(ioDispatcher) {
        getNoticeSubItemsUseCase(accessToken).collect {
            it?.forEach { items ->
                when(items.noticeType) {
                    NoticeTypeEnumClass.NEWS.name ->
                        items.title = noticeTypeMap[NoticeTypeEnumClass.NEWS.name] + items.title

                    NoticeTypeEnumClass.GENERAL.name ->
                        items.title = noticeTypeMap[NoticeTypeEnumClass.GENERAL.name] + items.title

                    NoticeTypeEnumClass.SCHEDULE.name ->
                        items.title = noticeTypeMap[NoticeTypeEnumClass.SCHEDULE.name] + items.title

                    NoticeTypeEnumClass.REVIEW.name ->
                        items.title = noticeTypeMap[NoticeTypeEnumClass.REVIEW.name] + items.title

                    NoticeTypeEnumClass.TECH.name ->
                        items.title = noticeTypeMap[NoticeTypeEnumClass.TECH.name] + items.title
                }
                items.images = items.images.ifEmpty { items.images }
                items.createdTime = convertTimeFormat(items.createdTime)
                items.modifiedTime = convertTimeFormat(items.modifiedTime)
            }

            _noticeSubDate.postValue(it)
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