package banquemisr.challenge05.data.di

import banquemisr.challenge05.data.repository.MovieRepositoryImpl
import banquemisr.challenge05.data.repository.NowPlayingMoviesRepositoryImpl
import banquemisr.challenge05.data.repository.PopularMoviesRepositoryImpl
import banquemisr.challenge05.data.repository.UpcomingMoviesRepositoryImpl
import banquemisr.challenge05.domain.repositories.MovieRepository
import banquemisr.challenge05.domain.repositories.NowPlayingMoviesRepository
import banquemisr.challenge05.domain.repositories.PopularMoviesRepository
import banquemisr.challenge05.domain.repositories.UpcomingMoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository



    @Binds
    @Singleton
    abstract fun bindNowPlayingMoviesRepository(
        impl: NowPlayingMoviesRepositoryImpl
    ): NowPlayingMoviesRepository

    @Binds
    @Singleton
    abstract fun bindPopularMoviesRepository(
        impl: PopularMoviesRepositoryImpl
    ): PopularMoviesRepository

    @Binds
    @Singleton
    abstract fun bindUpcomingMoviesRepository(
        impl: UpcomingMoviesRepositoryImpl
    ): UpcomingMoviesRepository
}
