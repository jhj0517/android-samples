package com.jhj0517.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "data")
data class ExampleData(
    @ColumnInfo(name = "name")
    val name:String=""
)
