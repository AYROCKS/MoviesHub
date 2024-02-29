package com.example.moiveshub.my_models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {

    @Insert
    fun addMovie(result: Result)

    @Query("SELECT * FROM movies_database")
    fun getMoviesData() : List<Result>

    @Delete
    fun deleteMovies(result: Result)
}