package com.example.sleeptracker2.sleepTracker

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sleeptracker2.database.SleepDao

class SleepTrackerViewModelFactory(private val dataSource:SleepDao,private val application: Application):
    ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SleepViewModel::class.java)){
            return SleepViewModel(
                dataSource,
                application
            ) as T
        }
        throw IllegalArgumentException("unknown View Model class")
    }

}