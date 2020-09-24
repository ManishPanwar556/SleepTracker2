package com.example.sleeptracker2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SleepDao {
    @Insert
    suspend fun insert(sleepEntity: SleepEntity)

    @Update
    suspend fun update(sleepEntity: SleepEntity)

    @Query("SELECT* FROM sleep_quality_table Where sleepId=:id")
    suspend fun get(id: Long): SleepEntity?

    @Query("DELETE FROM sleep_quality_table")
    suspend fun clear()

    @Query("SELECT * FROM sleep_quality_table ORDER BY sleepId DESC LIMIT 1")
    suspend fun getData(): SleepEntity?

    @Query("SELECT * FROM sleep_quality_table ORDER BY sleepId DESC")
    fun getAllData(): LiveData<List<SleepEntity>>
}