package com.example.tave.items.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

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
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = answer,
            modifier = Modifier.layoutId("answer"),
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
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
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = answer,
            modifier = Modifier.layoutId("answer"),
            fontSize = 10.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ProfileContentTxtSizeOnlyForIntro(txt: String, answer: String) {
    val constraints = decoupledConstraints(
        widthTxt = Dimension.value(316.dp),
        widthAnswer = Dimension.value(316.dp)
    )

    ConstraintLayout(constraints) {
        Text(
            text = txt,
            modifier = Modifier.layoutId("txt"),
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = answer,
            modifier = Modifier.layoutId("answer"),
            fontSize = 10.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )
    }
}

private fun decoupledConstraints(widthTxt: Dimension, widthAnswer: Dimension): ConstraintSet {
    return ConstraintSet {
        val txt = createRefFor("text")
        val answer = createRefFor("answer")

        constrain(txt) {
            top.linkTo(parent.top, margin = 20.dp)
            width = widthTxt
            // height = Dimension.value(50.dp)
        }
        constrain(answer) {
            top.linkTo(txt.bottom, margin = 20.dp)
            start.linkTo(txt.end)
            width = widthAnswer
            // height = Dimension.value(50.dp)
        }
    }
}
