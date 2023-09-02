package com.example.movienighthelper.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movienighthelper.data.local.dao.MovieWatchLaterDao
import com.example.movienighthelper.data.local.entity.WatchLaterEntity

@Database(entities =[WatchLaterEntity::class], version = 1)
abstract class MovieWatchLaterDatabase: RoomDatabase()  {
    abstract val movieWatchLaterDao: MovieWatchLaterDao

}