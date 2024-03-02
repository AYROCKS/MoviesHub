package com.example.moiveshub.my_models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun getDao() : Dao

    companion object{
        private var db: MoviesDatabase? = null


        fun createDb(context: Context) : MoviesDatabase {
            if (db == null) {

                db = Room.databaseBuilder(context, MoviesDatabase::class.java,"MyDatabase").allowMainThreadQueries()
                    .build()
            }

            return db!!

        }
    }
}