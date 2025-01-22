package banquemisr.challenge05.data.di

import banquemisr.challenge05.data.datasource.MovieDataSource
import banquemisr.challenge05.data.datasource.MovieDataSourceImpl
import banquemisr.challenge05.data.remote.ApiService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun provideMovieDataSource(remoteDS: MovieDataSourceImpl): MovieDataSource

}
