package com.example.tave.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shape = Shapes(
    extraSmall = RoundedCornerShape(0.5.dp),
    small = RoundedCornerShape(1.dp),
    medium = RoundedCornerShape(3.dp),
    large = RoundedCornerShape(5.dp),
    extraLarge = RoundedCornerShape(7.dp)
)

val CustomShape = Shapes(
    extraLarge = RoundedCornerShape(
        topStart = 7.dp,
        bottomStart = 7.dp,
    )
)

val NoticeImageCardCustomShape = Shapes(
    extraLarge = RoundedCornerShape(
        bottomStart = 7.dp,
        bottomEnd = 7.dp
    )
)

val ProfileCustomShape = Shapes(
    extraLarge = RoundedCornerShape(
        bottomStart = 20.dp,
        bottomEnd = 20.dp
    )
)

val ProfileImageCustomShape = Shapes(
    extraLarge = RoundedCornerShape(
        topStart = 25.dp,
        topEnd = 25.dp,
        bottomStart = 25.dp,
        bottomEnd = 25.dp
    )
)