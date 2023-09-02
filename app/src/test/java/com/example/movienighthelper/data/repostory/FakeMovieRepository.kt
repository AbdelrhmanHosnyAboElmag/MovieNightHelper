package com.example.movienighthelper.data.repostory

import com.example.movienighthelper.data.api.response.PopularMovie
import com.example.movienighthelper.data.local.entity.WatchLaterEntity
import com.example.movienighthelper.data.repository.MovieRepository

class FakeMovieRepository : MovieRepository {
    var PopularMovieList = PopularMovie()
    var watchLaterEntity = mutableListOf<WatchLaterEntity>()
    var index: Int = 0
    var throwError = false
    var insertSimulate = false
    override suspend fun getPopularMovie(): PopularMovie {
        if (throwError) {
            throw Exception("Simulated error")
        }
        return PopularMovieList
    }

    override suspend fun insertWatchLater(id: Long, is_watch: Boolean): Boolean {
        return insertSimulate
    }

    override suspend fun getWatchLater(): MutableList<WatchLaterEntity> {
        if (throwError) {
            throw Exception("Simulated error")
        }
        return watchLaterEntity
    }

    override suspend fun getSearchMovies(query: String): PopularMovie {
        if (query == PopularMovieList.resultPopularMovies[0].title) {
            throw Exception("Simulated error")
        }
        return PopularMovieList
    }
}