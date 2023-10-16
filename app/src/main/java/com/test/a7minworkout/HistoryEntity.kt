package com.test.a7minworkout

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "History-Table")
data class HistoryEntity(
    @PrimaryKey
    val date : String
)
