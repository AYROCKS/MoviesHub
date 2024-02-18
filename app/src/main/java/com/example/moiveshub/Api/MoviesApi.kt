package com.example.moiveshub.Api

import com.example.moiveshub.my_models.MovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("trending/movie/week")
    suspend fun getData(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MovieModel>

    @GET("movie/top_rated")
    suspend fun getLatestMovie(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ) : Response<MovieModel>

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ) : Response<MovieModel>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") search: String,
        @Query("api_key") api_key: String
    ): Response<MovieModel>


}