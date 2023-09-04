package com.example.movienighthelper.ui.state

import com.example.movienighthelper.ui.model.MovieDetailUi
import com.example.movienighthelper.ui.model.MovieSimilarUi

data class SimilarMovieState(
    val isLoading: Boolean = false,
    val MovieSimilarState: MovieSimilarUi = MovieSimilarUi(),
    val error: String = ""
)