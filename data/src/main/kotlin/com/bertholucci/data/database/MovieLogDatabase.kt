package com.bertholucci.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bertholucci.data.model.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieLogDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}