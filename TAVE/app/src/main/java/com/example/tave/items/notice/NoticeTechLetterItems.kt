import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tave.items.glide.GlideImageView
import com.example.tave.R


@Composable
fun NoticeTechLetterItems(
    modifier: Modifier,
    imageUrl: () -> Unit
) {
    GlideImageView(
        modifier = modifier.size(150.dp), 
        imageUrl = imageUrl, 
        contentDescription = "notice tech letter item",
        painterResource = R.drawable.tech_letter
    )
}