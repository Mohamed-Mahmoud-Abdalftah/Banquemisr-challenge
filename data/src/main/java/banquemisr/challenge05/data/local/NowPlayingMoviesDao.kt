package banquemisr.challenge05.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import banquemisr.challenge05.data.local.entities.NowPlayingMovie

@Dao
interface NowPlayingMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNowPlayingMovies(movies: List<NowPlayingMovie>)

    @Query("Select * From now_playing_movies")
    fun getMoviesNowPlaying(): PagingSource<Int, NowPlayingMovie>

    @Query("Delete From now_playing_movies")
    suspend fun clearAllNowPlayingMovies()
}