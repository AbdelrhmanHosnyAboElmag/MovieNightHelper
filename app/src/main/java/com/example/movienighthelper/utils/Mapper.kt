package com.example.movienighthelper.utils

import com.example.movienighthelper.data.api.response.PopularMovie
import com.example.movienighthelper.data.local.entity.WatchLaterEntity
import com.example.movienighthelper.ui.model.PopularMovieResultUi
import com.example.movienighthelper.ui.model.PopularMovieUi

interface ConvertibleTo<R> {
    fun toUiModel(): R
}

fun <R> List<out ConvertibleTo<R>>.toUiModelList(): List<R> {
    return this.map { it.toUiModel() }
}

fun addUiWatchLaterToApiMovies(
    popularMovie: PopularMovie,
    watchLaterList: MutableList<WatchLaterEntity>?
): PopularMovieUi {
    return PopularMovieUi(
        page = popularMovie.page,
        total_pages = popularMovie.total_pages,
        total_results = popularMovie.total_results,
        resultPopularMovies = popularMovie.resultPopularMovies.map { movie ->
            val isWatchLater = watchLaterList?.any { it.id == movie.id && it.is_watch_later } == true
            PopularMovieResultUi(
                id = movie.id,
                original_title = movie.original_title,
                overview = movie.overview,
                poster_path = movie.poster_path,
                release_date = movie.release_date,
                title = movie.title,
                is_watch_later = isWatchLater
            )
        }
    )
}
