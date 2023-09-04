package com.example.movienighthelper.data.api.response

import com.example.movienighthelper.ui.model.MovieDetailUi
import com.example.movienighthelper.utils.ConvertibleTo

data class MovieDetail(
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
) : ConvertibleTo<MovieDetailUi> {
    override fun toUiModel(): MovieDetailUi {
        return MovieDetailUi(
            id = this.id,
            overview = this.overview,
            poster_path = this.poster_path,
            release_date = this.release_date,
            revenue = this.revenue,
            runtime = this.runtime,
            status = this.status,
            tagline = this.tagline,
            title = this.title
        )
    }
}