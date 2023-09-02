package com.example.movienighthelper.data.repository

import com.example.movienighthelper.data.api.MoviesApi
import com.example.movienighthelper.data.api.response.PopularMovie
import com.example.movienighthelper.data.local.database.MovieDatabase
import com.example.movienighthelper.utils.apiKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi
) :MovieRepository {
    override suspend fun getPopularMovie(): PopularMovie {
        var result: PopularMovie
        withContext(Dispatchers.IO) {
            result = moviesApi.getPopularMovies().await()
        }
        return result
    }
}