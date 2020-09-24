package com.example.sleeptracker2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sleep_quality_table")
data class SleepEntity(
    @PrimaryKey(autoGenerate = true)
    var sleepId: Long = 0L,

    val startTimeMilli: Long = System.currentTimeMillis(),

    var endTimeMilli: Long = startTimeMilli,

    var sleepQuality: Int = -1

)