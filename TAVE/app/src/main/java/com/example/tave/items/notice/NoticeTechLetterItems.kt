import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tave.R
import com.example.tave.ui.theme.Shape


@Composable
fun NoticeTechLetterItems(modifier: Modifier) {
    Image(
        modifier = modifier
            .size(150.dp)
            .clip(shape = Shape.large),
        painter = painterResource(id = R.drawable.tech_letter),
        contentDescription = "notice tech letter item"
    )
}