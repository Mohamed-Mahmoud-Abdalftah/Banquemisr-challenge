package banquemisr.challenge05.domain.repositories

import androidx.paging.PagingData
import banquemisr.challenge05.domain.models.ListMovies
import banquemisr.challenge05.home.domian.models.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getNowPlayingMovies(language: String): Flow<PagingData<ListMovies>>

    fun getPopularMovies(language: String): Flow<PagingData<ListMovies>>
    fun getUpcomingMovies(language: String): Flow<PagingData<ListMovies>>

    // Single movie retrieval (no paging)
    suspend fun getMovieDetails(movieId: Int): MovieDetail
}
