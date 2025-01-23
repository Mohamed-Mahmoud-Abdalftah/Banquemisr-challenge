package banquemisr.challenge05.domain.useCase

import banquemisr.challenge05.domain.repositories.MovieRepository
import banquemisr.challenge05.domain.models.MovieDetail
import javax.inject.Inject


class MovieDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {

    suspend fun execute(movieId:Int): MovieDetail {
        return movieRepository.getMovieDetails(movieId)
    }

}
