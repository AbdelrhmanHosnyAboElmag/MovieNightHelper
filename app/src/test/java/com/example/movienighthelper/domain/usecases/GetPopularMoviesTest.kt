package com.example.movienighthelper.domain.usecases

import com.example.movienighthelper.data.api.response.PopularMovie
import com.example.movienighthelper.data.api.response.ResultPopularMovie
import com.example.movienighthelper.data.repostory.FakeMovieRepository
import com.example.movienighthelper.utils.DataResult
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetPopularMoviesTest{
    private lateinit var getRepo: GetPopularMovies
    private lateinit var fakeRepository: FakeMovieRepository

    @Before
    fun setUp() {
        fakeRepository = FakeMovieRepository()
        getRepo = GetPopularMovies(fakeRepository)
    }

    @Test
    fun `get movie that start with id 1`() = runBlocking {
        fakeRepository.PopularMovieList = PopularMovie(1, resultPopularMovies = mutableListOf(ResultPopularMovie(1,"saw","killer","/3","2010","saw")),1,1)
        val result = getRepo.invoke()
        result.collect { dataResult ->
            when (dataResult) {
                is DataResult.Success -> {
                    assertEquals(dataResult.data?.resultPopularMovies?.get(0)?.id, 1L)
                }
                is DataResult.Loading -> {}
                is DataResult.Error -> {}
            }
        }
    }


    @Test
    fun `get movie repo Error`() = runBlocking {
        fakeRepository.throwError = true
        fakeRepository.PopularMovieList = PopularMovie(1, resultPopularMovies = mutableListOf(ResultPopularMovie(1,"saw","killer","/3","2010","saw")),1,1)
        val result = getRepo.invoke()

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