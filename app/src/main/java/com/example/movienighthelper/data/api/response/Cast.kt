package com.example.movienighthelper.data.api.response

data class Cast(
    val known_for_department: String,
    val name: String,
    val popularity: Double,
    val profile_path: String?
)