package com.example.tave.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tave.items.notice.NoticeDetailContentTxt
import com.example.tave.items.notice.NoticeDetailWriterTxt
import com.example.tave.items.notice.NoticeTitleTxt

@Composable
fun NoticeDetailPage() {
    val scrollState = rememberScrollState()
    Scaffold {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            NoticeTitleTxt(title = "후반기 프로젝트 보증금 안내")
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                NoticeDetailWriterTxt(title = "2023/06/20")
                Spacer(modifier = Modifier.width(5.dp))
                NoticeDetailWriterTxt(title = "진수빈")
            }
            Spacer(modifier = Modifier.height(10.dp))
            NoticeDetailContentTxt(title = "테-하! 안녕하세요, 경영처 기획부 김태건입니다." +
                    "이번주 토요일에 예정된 만남의 장에서는 새로운 회원분들과 소통하며 후반기 프로젝트를 기획하는...")
        }
    }
}

@Composable
@Preview
fun PreviewNoticeDetailPage() {
    val scrollState = rememberScrollState()
    Scaffold {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            NoticeTitleTxt(title = "후반기 프로젝트 보증금 안내")
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                NoticeDetailWriterTxt(title = "2023/06/20")
                Spacer(modifier = Modifier.width(5.dp))
                NoticeDetailWriterTxt(title = "진수빈")
            }
            Spacer(modifier = Modifier.height(10.dp))
            NoticeDetailContentTxt(title = "테-하! 안녕하세요, 경영처 기획부 김태건입니다." +
                    "이번주 토요일에 예정된 만남의 장에서는 새로운 회원분들과 소통하며 후반기 프로젝트를 기획하는...")
        }
    }
}