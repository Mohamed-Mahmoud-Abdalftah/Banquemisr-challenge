package banquemisr.challenge05.detail.presentation.detailScreen.state

import androidx.compose.runtime.Immutable
import banquemisr.challenge05.domain.models.MovieDetail


@Immutable
data class DetailUIState(
    val isLoading: Boolean = false,
    val moviesData: MovieDetail? =null,
    val error: Throwable? = null
)