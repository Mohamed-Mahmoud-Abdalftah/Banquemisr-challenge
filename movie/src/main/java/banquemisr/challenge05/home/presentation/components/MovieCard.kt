package banquemisr.challenge05.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import banquemisr.challenge05.core.components.CoilImageComponent
import banquemisr.challenge05.core.utils.Constants.IMAGE_URL
import banquemisr.challenge05.domain.models.ListMovies

@Composable
fun ProductCard(movieItem: ListMovies, onItemClick: (Int) -> Unit) {
    Card(onClick = { onItemClick(movieItem.id) },
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(250.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            MovieImage(movieItem.posterPath)
            MovieTitle(movieItem.title)
            MovieDate(movieItem.releaseDate)
        }
    }
}

@Composable
private fun MovieImage(image: String) {
    CoilImageComponent(
        imageUrl = IMAGE_URL + image,
        contentScale = ContentScale.FillBounds,
        contentDescription = "Movie poster",
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 190.dp)
    )
}

@Composable
private fun MovieTitle(title: String) {
    Text(
        modifier = Modifier.padding(horizontal = 8.dp),
        textAlign = TextAlign.Center,
        text = title,
        maxLines = 1,
        minLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = TextStyle(
            fontSize = typography.titleSmall.fontSize,
            fontWeight = typography.titleSmall.fontWeight,
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = typography.titleSmall.fontFamily
        )
    )
}

@Composable
private fun MovieDate(releaseDate: String) {
    Text(
        text = releaseDate, style = TextStyle(
            fontSize = typography.labelSmall.fontSize,
            fontWeight = typography.labelSmall.fontWeight,
            color = MaterialTheme.colorScheme.primary,
            fontFamily = typography.labelSmall.fontFamily
        )
    )
}
