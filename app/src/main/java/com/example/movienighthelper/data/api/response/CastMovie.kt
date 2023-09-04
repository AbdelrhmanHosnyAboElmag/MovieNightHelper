package com.example.movienighthelper.data.api.response

data class CastMovie(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)