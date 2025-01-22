package banquemisr.challenge05.domain.useCase

import androidx.paging.PagingData
import banquemisr.challenge05.domain.models.ListMovies
import banquemisr.challenge05.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetPlayingMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    fun invoke(language: String = "en-US"): Flow<PagingData<ListMovies>> {
        return movieRepository.getNowPlayingMovies(language)
    }
}
