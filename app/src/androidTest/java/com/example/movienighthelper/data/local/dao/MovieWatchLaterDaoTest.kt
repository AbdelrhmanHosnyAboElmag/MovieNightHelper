package com.example.movienighthelper.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.movienighthelper.data.local.database.MovieWatchLaterDatabase
import com.example.movienighthelper.data.local.entity.WatchLaterEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class MovieWatchLaterDaoTest  {

    private lateinit var database: MovieWatchLaterDatabase
    private lateinit var dao: MovieWatchLaterDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MovieWatchLaterDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.movieWatchLaterDao
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun insertWatchLater() = runTest {
        val repoItem = WatchLaterEntity(
            id = 1L,
            is_watch_later = true
        )
        dao.insertAll(repoItem)
        assertThat(dao.getEntityById(1L)).isEqualTo(repoItem)
    }

    @Test
    fun getWatchLater() = runTest {
        val repoItem = WatchLaterEntity(
            id = 1L,
            is_watch_later = true
        )
        dao.insertAll(repoItem)
        assertThat(dao.getWatchLater()[0]).isEqualTo(repoItem)
    }

}