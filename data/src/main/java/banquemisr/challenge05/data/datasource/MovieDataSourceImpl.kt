package banquemisr.challenge05.data.datasource

import banquemisr.challenge05.data.model.MovieResponse
import banquemisr.challenge05.data.remote.ApiService
import banquemisr.challenge05.core.utils.extensions.handleCall
import banquemisr.challenge05.home.domian.models.MovieDetail
import javax.inject.Inject

 class MovieDataSourceImpl @Inject constructor(
    private val api: ApiService
) : MovieDataSource {
    override suspend fun getNowPlaying(language: String, page: Int): MovieResponse {
        return handleCall {
            api.getNowPlayingMovies(language,page)
        }
    }

    override suspend fun getPopular(language: String, page: Int): MovieResponse {
        return handleCall {
            api.getPopularMovies(language,page)
        }
    }

    override suspend fun getUpcoming(language: String, page: Int): MovieResponse {
        return handleCall {
            api.getUpcomingMovies(language,page)
        }
    }

     override suspend fun getMovieDetails(page: Int): MovieDetail {
         TODO("Not yet implemented")
     }

 }