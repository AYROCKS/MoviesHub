package com.example.moiveshub.my_mvvm

import com.example.moiveshub.Api.Constants
import com.example.moiveshub.Api.MoviesApi
import com.example.moiveshub.my_models.MovieModel
import retrofit2.Response

class MovieRepository(private val moviesApi: MoviesApi) {

   suspend fun getMovies(page: Int) : Response<MovieModel> = moviesApi.getData(Constants.API_KEY,"en-IN", page)

   suspend fun searchMovies(search: String): Response<MovieModel> = moviesApi.searchMovie(search, Constants.API_KEY)

   suspend fun getLatestMovies(page: Int) : Response<MovieModel> = moviesApi.getLatestMovie(Constants.API_KEY, page)

   suspend fun getPopularMovies(page: Int) : Response<MovieModel> = moviesApi.getPopularMovie(Constants.API_KEY, page)
}
