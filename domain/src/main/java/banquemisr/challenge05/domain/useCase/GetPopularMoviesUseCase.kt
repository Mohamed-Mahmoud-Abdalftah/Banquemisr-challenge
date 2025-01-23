package banquemisr.challenge05.domain.useCase

import androidx.paging.PagingData
import androidx.paging.PagingSource
import banquemisr.challenge05.core.utils.Constants.DEFAULT_LANGUAGE
import banquemisr.challenge05.domain.models.ListMovies
import banquemisr.challenge05.domain.repositories.MovieRepository
import banquemisr.challenge05.domain.repositories.PopularMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetPopularMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository, private val popularMoviesRepository: PopularMoviesRepository,
) {
    // Fetch now playing movies from API
    fun execute(language: String = DEFAULT_LANGUAGE): Flow<PagingData<ListMovies>> {
        return movieRepository.getPopularMovies(language)
    }

    // Insert now playing movies into the local database
    suspend fun insertMoviesIntoDatabase(movies: List<ListMovies>) {
        popularMoviesRepository.insertAllPopularMovies(movies)
    }

    // Get now playing movies from local database (Room)
    fun getMoviesFromDatabase(): PagingSource<Int, ListMovies> {
        return popularMoviesRepository.getPopularMovies()
    }

    // Clear all now playing movies from the local database
    suspend fun clearMoviesFromDatabase() {
        popularMoviesRepository.clearPopularMovies()
    }
}
