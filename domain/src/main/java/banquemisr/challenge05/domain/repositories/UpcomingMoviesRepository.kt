package banquemisr.challenge05.domain.repositories

import androidx.paging.PagingSource
import banquemisr.challenge05.domain.models.ListMovies

interface UpcomingMoviesRepository {
    suspend fun insertAllUpcomingMovies(movies: List<ListMovies>)
    fun getUpcomingMovies(): PagingSource<Int, ListMovies>
    suspend fun clearUpcomingMovies()
}