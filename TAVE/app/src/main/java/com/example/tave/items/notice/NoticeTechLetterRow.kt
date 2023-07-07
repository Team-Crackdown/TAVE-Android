package com.example.tave.items.notice

import NoticeTechLetterItems
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tave.pages.techLetters

@Composable
fun NoticeTechLetterRow(
    modifier: Modifier
) {
    LazyRow {
        items(count = techLetters.size) {
            NoticeTechLetterItems(modifier = modifier)
            Spacer(modifier = modifier.width(5.dp))
        }
    }
}