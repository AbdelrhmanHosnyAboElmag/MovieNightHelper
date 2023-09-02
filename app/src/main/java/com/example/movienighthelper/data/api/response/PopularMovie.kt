package com.example.movienighthelper.data.api.response

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class PopularMovie(
    val page: Int=0,
    @Json(name = "results") val resultPopularMovies: List<ResultPopularMovie> = listOf(),
    val total_pages: Int =0,
    val total_results: Int =0
)