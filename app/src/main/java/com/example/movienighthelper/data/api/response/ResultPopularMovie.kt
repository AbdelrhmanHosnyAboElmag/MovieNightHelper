package com.example.movienighthelper.data.api.response

import com.example.movienighthelper.ui.model.PopularMovieResultUi
import com.example.movienighthelper.utils.ConvertibleTo

data class ResultPopularMovie(
    val id: Int,
    val original_title: String,
    val overview: String,
    val poster_path: String?,
    val release_date: String,
    val title: String,
): ConvertibleTo<PopularMovieResultUi> {
    override fun toUiModel(): PopularMovieResultUi {
        return PopularMovieResultUi(
            id = this.id,
            original_title = this.original_title,
            overview = this.overview,
            poster_path = this.poster_path,
            release_date = this.release_date,
            title = this.title
        )
    }
}