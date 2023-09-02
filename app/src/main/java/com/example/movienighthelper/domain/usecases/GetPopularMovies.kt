package com.example.movienighthelper.domain.usecases

import com.example.movienighthelper.data.api.response.PopularMovie
import com.example.movienighthelper.data.repository.MovieRepository
import com.example.movienighthelper.utils.DataResult
import com.example.movienighthelper.utils.HttpStatusMessageResolver
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class GetPopularMovies @Inject constructor(private val MovieRepositoryImpl: MovieRepository) {
    operator fun invoke(): Flow<DataResult<PopularMovie>> = flow {
        try {
            emit(DataResult.Loading())
            val repo = MovieRepositoryImpl.getPopularMovie()
            emit(DataResult.Success(repo))
        } catch (e: HttpException) {
            emit(DataResult.Error(HttpStatusMessageResolver.resolveMessage(e.code())))
        } catch (e: UnknownHostException) {
            emit(DataResult.Error("No network connection"))
        } catch (e: SocketTimeoutException) {
            emit(DataResult.Error("Network request timed out"))
        } catch (e: Exception) {
            emit(DataResult.Error("An error occurred: ${e.message}"))
        }
    }
}