package com.example.movienighthelper.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movienighthelper.data.local.entity.WatchLaterEntity

@Dao
interface MovieWatchLaterDao {
    @Query("select * from watchlaterentity")
    fun getWatchLater(): MutableList<WatchLaterEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movieWatchLater: WatchLaterEntity)

    @Query("SELECT * FROM watchlaterentity WHERE id = :entityId")
    fun getEntityById(entityId: Int): WatchLaterEntity?
}