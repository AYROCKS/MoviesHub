package com.example.moiveshub.my_mvvm

import androidx.lifecycle.LiveData
import com.example.moiveshub.Api.Constants
import com.example.moiveshub.Api.MoviesApi
import com.example.moiveshub.my_models.Dao
import com.example.moiveshub.my_models.Result

class MovieRepository(private val moviesApi: MoviesApi, private val moviesDatabase: Dao) {

   suspend fun getMovies(page: Int) =  moviesApi.getData(Constants.API_KEY,"en-IN", page)

   suspend fun searchMovies(search: String) = moviesApi.searchMovie(search, Constants.API_KEY)

   suspend fun getLatestMovies(page: Int) = moviesApi.getLatestMovie(Constants.API_KEY, page)

   suspend fun getPopularMovies(page: Int) = moviesApi.getPopularMovie(Constants.API_KEY, page)

   fun getData() : LiveData<List<Result>> = moviesDatabase.getMoviesData()
   fun deleteData(result: Result) = moviesDatabase.deleteMovies(result)

   fun addData(result: Result) = moviesDatabase.addMovie(result)

   fun isSaved(id : Int) = moviesDatabase.isSaved(id)
}
