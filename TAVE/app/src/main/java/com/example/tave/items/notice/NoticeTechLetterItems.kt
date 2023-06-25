import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tave.R
import com.example.tave.ui.theme.Shape

@Composable
fun NoticeTechLetterItems(
    painterResourceId: Int
) {
    Image(
        modifier = Modifier
            .size(150.dp)
            .clip(shape = Shape.large),
        painter = painterResource(painterResourceId),
        contentScale = ContentScale.Fit,
        contentDescription = "Sub Items Tech Letter"
    )
    Spacer(modifier = Modifier.width(10.dp))
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewNoticeTechLetterItems() {
    NoticeTechLetterItems(painterResourceId = R.drawable.tech_letter)
}