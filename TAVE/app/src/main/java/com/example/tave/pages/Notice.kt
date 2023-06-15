package com.example.tave.pages

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tave.items.notice.NoticeCard
import com.example.tave.items.notice.NoticeTitleTxt

class Notice(val title: String, val content: String)

val notices = mutableListOf<Notice>(
    Notice(title = "후반기 프로젝트 보증금 안내", content = "테-하! 안녕하세요, 경영처 회계부 진수빈입니다.\n" +
            "후반기 프로젝트 보증금 안내를 드리고자합니다."),
    Notice(title = "후반기 만남의 장 회원 소개 part2", content = "테-하! 안녕하세요, 경영처장 박정재입니다.\n" +
            "이어서 금일은 회원 소개 2탄으로 돌아왔습니다."),
    Notice(title = "후반기 만남의 장 회원 소개 part1", content = "테-하! 안녕하세요, 경영처 기획부 김태건입니다.\n" +
            "이번주 토요일에 예정된 만남의 장에서는 새로운 회원분들과 소통하며 후반기 프로젝트를 기획하는...")
)



@Composable
fun NoticePage(context: Context, navController: NavController) {
    Scaffold{
        Column(modifier = Modifier.padding(24.dp)) {
            NoticeTitleTxt(title = "공지사항")
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn{
                items(notices.size) {index ->
                    NoticeCard(title = notices[index].title, content = notices[index].content)
                }
            }
        }
    }
}