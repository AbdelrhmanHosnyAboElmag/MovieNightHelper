package com.example.movienighthelper.ui.model

import com.example.movienighthelper.data.api.response.PopularMovie


data class PopularMovieUi(
    val page: Int = 0,
    val resultPopularMovies: List<PopularMovieResultUi> = listOf(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)