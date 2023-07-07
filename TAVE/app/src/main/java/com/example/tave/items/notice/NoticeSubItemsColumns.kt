package com.example.tave.items.notice

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.domain.entity.notice.NoticeDetailEntity

@Composable
fun NoticeSubItemsColumns(
    modifier: Modifier,
    onItemClick: (Int) -> Unit,
    noticeSubItems: List<NoticeDetailEntity>?
) {
    val admin: String = if (noticeSubItems != null) { "테이브 운영진" } else { "" }

    LazyColumn {
        noticeSubItems?.size?.let {
            items(it) { index ->
                NoticeSubItems(
                    modifier = modifier,
                    subItemTitle = "${noticeSubItems[index].title}",
                    subItemWriter = admin,
                    subItemTimeStamp = "${noticeSubItems[index].createdTime}분 경 업로드",
                    imageUrl = "",
                    onClick = { onItemClick(noticeSubItems[index].id) },
                    index = index
                )
                Spacer(modifier = modifier.height(5.dp))
                Divider(
                    color = Color.Black.copy(alpha = 0.2f),
                    modifier = modifier.fillMaxWidth()
                )
            }
        }
    }
}