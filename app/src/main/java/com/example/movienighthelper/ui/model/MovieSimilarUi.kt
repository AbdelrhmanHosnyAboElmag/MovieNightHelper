package com.example.movienighthelper.ui.model


data class MovieSimilarUi(
    val page: Int=0,
    val results: List<PopularMovieResultUi> = listOf(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)