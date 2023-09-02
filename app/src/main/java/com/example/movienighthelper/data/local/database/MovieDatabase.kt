package com.example.movienighthelper.data.local.database

import androidx.room.RoomDatabase
import com.example.movienighthelper.data.local.dao.MovieDao

//@Database(entities =[RepositoriesDatabase::class, RepoDetailsDatabase::class], version = 1)
//@TypeConverters(OwnerTypeConverter::class)
abstract class MovieDatabase: RoomDatabase()  {
    abstract val movieDao: MovieDao

}