package banquemisr.challenge05.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import banquemisr.challenge05.data.local.UpcomingMoviesDao
import banquemisr.challenge05.data.mapper.toDomain
import banquemisr.challenge05.data.mapper.toUpcomingEntity
import banquemisr.challenge05.domain.models.ListMovies
import banquemisr.challenge05.domain.repositories.UpcomingMoviesRepository
import javax.inject.Inject


class UpcomingMoviesRepositoryImpl @Inject constructor(
    private val upcomingMoviesDao: UpcomingMoviesDao
) : UpcomingMoviesRepository {

    override suspend fun insertAllUpcomingMovies(movies: List<ListMovies>) {
        val entities = movies.map { it.toUpcomingEntity() }
        upcomingMoviesDao.insertAllUpcomingMovies(entities)
    }

    override fun getUpcomingMovies(): PagingSource<Int, ListMovies> {
        return object : PagingSource<Int, ListMovies>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListMovies> {
                return when (val result = upcomingMoviesDao.getUpcomingMovies().load(params)) {
                    is LoadResult.Page -> LoadResult.Page(
                        data = result.data.map { it.toDomain() },
                        prevKey = result.prevKey,
                        nextKey = result.nextKey
                    )
                    is LoadResult.Error -> LoadResult.Error(result.throwable)
                    is LoadResult.Invalid -> LoadResult.Invalid()
                }
            }

            override fun getRefreshKey(state: PagingState<Int, ListMovies>): Int? {
                return state.anchorPosition
            }
        }
    }

    override suspend fun clearUpcomingMovies() {
        upcomingMoviesDao.clearUpcomingMovies()
    }


}
