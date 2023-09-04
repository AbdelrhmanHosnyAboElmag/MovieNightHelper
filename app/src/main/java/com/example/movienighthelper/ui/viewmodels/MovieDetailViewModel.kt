package com.example.movienighthelper.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movienighthelper.base.BaseViewModel
import com.example.movienighthelper.domain.usecases.GetMovieDetailUseCase
import com.example.movienighthelper.domain.usecases.GetPairActorAndDirectorUseCase
import com.example.movienighthelper.domain.usecases.GetSimilarMovieUseCase
import com.example.movienighthelper.domain.usecases.InsertWatchLaterUseCase
import com.example.movienighthelper.ui.model.MovieDetailUi
import com.example.movienighthelper.ui.state.ActorAndDircetorState
import com.example.movienighthelper.ui.state.MovieDetailState
import com.example.movienighthelper.ui.state.MovieInsertState
import com.example.movienighthelper.ui.state.PopularMovieState
import com.example.movienighthelper.ui.state.SimilarMovieState
import com.example.movienighthelper.utils.DataResult
import com.example.movienighthelper.utils.addUiWatchLaterToApiMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getSimilarMovieUseCase: GetSimilarMovieUseCase,
    private val getPairActorAndDirectorUseCase: GetPairActorAndDirectorUseCase,
    private val insertWatchLaterUseCase: InsertWatchLaterUseCase
    ) :
    BaseViewModel() {
    private val _detailMovie = MutableLiveData<MovieDetailState?>()
    val detailMovie get() : LiveData<MovieDetailState?> = _detailMovie


    fun loadMoviesDetail(movieId: Int) {
        getMovieDetailUseCase(movieId).onEach { result ->
            when (result) {
                is DataResult.Success -> {
                    _detailMovie.value = result.data?.let {
                        MovieDetailState(
                            MovieDetailState = result.data.toUiModel()
                        )
                    }
                }

                is DataResult.Loading -> {
                    _detailMovie.value = MovieDetailState(isLoading = true)
                }

                is DataResult.Error -> {
                    _detailMovie.value = result.message?.let { MovieDetailState(error = it) }
                }
            }
        }.launchIn(viewModelScope)
    }

    private val _similarMovie = MutableLiveData<SimilarMovieState?>()
    val similarMovie get() : LiveData<SimilarMovieState?> = _similarMovie

    fun loadSimilarMovies(movieId: Int) {
        getSimilarMovieUseCase(movieId).onEach { result ->
            when (result) {
                is DataResult.Success -> {
                    _similarMovie.value = result.data?.let {
                        SimilarMovieState(
                            MovieSimilarState = result.data.toUiModel()
                        )
                    }
                }

                is DataResult.Loading -> {
                    _similarMovie.value = SimilarMovieState(isLoading = true)
                }

                is DataResult.Error -> {
                    _similarMovie.value = result.message?.let { SimilarMovieState(error = it) }
                }
            }
        }.launchIn(viewModelScope)
    }

    private val _actorAndDirector = MutableLiveData<ActorAndDircetorState?>()
    val actorAndDirector get() : LiveData<ActorAndDircetorState?> = _actorAndDirector

    fun loadActorAndDirectorMovies(movieId: Int) {
        getPairActorAndDirectorUseCase(movieId).onEach { result ->
            when (result) {
                is DataResult.Success -> {
                    _actorAndDirector.value = result.data?.let {
                        ActorAndDircetorState(
                            castActorAndDirector = result.data
                        )
                    }
                }

                is DataResult.Loading -> {
                    _actorAndDirector.value = ActorAndDircetorState(isLoading = true)
                }

                is DataResult.Error -> {
                    _actorAndDirector.value = result.message?.let { ActorAndDircetorState(error = it) }
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

}