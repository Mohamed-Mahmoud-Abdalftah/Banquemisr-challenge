package banquemisr.challenge05.data.di

import android.content.Context
import androidx.room.Room
import banquemisr.challenge05.data.local.MoviesDatabase
import banquemisr.challenge05.data.local.NowPlayingMoviesDao
import banquemisr.challenge05.data.local.PopularMoviesDao
import banquemisr.challenge05.data.local.UpcomingMoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            "movies_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideNowPlayingMoviesDao(database: MoviesDatabase): NowPlayingMoviesDao {
        return database.nowPlayingMoviesDao()
    }

    @Provides
    @Singleton
    fun provideUpcomingMoviesDao(database: MoviesDatabase): UpcomingMoviesDao {
        return database.upcomingMoviesDao()
    }

    @Provides
    @Singleton
    fun providePopularMoviesDao(database: MoviesDatabase): PopularMoviesDao {
        return database.popularMoviesDao()
    }

}
