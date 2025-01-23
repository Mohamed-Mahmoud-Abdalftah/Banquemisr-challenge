package banquemisr.challenge05.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import banquemisr.challenge05.data.local.entities.NowPlayingMovie
import banquemisr.challenge05.data.local.entities.PopularMovie
import banquemisr.challenge05.data.local.entities.UpcomingMovie

@Database(
    entities = [PopularMovie::class, UpcomingMovie::class, NowPlayingMovie::class],
    version = 1
)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun nowPlayingMoviesDao(): NowPlayingMoviesDao
    abstract fun upcomingMoviesDao(): UpcomingMoviesDao
    abstract fun popularMoviesDao(): PopularMoviesDao
}