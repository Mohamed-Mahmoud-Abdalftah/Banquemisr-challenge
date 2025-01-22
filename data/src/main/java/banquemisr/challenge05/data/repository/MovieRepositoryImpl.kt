package banquemisr.challenge05.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import banquemisr.challenge05.core.utils.Constants
import banquemisr.challenge05.core.utils.IODispatcher
import banquemisr.challenge05.data.datasource.MovieDataSource
import banquemisr.challenge05.data.datasource.NowPlayingMoviePagingSource
import banquemisr.challenge05.data.datasource.PopularMoviePagingSource
import banquemisr.challenge05.data.datasource.UpcomingMoviePagingSource
import banquemisr.challenge05.domain.models.ListMovies
import banquemisr.challenge05.domain.repositories.MovieRepository
import banquemisr.challenge05.home.domian.models.MovieDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MovieRepositoryImpl @Inject constructor(
    private val dataSource: MovieDataSource,
    @IODispatcher private val dispatcher: CoroutineContext
) : MovieRepository {

    override fun getNowPlayingMovies(language: String): Flow<PagingData<ListMovies>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.MAX_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { NowPlayingMoviePagingSource(dataSource, language) }
        ).flow
    }

    override fun getPopularMovies(language: String): Flow<PagingData<ListMovies>> =
        Pager(
            config = PagingConfig(pageSize = Constants.MAX_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { PopularMoviePagingSource(dataSource, language) }
        ).flow.flowOn(dispatcher)

    override fun getUpcomingMovies(language: String): Flow<PagingData<ListMovies>> =
        Pager(
            config = PagingConfig(pageSize = Constants.MAX_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { UpcomingMoviePagingSource(dataSource, language) }
        ).flow.flowOn(dispatcher)

    override suspend fun getMovieDetails(movieId: Int): MovieDetail {
        // Implement method to retrieve movie details
        return dataSource.getMovieDetails(movieId)
    }
}
