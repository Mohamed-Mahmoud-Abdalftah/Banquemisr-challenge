package banquemisr.challenge05.domain.repositories

import androidx.paging.PagingSource
import banquemisr.challenge05.domain.models.ListMovies

interface PopularMoviesRepository {
    suspend fun insertAllPopularMovies(movies: List<ListMovies>)
    fun getPopularMovies(): PagingSource<Int, ListMovies>
    suspend fun clearPopularMovies()
}