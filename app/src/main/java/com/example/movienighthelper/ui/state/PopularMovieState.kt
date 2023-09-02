package com.example.movienighthelper.ui.state

import com.example.movienighthelper.ui.model.PopularMovieUi

data class PopularMovieState(
    val isLoading: Boolean = false,
    val PopularScreenState: PopularMovieUi = PopularMovieUi(),
    val error: String = ""
)