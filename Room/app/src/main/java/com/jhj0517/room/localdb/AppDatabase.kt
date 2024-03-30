package com.jhj0517.room.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jhj0517.room.models.ExampleData


@Database(entities = [ExampleData::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataDao(): DataDao?
}