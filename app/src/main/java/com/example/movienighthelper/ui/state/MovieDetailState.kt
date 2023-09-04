package com.example.movienighthelper.ui.state

import com.example.movienighthelper.ui.model.MovieDetailUi
import com.example.movienighthelper.ui.model.PopularMovieUi

data class MovieDetailState(
    val isLoading: Boolean = false,
    val MovieDetailState: MovieDetailUi = MovieDetailUi(),
    val error: String = ""
)