package com.example.movienighthelper.domain.usecases

import com.example.movienighthelper.data.api.response.Cast
import com.example.movienighthelper.data.api.response.Crew
import com.example.movienighthelper.data.api.response.MovieDetail
import com.example.movienighthelper.data.repository.MovieRepository
import com.example.movienighthelper.utils.DataResult
import com.example.movienighthelper.utils.HttpStatusMessageResolver
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class GetPairActorAndDirectorUseCase @Inject constructor(private val MovieRepositoryImpl: MovieRepository) {
    operator fun invoke(movieId: Int): Flow<DataResult<Pair<List<Cast>, List<Crew>>>> =
        flow {
            try {
                emit(DataResult.Loading())
                val repo = MovieRepositoryImpl.getMovieCast(movieId)
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