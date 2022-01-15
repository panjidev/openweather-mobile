package com.test.openweather.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.test.openweather.db.FavoriteDao
import com.test.openweather.db.FavoriteRoomDatabase
import com.test.openweather.model.Favorite
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val fav: FavoriteDao
    private val  executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteRoomDatabase.getDatabase(application)
        fav = db.favDao()
    }

    fun getFav(login: String): LiveData<Favorite> = fav.getFav(login)

    fun getAllFav(): LiveData<List<Favorite>> = fav.getAllFav()

    fun insert(favorite: Favorite) {
        executorService.execute { fav.insert(favorite) }
    }

    fun delete(login: String) {
        executorService.execute { fav.delete(login) }
    }
}