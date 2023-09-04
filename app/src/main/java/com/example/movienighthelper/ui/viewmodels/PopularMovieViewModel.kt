package com.example.movienighthelper.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movienighthelper.base.BaseViewModel
import com.example.movienighthelper.domain.usecases.GetPopularMovies
import com.example.movienighthelper.domain.usecases.GetSearchMoviesUseCase
import com.example.movienighthelper.domain.usecases.GetWatchListUseCase
import com.example.movienighthelper.domain.usecases.InsertWatchLaterUseCase
import com.example.movienighthelper.ui.state.MovieInsertState
import com.example.movienighthelper.ui.state.MovieWatchLaterState
import com.example.movienighthelper.ui.state.PopularMovieState
import com.example.movienighthelper.utils.DataResult
import com.example.movienighthelper.utils.addUiWatchLaterToApiMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PopularMovieViewModel @Inject constructor(
    private val getPopularMovieUseCase: GetPopularMovies,
    private val insertWatchLaterUseCase: InsertWatchLaterUseCase,
    private val getWatchListUseCase: GetWatchListUseCase,
    private val getSearchMoviesUseCase: GetSearchMoviesUseCase
) : BaseViewModel() {
    private val _popularMovie = MutableLiveData<PopularMovieState?>()
    val popularMovie get() : LiveData<PopularMovieState?> = _popularMovie

    init {
        Log.d("ersdfads", ":1 ")
        loadWatchLater()
    }

    fun loadPopularMovies() {
        getPopularMovieUseCase().onEach { result ->
            when (result) {
                is DataResult.Success -> {
                    _popularMovie.value = result.data?.let { PopularMovieState(PopularScreenState =addUiWatchLaterToApiMovies(it,movieWatchLater.value?.watchLater)) }
                }

                is DataResult.Loading -> {
                    _popularMovie.value = PopularMovieState(isLoading = true)
                }

                is DataResult.Error -> {
                    _popularMovie.value = result.message?.let { PopularMovieState(error = it) }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loadSearchMovies(query:String) {
        getSearchMoviesUseCase(query).onEach { result ->
            when (result) {
                is DataResult.Success -> {
                    _popularMovie.value = result.data?.let { PopularMovieState(PopularScreenState =addUiWatchLaterToApiMovies(it,movieWatchLater.value?.watchLater)) }
                }

                is DataResult.Loading -> {
                    _popularMovie.value = PopularMovieState(isLoading = true)
                }

                is DataResult.Error -> {
                    _popularMovie.value = result.message?.let { PopularMovieState(error = it) }
                }
            }
        }.launchIn(viewModelScope)
    }

    private val _insertMovieWatchLater = MutableLiveData<MovieInsertState?>()
    val insertMovieWatchLater get() : LiveData<MovieInsertState?> = _insertMovieWatchLater

    fun insertWatchLater(id:Int,is_watch:Boolean){
        insertWatchLaterUseCase(id, is_watch).onEach { result ->
            when (result) {
                is DataResult.Success -> {
                    _insertMovieWatchLater.value = result.data?.let { MovieInsertState(isWatchLater = it) }
                }

                is DataResult.Loading -> {
                }

                is DataResult.Error -> {
                    _insertMovieWatchLater.value = result.message?.let { MovieInsertState(error = it) }

                }
            }
        }.launchIn(viewModelScope)
    }


    private val _movieWatchLater = MutableLiveData<MovieWatchLaterState?>()
    val movieWatchLater get() : LiveData<MovieWatchLaterState?> = _movieWatchLater

    fun loadWatchLater(){
        getWatchListUseCase().onEach { result ->
            when (result) {
                is DataResult.Success -> {
                    _movieWatchLater.value = result.data?.let { MovieWatchLaterState(watchLater = it) }
                    loadPopularMovies()
                }

                is DataResult.Loading -> {
                }

                is DataResult.Error -> {
                    _movieWatchLater.value = result.message?.let { MovieWatchLaterState(error = it) }

                }
            }
        }.launchIn(viewModelScope)
    }
}