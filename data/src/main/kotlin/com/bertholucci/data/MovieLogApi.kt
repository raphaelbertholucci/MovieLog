package com.bertholucci.data

import com.bertholucci.data.model.MovieListResponse
import com.bertholucci.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieLogApi {

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int?): MovieListResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int?): MovieListResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page: Int?): MovieListResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int?): MovieListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: String?): MovieResponse

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String?,
        @Query("page") page: Int?
    ): MovieListResponse
}