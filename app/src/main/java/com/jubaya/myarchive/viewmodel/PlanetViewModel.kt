package com.jubaya.myarchive.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room

import com.jubaya.myarchive.model.ArchiveDatabase
import com.jubaya.myarchive.model.Planet
import com.jubaya.myarchive.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PlanetViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val planetsLD = MutableLiveData<List<Planet>>()
    val loadingLD = MutableLiveData<Boolean>()
    val planetLoadErrorLD = MutableLiveData<Boolean>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    private val db = Room.databaseBuilder(
        getApplication(),
        ArchiveDatabase::class.java, "newplanetdb"
    ).build()

    fun refresh() {
        loadingLD.value = true
        planetLoadErrorLD.value = false

        launch {
            try {
                val db = buildDb(getApplication())
//                val allPlanet = db.planetDao().selectAll()
                planetsLD.postValue(db.planetDao().selectAll())
            } catch (e: Exception) {
                planetLoadErrorLD.postValue(true)
            } finally {
                loadingLD.postValue(false)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}