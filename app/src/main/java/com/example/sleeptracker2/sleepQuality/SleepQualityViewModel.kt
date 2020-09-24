package com.example.sleeptracker2.sleepQuality

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sleeptracker2.database.SleepDao
import com.example.sleeptracker2.database.SleepEntity
import kotlinx.coroutines.launch

class SleepQualityViewModel(val sleepid:Long=0L,val dataSource:SleepDao) :ViewModel(){
    private val _navigateToSleepTracker = MutableLiveData<Boolean?>()

    val navigateToSleepTracker: LiveData<Boolean?>
        get() = _navigateToSleepTracker

    fun doneNavigating() {
        _navigateToSleepTracker.value = null
    }
    fun onSetSleepQuality(quality:Int){
        viewModelScope.launch {
            val data=dataSource.get(sleepid)?:return@launch
            data.sleepQuality=quality
            dataSource.update(data)
         _navigateToSleepTracker.value=true
        }
    }
}