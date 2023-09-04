package com.example.movienighthelper.domain.usecases

import com.example.movienighthelper.data.repository.MovieRepository
import com.example.movienighthelper.utils.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertWatchLaterUseCase @Inject constructor(private val MovieRepositoryImpl: MovieRepository) {
    operator fun invoke(id:Int,is_watch:Boolean): Flow<DataResult<Boolean>> = flow {
        try {
            emit(DataResult.Loading())
            val repo = MovieRepositoryImpl.insertWatchLater(id, is_watch)
            emit(DataResult.Success(repo))
        } catch (e: Exception) {
            emit(DataResult.Error("An error occurred: ${e.message}"))
        }
    }
}