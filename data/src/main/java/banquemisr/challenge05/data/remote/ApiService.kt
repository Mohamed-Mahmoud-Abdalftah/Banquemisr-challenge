package banquemisr.challenge05.data.remote

import banquemisr.challenge05.core.utils.Constants.DEFAULT_LANGUAGE
import banquemisr.challenge05.data.model.MovieDetails
import banquemisr.challenge05.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String = DEFAULT_LANGUAGE,
        @Query("page") page: Int = 1
    ): Response<MovieResponse>

    @GET("popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = DEFAULT_LANGUAGE,
        @Query("page") page: Int = 1
    ): Response<MovieResponse>

    @GET("upcoming")
    suspend fun getUpcomingMovies(
        @Query("language") language: String = DEFAULT_LANGUAGE,
        @Query("page") page: Int = 1
    ): Response<MovieResponse>

    @GET("{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): Response<MovieDetails>


}
