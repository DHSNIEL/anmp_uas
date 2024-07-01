package com.jubaya.myarchive.model

import androidx.room.Dao
import androidx.room.*

@Dao
interface PlanetDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll (vararg planet:Planet)

    @Query("SELECT * FROM planets")
    fun selectAll(): List<Planet>

    @Query("SELECT * FROM planets WHERE id=:id")
    fun selectPlanet(id:Int): Planet

    @Delete
    fun deletePlanet(planet: Planet)
}