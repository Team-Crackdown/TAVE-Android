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
        topEnd = 0.dp,
        bottomStart = 7.dp,
        bottomEnd = 0.dp
    )
)