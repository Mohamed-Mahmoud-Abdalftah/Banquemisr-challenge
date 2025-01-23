package banquemisr.challenge05.domain.useCase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import banquemisr.challenge05.core.utils.Constants.DEFAULT_LANGUAGE

import banquemisr.challenge05.domain.models.ListMovies
import banquemisr.challenge05.domain.repositories.MovieRepository
import banquemisr.challenge05.domain.repositories.NowPlayingMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetPlayingMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val nowPlayingMoviesRepository: NowPlayingMoviesRepository
) {

    // Fetch now playing movies from API
    fun execute(language: String = DEFAULT_LANGUAGE): Flow<PagingData<ListMovies>> {
        return movieRepository.getNowPlayingMovies(language)
    }

    // Insert now playing movies into the local database
    suspend fun insertMoviesIntoDatabase(movies: List<ListMovies>) {
        nowPlayingMoviesRepository.insertAllNowPlayingMovies(movies)
    }

    // Get now playing movies from local database (Room)
//    fun getMoviesFromDatabase(): PagingSource<Int, ListMovies> {
//        return nowPlayingMoviesRepository.getMoviesNowPlaying()
//    }
    fun getMoviesFromDatabase(): Flow<PagingData<ListMovies>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,  // Customize based on your data requirements
                enablePlaceholders = false
            ),
            pagingSourceFactory = { nowPlayingMoviesRepository.getMoviesNowPlaying() }
        ).flow
    }



    // Clear all now playing movies from the local database
    suspend fun clearMoviesFromDatabase() {
        nowPlayingMoviesRepository.clearAllNowPlayingMovies()
    }

}
