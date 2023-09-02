package com.example.movienighthelper.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movienighthelper.TestCoroutineRule
import com.example.movienighthelper.data.api.response.PopularMovie
import com.example.movienighthelper.domain.usecases.GetPopularMovies
import com.example.movienighthelper.domain.usecases.GetSearchMoviesUseCase
import com.example.movienighthelper.domain.usecases.GetWatchListUseCase
import com.example.movienighthelper.domain.usecases.InsertWatchLaterUseCase
import com.example.movienighthelper.ui.model.PopularMovieUi
import com.example.movienighthelper.ui.state.PopularMovieState
import com.example.movienighthelper.utils.DataResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class PopularMovieViewModelTest {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var getPopularMoviesUseCase: GetPopularMovies
    @Mock
    lateinit var insertWatchLaterUseCase: InsertWatchLaterUseCase
    @Mock
    lateinit var getWatchListUseCase: GetWatchListUseCase
    @Mock
    lateinit var getSearchMoviesUseCase: GetSearchMoviesUseCase


    private lateinit var popularMovieViewModel: PopularMovieViewModel
    @Before
    fun setUp() {
        popularMovieViewModel = PopularMovieViewModel(getPopularMoviesUseCase,insertWatchLaterUseCase,getWatchListUseCase,getSearchMoviesUseCase)
    }
    @Test
    fun loadPopularMovieApiDataSuccess() = runTest {
        // Arrange
        val expectedValue = PopularMovieState(PopularScreenState = PopularMovieUi()) // Add expected result here
        `when`(getPopularMoviesUseCase()).thenReturn(flowOf(DataResult.Success(PopularMovie())))
        popularMovieViewModel.loadPopularMovies()
        val result = popularMovieViewModel.popularMovie.value
        assertEquals(expectedValue, result)
    }

    @Test
    fun loadSocialMediaDataError() = runTest {
        // Arrange
        val expectedValue = PopularMovieState(error = "error-occurred")
        `when`(getPopularMoviesUseCase()).thenReturn(flowOf(DataResult.Error("error-occurred")))
        popularMovieViewModel.loadPopularMovies()
        val result = popularMovieViewModel.popularMovie.value
        assertEquals(expectedValue, result)
    }
}