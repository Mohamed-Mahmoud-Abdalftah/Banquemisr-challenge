package banquemisr.challenge05.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import banquemisr.challenge05.data.local.PopularMoviesDao
import banquemisr.challenge05.data.mapper.toDomain
import banquemisr.challenge05.data.mapper.toPopularEntity
import banquemisr.challenge05.domain.models.ListMovies
import banquemisr.challenge05.domain.repositories.PopularMoviesRepository
import javax.inject.Inject


class PopularMoviesRepositoryImpl @Inject constructor(
    private val popularMoviesDao: PopularMoviesDao
) : PopularMoviesRepository {

    override suspend fun insertAllPopularMovies(movies: List<ListMovies>) {
        val entities = movies.map { it.toPopularEntity() }
        popularMoviesDao.insertAllPopularMovies(entities)
    }

    override fun getPopularMovies(): PagingSource<Int, ListMovies> {
        return object : PagingSource<Int, ListMovies>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListMovies> {
                return when (val result = popularMoviesDao.getPopularMovies().load(params)) {
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

    override suspend fun clearPopularMovies() {
        popularMoviesDao.clearPopularMovies()
    }

}
