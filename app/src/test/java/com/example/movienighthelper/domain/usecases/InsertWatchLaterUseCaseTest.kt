package com.example.movienighthelper.domain.usecases

import com.example.movienighthelper.data.local.entity.WatchLaterEntity
import com.example.movienighthelper.data.repostory.FakeMovieRepository
import com.example.movienighthelper.utils.DataResult
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class InsertWatchLaterUseCaseTest{
    private lateinit var getRepo: InsertWatchLaterUseCase
    private lateinit var fakeRepository: FakeMovieRepository

    @Before
    fun setUp() {
        fakeRepository = FakeMovieRepository()
        getRepo = InsertWatchLaterUseCase(fakeRepository)
    }

    @Test
    fun `get movie watch later false`() = runBlocking {
        fakeRepository.insertSimulate = false
        val result = getRepo.invoke(1L,false)
        result.collect { dataResult ->
            when (dataResult) {
                is DataResult.Success -> {
                    assertEquals(dataResult.data, false)
                }
                is DataResult.Loading -> {}
                is DataResult.Error -> {}
            }
        }
    }


    @Test
    fun `get movie watch later Error`() = runBlocking {
        fakeRepository.throwError = true
        fakeRepository.watchLaterEntity = mutableListOf(WatchLaterEntity(1L,true))
        val result = getRepo.invoke(1L,true)

        result.collect { dataResult ->
            when (dataResult) {
                is DataResult.Success -> {
                    // You can add assertions for the success state if needed
                }
                is DataResult.Loading -> {
                    // You can add assertions for the loading state if needed
                }
                is DataResult.Error -> {
                    // Assert that the error state is received
                    assertEquals(dataResult.message, "An error occurred: Simulated error")
                }
            }
        }
    }

}