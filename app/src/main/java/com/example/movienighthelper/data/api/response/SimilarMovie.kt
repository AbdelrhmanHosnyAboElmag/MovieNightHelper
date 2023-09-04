package com.example.movienighthelper.data.api.response

import com.example.movienighthelper.ui.model.MovieSimilarUi
import com.example.movienighthelper.ui.model.PopularMovieUi
import com.example.movienighthelper.utils.ConvertibleTo
import com.example.movienighthelper.utils.toUiModelList

data class SimilarMovie(
    val page: Int,
    val results: List<ResultPopularMovie>,
    val total_pages: Int,
    val total_results: Int
): ConvertibleTo<MovieSimilarUi> {
    override fun toUiModel(): MovieSimilarUi {
        return MovieSimilarUi(
            page = this.page,
            results = this.results.toUiModelList(),
            total_pages = this.total_pages,
            total_results = this.total_results
        )
    }
}