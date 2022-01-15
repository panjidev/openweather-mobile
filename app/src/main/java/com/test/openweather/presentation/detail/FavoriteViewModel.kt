package com.test.openweather.presentation.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.test.openweather.data.FavoriteRepository
import com.test.openweather.model.Favorite

class FavoriteViewModel(application: Application) : ViewModel(){

    private val favoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun insert(fav: Favorite){
        favoriteRepository.insert(fav)
    }

    fun delete(login: String) {
        favoriteRepository.delete(login)
    }

    fun getFav(login: String): LiveData<Favorite> = favoriteRepository.getFav(login)

    fun getAllFav(): LiveData<List<Favorite>> = favoriteRepository.getAllFav()
}