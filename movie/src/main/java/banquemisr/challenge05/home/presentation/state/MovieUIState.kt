package banquemisr.challenge05.home.presentation.state

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import banquemisr.challenge05.domain.models.ListMovies


@Immutable
data class MovieUIState( // can be sealed class
    val isLoading: Boolean = false,
    val moviesData: PagingData<ListMovies> = PagingData.empty(),
    val error: Throwable? = null,
    val selectedMovieItemID: Int? = null
)