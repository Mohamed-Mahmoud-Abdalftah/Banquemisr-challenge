package banquemisr.challenge05.domain.repositories

import androidx.paging.PagingSource
import banquemisr.challenge05.domain.models.ListMovies


interface NowPlayingMoviesRepository {
    suspend fun insertAllNowPlayingMovies(movies: List<ListMovies>)
    fun getMoviesNowPlaying(): PagingSource<Int, ListMovies>
    suspend fun clearAllNowPlayingMovies()
}


