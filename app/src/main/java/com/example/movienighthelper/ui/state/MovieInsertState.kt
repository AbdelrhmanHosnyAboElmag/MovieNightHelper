package com.example.movienighthelper.ui.state

import com.example.movienighthelper.ui.model.PopularMovieUi

data class MovieInsertState(
    val isWatchLater: Boolean = false,
    val error: String = ""
)