package banquemisr.challenge05.data.datasource

import banquemisr.challenge05.core.utils.Constants.DEFAULT_LANGUAGE
import banquemisr.challenge05.data.model.MovieDetails
import banquemisr.challenge05.data.model.MovieResponse


interface MovieDataSource {

    suspend fun getNowPlaying(
        language: String = DEFAULT_LANGUAGE,
        page: Int = 1
    ): MovieResponse

    suspend fun getPopular(
        language: String = DEFAULT_LANGUAGE,
        page: Int = 1
    ): MovieResponse

    suspend fun getUpcoming(
        language: String = DEFAULT_LANGUAGE,
        page: Int = 1
    ): MovieResponse

    suspend fun getMovieDetails(
        movieId: Int
    ): MovieDetails


}
