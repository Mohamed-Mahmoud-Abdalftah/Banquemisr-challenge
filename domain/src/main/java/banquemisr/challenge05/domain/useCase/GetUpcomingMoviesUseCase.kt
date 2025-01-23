package banquemisr.challenge05.domain.useCase

import androidx.paging.PagingData
import androidx.paging.PagingSource
import banquemisr.challenge05.core.utils.Constants.DEFAULT_LANGUAGE
import banquemisr.challenge05.domain.models.ListMovies
import banquemisr.challenge05.domain.repositories.MovieRepository
import banquemisr.challenge05.domain.repositories.UpcomingMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetUpcomingMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val  upcomingMoviesRepository: UpcomingMoviesRepository
) {
    // Fetch now playing movies from API
    fun execute(language: String = DEFAULT_LANGUAGE): Flow<PagingData<ListMovies>> {
        return movieRepository.getUpcomingMovies(language)
    }

    // Get now playing movies from local database (Room)
    fun getMoviesFromDatabase(): PagingSource<Int, ListMovies> {
        return upcomingMoviesRepository.getUpcomingMovies()
    }

    // Insert now playing movies into the local database
    suspend fun insertMoviesIntoDatabase(movies: List<ListMovies>) {
        upcomingMoviesRepository.insertAllUpcomingMovies(movies)
    }

    // Clear all now playing movies from the local database
    suspend fun clearMoviesFromDatabase() {
        upcomingMoviesRepository.clearUpcomingMovies()
    }
}
