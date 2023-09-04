package com.example.movienighthelper.data.repostory

import com.example.movienighthelper.data.api.response.Cast
import com.example.movienighthelper.data.api.response.CastMovie
import com.example.movienighthelper.data.api.response.Crew
import com.example.movienighthelper.data.api.response.MovieDetail
import com.example.movienighthelper.data.api.response.PopularMovie
import com.example.movienighthelper.data.api.response.SimilarMovie
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

    override suspend fun insertWatchLater(id: Int, is_watch: Boolean): Boolean {
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

    override suspend fun getMovieDetails(movieId: Int): MovieDetail {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieSimilar(movieId: Int): SimilarMovie {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieCast(movieId: Int): Pair<List<Cast>, List<Crew>> {
        TODO("Not yet implemented")
    }

    override suspend fun filterActor(cast: List<Cast>): List<Cast> {
        TODO("Not yet implemented")
    }

    override suspend fun filterCrew(crew: List<Crew>): List<Crew> {
        TODO("Not yet implemented")
    }


}