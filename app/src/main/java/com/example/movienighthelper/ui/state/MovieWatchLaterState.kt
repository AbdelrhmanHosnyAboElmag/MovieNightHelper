package com.example.movienighthelper.ui.state

import com.example.movienighthelper.data.local.entity.WatchLaterEntity
import com.example.movienighthelper.ui.model.PopularMovieUi

data class MovieWatchLaterState(
    val watchLater: MutableList<WatchLaterEntity> = mutableListOf(),
    val error: String = ""
)
