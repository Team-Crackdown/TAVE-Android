package com.example.tave.items.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.tave.ui.font.NotoSansKr

@Composable
fun ProfileContentTxtSizeLarge(txt: String, answer: String) {
    val constraints = decoupledConstraints(
        widthTxt = Dimension.value(100.dp),
        widthAnswer = Dimension.value(150.dp)
    )

    ConstraintLayout(constraints) {
        Text(
            text = txt,
            modifier = Modifier.layoutId("txt"),
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = NotoSansKr,
                fontWeight = FontWeight.Bold,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
                lineHeight = 2.5.em
            )
        )
        Text(
            text = answer,
            modifier = Modifier.layoutId("answer"),
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = NotoSansKr,
                fontWeight = FontWeight.Bold,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
                lineHeight = 2.5.em
            )
        )
    }
}

@Composable
fun ProfileContentTxtSizeSmall(txt: String, answer: String) {
    val constraints = decoupledConstraints(
        widthTxt = Dimension.value(100.dp),
        widthAnswer = Dimension.value(150.dp)
    )

    ConstraintLayout(constraints) {
        Text(
            text = txt,
            modifier = Modifier.layoutId("txt"),
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = NotoSansKr,
                fontWeight = FontWeight.Bold,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
                lineHeight = 2.5.em
            )
        )
        Text(
            text = answer,
            modifier = Modifier.layoutId("answer"),
            style = TextStyle(
                fontSize = 10.sp,
                fontFamily = NotoSansKr,
                fontWeight = FontWeight.Bold,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
                lineHeight = 2.5.em
            )
        )
    }
}


private fun decoupledConstraints(
    widthTxt: Dimension,
    widthAnswer: Dimension
): ConstraintSet {
    return ConstraintSet {
        val txt = createRefFor("text")
        val answer = createRefFor("answer")

        constrain(txt) {
            top.linkTo(parent.top, margin = 20.dp)
            width = widthTxt
        }
        constrain(answer) {
            top.linkTo(txt.bottom, margin = 20.dp)
            start.linkTo(txt.end)
            width = widthAnswer
        }
    }
}
