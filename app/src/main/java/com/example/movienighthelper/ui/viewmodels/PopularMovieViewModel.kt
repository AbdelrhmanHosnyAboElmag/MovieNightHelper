package com.example.movienighthelper.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movienighthelper.base.BaseViewModel
import com.example.movienighthelper.domain.usecases.GetPopularMovies
import com.example.movienighthelper.ui.state.PopularMovieState
import com.example.movienighthelper.utils.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PopularMovieViewModel @Inject constructor(
    private val getPopularMovieUseCase: GetPopularMovies
) : BaseViewModel() {
    private val _popularMovie = MutableLiveData<PopularMovieState?>()
    val popularMovie get() : LiveData<PopularMovieState?> = _popularMovie

    init {
        loadPopularMovies()
    }

    fun loadPopularMovies() {
        getPopularMovieUseCase().onEach { result ->
            when (result) {
                is DataResult.Success -> {
                    _popularMovie.value = result.data?.let { PopularMovieState(PopularScreenState = it.toUiModel()) }
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
}