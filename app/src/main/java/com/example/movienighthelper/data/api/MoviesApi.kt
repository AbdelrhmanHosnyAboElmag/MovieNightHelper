package com.example.movienighthelper.data.api

import com.example.movienighthelper.data.api.response.CastMovie
import com.example.movienighthelper.data.api.response.MovieDetail
import com.example.movienighthelper.data.api.response.PopularMovie
import com.example.movienighthelper.data.api.response.SimilarMovie
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
    @GET("3/movie/popular")
    fun getPopularMovies(): Deferred<PopularMovie>
    @GET("3/search/movie")
    fun searchMovies(@Query("query") query: String): Deferred<PopularMovie>

    @GET("3/movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
    ): Deferred<MovieDetail>

    @GET("3/movie/{movie_id}/similar")
    fun getSimilarMovies(
        @Path("movie_id") movieId: Int): Deferred<SimilarMovie>

    @GET("3/movie/{movie_id}/credits")
    fun getCredits(
        @Path("movie_id") movieId: Int): Deferred<CastMovie>
}