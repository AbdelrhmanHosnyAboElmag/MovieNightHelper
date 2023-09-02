package com.example.movienighthelper.ui.model

import com.example.movienighthelper.data.api.response.ResultPopularMovie

data class PopularMovieResultUi(
    val id: Long,
    val original_title: String,
    val overview: String,
    val poster_path: String?,
    val release_date: String,
    val title: String,
    var is_watch_later: Boolean = false
)