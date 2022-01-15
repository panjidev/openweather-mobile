package com.test.openweather.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.openweather.model.Favorite

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(fav: Favorite)

    @Query("DELETE from Favorite WHERE cityName= :cityName")
    fun delete(cityName: String)

    @Query("SELECT * from Favorite WHERE cityName= :cityName")
    fun getFav(cityName: String): LiveData<Favorite>

    @Query("SELECT * from Favorite ORDER BY id ASC")
    fun getAllFav(): LiveData<List<Favorite>>
}