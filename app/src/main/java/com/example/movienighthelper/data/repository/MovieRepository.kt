package com.example.movienighthelper.data.repository

import com.example.movienighthelper.data.api.response.Cast
import com.example.movienighthelper.data.api.response.CastMovie
import com.example.movienighthelper.data.api.response.Crew
import com.example.movienighthelper.data.api.response.MovieDetail
import com.example.movienighthelper.data.api.response.PopularMovie
import com.example.movienighthelper.data.api.response.SimilarMovie
import com.example.movienighthelper.data.local.entity.WatchLaterEntity

interface MovieRepository {
    suspend fun getPopularMovie(): PopularMovie
    suspend fun insertWatchLater(id:Int,is_watch:Boolean): Boolean
    suspend fun getWatchLater():MutableList<WatchLaterEntity>
    suspend fun getSearchMovies(query: String):PopularMovie
    suspend fun getMovieDetails(movieId:Int):MovieDetail
    suspend fun getMovieSimilar(movieId:Int):SimilarMovie
    suspend fun getMovieCast(movieId:Int): Pair<List<Cast>, List<Crew>>
    suspend fun filterActor(cast: List<Cast>):List<Cast>
    suspend fun filterCrew(crew: List<Crew>):List<Crew>

}