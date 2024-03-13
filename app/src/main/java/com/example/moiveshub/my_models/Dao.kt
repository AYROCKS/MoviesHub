package com.example.moiveshub.my_models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {

    @Insert
    fun addMovie(result: Result)

    @Query("SELECT * FROM movies_database")
    fun getMoviesData() : LiveData<List<Result>>

    @Delete
    fun deleteMovies(result: Result)

    @Query("SELECT EXISTS(SELECT 1 FROM movies_database WHERE id = :id)")
    fun isSaved(id : Int) : LiveData<Boolean>

}