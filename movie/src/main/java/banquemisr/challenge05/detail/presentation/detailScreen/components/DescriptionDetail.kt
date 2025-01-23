package banquemisr.challenge05.detail.presentation.detailScreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import banquemisr.challenge05.them.MovieTypography


@Composable
fun DescriptionDetail(overview: String?) {
    Text(
        text =overview.orEmpty(),  modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        style = MovieTypography.bodyMedium,
        textAlign = TextAlign.Center
    )
}