package com.example.movienighthelper.data.repository

import com.example.movienighthelper.data.api.MoviesApi
import com.example.movienighthelper.data.api.response.PopularMovie
import com.example.movienighthelper.data.local.database.MovieWatchLaterDatabase
import com.example.movienighthelper.data.local.entity.WatchLaterEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi,
    private val database: MovieWatchLaterDatabase
) : MovieRepository {
    override suspend fun getPopularMovie(): PopularMovie {
        return withContext(Dispatchers.IO) {
            moviesApi.getPopularMovies().await()
        }
    }

    override suspend fun insertWatchLater(id: Long, is_watch: Boolean): Boolean {
        return withContext(Dispatchers.IO) {
            database.movieWatchLaterDao.insertAll(WatchLaterEntity(id, is_watch))
            database.movieWatchLaterDao.getEntityById(id)?.is_watch_later == true
        }
    }

    override suspend fun getWatchLater(): MutableList<WatchLaterEntity> {
        return withContext(Dispatchers.IO) {
            database.movieWatchLaterDao.getWatchLater()
        }
    }

    override suspend fun getSearchMovies(query: String): PopularMovie {
       return withContext(Dispatchers.IO) {
            moviesApi.searchMovies(query).await()
        }
    }
}