package banquemisr.challenge05.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import banquemisr.challenge05.data.local.entities.UpcomingMovie

@Dao
interface UpcomingMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUpcomingMovies(movies: List<UpcomingMovie>)

    @Query("Select * From upcoming_movies")
    fun getUpcomingMovies(): PagingSource<Int, UpcomingMovie>

    @Query("Delete From upcoming_movies")
    suspend fun clearUpcomingMovies()
}