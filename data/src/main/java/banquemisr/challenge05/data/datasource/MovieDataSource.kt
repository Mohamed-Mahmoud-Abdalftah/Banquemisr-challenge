package banquemisr.challenge05.data.datasource

import banquemisr.challenge05.data.model.MovieResponse
import banquemisr.challenge05.home.domian.models.MovieDetail


interface MovieDataSource {
    suspend fun getNowPlaying(language: String, page: Int): MovieResponse
    suspend fun getPopular(language: String, page: Int): MovieResponse
    suspend fun getUpcoming(language: String, page: Int): MovieResponse
    suspend fun getMovieDetails( page: Int): MovieDetail
}