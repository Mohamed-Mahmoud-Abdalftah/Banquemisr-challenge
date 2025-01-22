package banquemisr.challenge05.domain.useCase

import androidx.paging.PagingData
import banquemisr.challenge05.domain.models.ListData
import banquemisr.challenge05.domain.models.ListMovies
import banquemisr.challenge05.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//class GetUpcomingMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
//    suspend operator fun invoke(language: String = "en-US", page: Int = 1): Flow<ListData> {
//        return movieRepository.getUpcomingMovies(language, page)
//    }

class GetUpcomingMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    fun invoke(language: String = "en-US"): Flow<PagingData<ListMovies>> {
        return movieRepository.getUpcomingMovies(language)
    }
}
