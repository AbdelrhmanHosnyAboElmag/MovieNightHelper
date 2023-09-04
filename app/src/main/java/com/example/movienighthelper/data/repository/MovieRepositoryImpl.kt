package com.example.movienighthelper.data.repository

import com.example.movienighthelper.data.api.MoviesApi
import com.example.movienighthelper.data.api.response.Cast
import com.example.movienighthelper.data.api.response.CastMovie
import com.example.movienighthelper.data.api.response.Crew
import com.example.movienighthelper.data.api.response.MovieDetail
import com.example.movienighthelper.data.api.response.PopularMovie
import com.example.movienighthelper.data.api.response.SimilarMovie
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

    override suspend fun insertWatchLater(id: Int, is_watch: Boolean): Boolean {
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

    override suspend fun getMovieDetails(movieId: Int): MovieDetail {
        return withContext(Dispatchers.IO) {
            moviesApi.getMovieDetails(movieId).await()
        }
    }

    override suspend fun getMovieSimilar(movieId: Int): SimilarMovie {
        return withContext(Dispatchers.IO) {
            moviesApi.getSimilarMovies(movieId).await()
        }
    }

    override suspend fun getMovieCast(movieId: Int): Pair<List<Cast>, List<Crew>> {
        return withContext(Dispatchers.IO) {
            val allCast= moviesApi.getCredits(movieId).await()
             filterActor(allCast.cast) to filterCrew(allCast.crew)
        }
    }

    override suspend fun filterActor(cast: List<Cast>): List<Cast> {
        val actors = cast.filter { it.known_for_department == "Acting" }
        val sortedActors = actors.sortedByDescending { it.popularity }
        val top5Actors = sortedActors.take(5)

        return top5Actors
    }

    override suspend fun filterCrew(crew: List<Crew>): List<Crew> {
        val directors = crew.filter { it.known_for_department == "Directing" }
        val sortedDirectors = directors.sortedByDescending { it.popularity }
        val top5Directors = sortedDirectors.take(5)

        return top5Directors
    }
}