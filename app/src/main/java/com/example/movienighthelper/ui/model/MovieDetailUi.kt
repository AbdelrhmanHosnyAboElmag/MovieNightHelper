package com.example.movienighthelper.ui.model

data class MovieDetailUi(
    val id: Int=0,
    val overview: String="",
    val poster_path: String="",
    val release_date: String="",
    val revenue: Int=0,
    val runtime: Int=0,
    val status: String="",
    val tagline: String="",
    val title: String="",
)