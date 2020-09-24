package com.example.sleeptracker2.sleepQuality

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sleeptracker2.database.SleepDao
import com.example.sleeptracker2.database.SleepEntity

class SleepQualityViewModelFactory(private val sleepDataKey: Long,private val dataSource:SleepDao) :ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepQualityViewModel::class.java)) {
            return SleepQualityViewModel(sleepDataKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}