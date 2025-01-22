package banquemisr.challenge05.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import banquemisr.challenge05.data.mapper.mapToListData
import banquemisr.challenge05.domain.models.ListMovies
import javax.inject.Inject

class PopularMoviePagingSource @Inject constructor(
    private val movieDataSource: MovieDataSource,
    private val language: String
) : PagingSource<Int, ListMovies>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListMovies> {
        val page = params.key ?: 1
        return try {
            val response = movieDataSource.getPopular(language, page)
            val movieList = response.mapToListData().movieList.orEmpty()

            LoadResult.Page(
                data = movieList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movieList.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ListMovies>): Int? {
        return state.anchorPosition
    }
}

