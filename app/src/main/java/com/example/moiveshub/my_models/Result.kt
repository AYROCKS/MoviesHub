package com.example.moiveshub.my_models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_database")
data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    var saved : Boolean = false
)