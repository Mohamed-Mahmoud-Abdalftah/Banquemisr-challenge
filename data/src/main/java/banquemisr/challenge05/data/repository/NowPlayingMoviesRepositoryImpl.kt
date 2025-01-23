package banquemisr.challenge05.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import banquemisr.challenge05.data.local.NowPlayingMoviesDao
import banquemisr.challenge05.data.mapper.toDomain
import banquemisr.challenge05.data.mapper.toNowPlayingEntity
import banquemisr.challenge05.domain.models.ListMovies
import banquemisr.challenge05.domain.repositories.NowPlayingMoviesRepository
import javax.inject.Inject


class NowPlayingMoviesRepositoryImpl @Inject constructor(
    private val nowPlayingMoviesDao: NowPlayingMoviesDao
) : NowPlayingMoviesRepository {

    override suspend fun insertAllNowPlayingMovies(movies: List<ListMovies>) {
        val entities = movies.map { it.toNowPlayingEntity() }
        nowPlayingMoviesDao.insertAllNowPlayingMovies(entities)
    }

    override fun getMoviesNowPlaying(): PagingSource<Int, ListMovies> {
        return object : PagingSource<Int, ListMovies>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListMovies> {
                return when (val result = nowPlayingMoviesDao.getMoviesNowPlaying().load(params)) {
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

    override suspend fun clearAllNowPlayingMovies() {
        nowPlayingMoviesDao.clearAllNowPlayingMovies()
    }
}
