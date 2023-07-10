package com.example.tave.items.profile

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tave.R
import com.example.tave.common.util.standardQuadFromTo
import com.example.tave.items.glide.GlideImageView
import com.example.tave.ui.font.NotoSansKr
import com.example.tave.ui.theme.DarkBlue
import com.example.tave.ui.theme.MidBlue
import com.example.tave.ui.theme.ProfileCustomShape
import com.example.tave.ui.theme.ProfileImageCustomShape
import com.example.tave.ui.theme.SeaBlue

@Composable
fun ProfileBox(
    modifier: Modifier,
    userName: String?,
    userProfileImage: String
) {
    BoxWithConstraints(
        modifier = modifier
            .clip(ProfileCustomShape.extraLarge)
            .background(SeaBlue),
        content = {
            val height = constraints.maxHeight
            val width = constraints.maxWidth

            val mediumColoredPoint1 = Offset(0f, height * 0.3f)
            val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
            val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
            val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
            val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

            val mediumColoredPath = Path().apply {
                moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
                standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
                standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
                standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
                standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
                lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
                lineTo(-100f, height.toFloat() + 100f)
                close()
            }

            val lightPoint1 = Offset(0f, height * 0.3f)
            val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
            val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
            val lightPoint4 = Offset(width * 0.65f, height.toFloat())
            val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

            val lightColoredPath = Path().apply {
                moveTo(lightPoint1.x, lightPoint1.y)
                standardQuadFromTo(lightPoint1, lightPoint2)
                standardQuadFromTo(lightPoint2, lightPoint3)
                standardQuadFromTo(lightPoint3, lightPoint4)
                standardQuadFromTo(lightPoint4, lightPoint5)
                lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
                lineTo(-100f, height.toFloat() + 100f)
                close()
            }
            Canvas(
                modifier = modifier
                    .fillMaxWidth()
                    .height(height.dp)
            ) {
                drawPath(
                    path = mediumColoredPath,
                    color = MidBlue
                )
                drawPath(
                    path = lightColoredPath,
                    color = DarkBlue
                )
            }
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .height(height.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GlideImageView(
                    modifier = Modifier
                        .size(140.dp)
                        .clip(ProfileImageCustomShape.extraLarge),
                    imageUrl = userProfileImage,
                    painterResource = R.drawable.profile_default
                )
                Spacer(modifier = Modifier.size(30.dp))
                Text(
                    text = userName ?: "",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 25.sp,
                        fontFamily = NotoSansKr,
                        fontWeight = FontWeight.SemiBold,
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    )
                )
            }
        }
    )
}
