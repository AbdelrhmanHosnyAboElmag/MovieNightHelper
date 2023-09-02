package com.example.movienighthelper.di


import com.example.movienighthelper.data.repository.MovieRepository
import com.example.movienighthelper.data.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideRepositoriesItemRepository(repository: MovieRepositoryImpl): MovieRepository {
        return repository
    }
}