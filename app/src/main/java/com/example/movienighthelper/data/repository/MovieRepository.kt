package com.example.movienighthelper.data.repository

import com.example.movienighthelper.data.api.response.PopularMovie

interface MovieRepository {
    suspend fun getPopularMovie(): PopularMovie
}