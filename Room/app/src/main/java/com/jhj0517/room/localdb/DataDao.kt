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
    suspend fun insertData(data: ExampleData)

    @Update
    suspend fun updateData(data: ExampleData)

    @Delete
    suspend fun deleteData(data: ExampleData)

    @Query("SELECT * FROM example_table")
    suspend fun getAllData(): List<ExampleData>
}