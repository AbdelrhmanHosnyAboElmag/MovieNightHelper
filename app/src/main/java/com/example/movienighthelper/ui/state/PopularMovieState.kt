package com.example.movienighthelper.ui.state

import com.example.movienighthelper.data.api.response.PopularMovie

data class PopularMovieState(
    val isLoading: Boolean = false,
    val PopularScreenState: PopularMovie = PopularMovie(),
    val error: String = ""
)