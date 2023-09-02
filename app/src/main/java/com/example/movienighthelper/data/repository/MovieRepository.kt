package com.example.movienighthelper.data.repository

import com.example.movienighthelper.data.api.response.PopularMovie
import com.example.movienighthelper.data.local.entity.WatchLaterEntity

interface MovieRepository {
    suspend fun getPopularMovie(): PopularMovie
    suspend fun insertWatchLater(id:Long,is_watch:Boolean): Boolean

    suspend fun getWatchLater():MutableList<WatchLaterEntity>

}