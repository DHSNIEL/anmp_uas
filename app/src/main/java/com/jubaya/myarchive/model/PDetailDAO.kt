package com.jubaya.myarchive.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface PDetailDAO {
    @Query("SELECT * FROM planetdetails WHERE planet_id = :planetId")
    fun selectDetailsByPlanetId(planetId: Int): LiveData<List<PDetail>>
}