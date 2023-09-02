package com.example.movienighthelper.data.api.response

import com.example.movienighthelper.ui.model.PopularMovieUi
import com.example.movienighthelper.utils.ConvertibleTo
import com.example.movienighthelper.utils.toUiModelList
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class PopularMovie(
    val page: Int=0,
    @Json(name = "results") val resultPopularMovies: List<ResultPopularMovie> = listOf(),
    val total_pages: Int =0,
    val total_results: Int =0
): ConvertibleTo<PopularMovieUi> {
    override fun toUiModel(): PopularMovieUi {
        return PopularMovieUi(
            page = this.page,
            resultPopularMovies = this.resultPopularMovies.toUiModelList(),
            total_pages = this.total_pages,
            total_results = this.total_results
        )
    }
}