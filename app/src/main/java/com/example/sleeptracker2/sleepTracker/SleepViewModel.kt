package com.example.sleeptracker2.sleepTracker

import android.app.Application
import androidx.lifecycle.*
import androidx.room.Room
import com.example.sleeptracker2.database.SleepDao
import com.example.sleeptracker2.database.SleepDatabase
import com.example.sleeptracker2.database.SleepEntity
import com.example.sleeptracker2.formatData
import kotlinx.coroutines.launch


class SleepViewModel(val dataSource: SleepDao, application: Application) :
    AndroidViewModel(application) {
    private val _navigateToSleepQuality = MutableLiveData<SleepEntity>()

    val navigateToSleepQuality: LiveData<SleepEntity>
        get() = _navigateToSleepQuality
    private var sleepdata = MutableLiveData<SleepEntity?>()
    private val allData = dataSource.getAllData()
    val nightString = Transformations.map(allData) { allData ->
        formatData(allData, application.resources)
    }
    var startEnabled=Transformations.map(sleepdata){
        it==null
    }
    var stopEnabled=Transformations.map(sleepdata){
        it!=null
    }

    init {
        initializeSleepData()
    }

    fun doneNavigating() {
        _navigateToSleepQuality.value = null
    }

    private fun initializeSleepData() {
        viewModelScope.launch {
            sleepdata.value = getSleepData()
        }
    }

    private suspend fun getSleepData(): SleepEntity? {
        var night = dataSource.getData()
        if (night?.endTimeMilli != night?.startTimeMilli) {
            night = null
        }
        return night
    }

    fun onStartTracking() {
        viewModelScope.launch {
            val newData = SleepEntity()
            sleepdata.value = getSleepData()
            insertNewData(newData)

        }
    }

    private suspend fun insertNewData(newData: SleepEntity) {
        dataSource.insert(newData)
    }

    fun onStopTracking() {
        viewModelScope.launch {

            val olddata = getSleepData() ?: return@launch
            olddata.endTimeMilli = System.currentTimeMillis()
            update(olddata)
            _navigateToSleepQuality.value = olddata
        }
    }

    private suspend fun update(olddata: SleepEntity) {
        dataSource.update(olddata)
    }

    fun onClear() {
        viewModelScope.launch {
            clear()
        }
    }

    private suspend fun clear() {
        dataSource.clear()
    }

}