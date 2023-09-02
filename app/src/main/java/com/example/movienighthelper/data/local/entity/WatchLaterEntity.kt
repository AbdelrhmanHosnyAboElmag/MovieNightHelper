package com.example.movienighthelper.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WatchLaterEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long ,
    val is_watch_later:Boolean = false
)