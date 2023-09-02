package com.example.movienighthelper.data.api

import com.example.movienighthelper.data.api.response.PopularMovie
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("3/movie/popular")
    fun getPopularMovies(): Deferred<PopularMovie>
    @GET("3/search/movie")
    fun searchMovies(@Query("query") query: String): Deferred<PopularMovie>
}