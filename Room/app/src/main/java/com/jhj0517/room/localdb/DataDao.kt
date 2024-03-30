package com.jhj0517.room.localdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jhj0517.room.models.ExampleData


@Dao
interface DataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(data: ExampleData?)

    @Update
    fun updateData(data: ExampleData?)

    @Delete
    fun deleteData(data: ExampleData?)

    @Query("SELECT * FROM example_table")
    fun getAllData(): List<ExampleData>
}