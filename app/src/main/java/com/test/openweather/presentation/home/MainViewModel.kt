package com.test.openweather.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.openweather.data.Repository
import com.test.openweather.model.ForecastResponse
import com.test.openweather.model.WeatherResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repository: Repository) : ViewModel(){

    val dataForecast = MutableLiveData<ForecastResponse>()
    val dataWeather = MutableLiveData<WeatherResponse>()
    val apiResponse = MutableLiveData<String>()

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    fun getWeather(cityName : String, appId: String){
        compositeDisposable.add(
            repository.getWeather(cityName, appId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    val response = it
                    dataWeather.postValue(response)
                },{
                    apiResponse.postValue(it.message)
                })
        )
    }

    fun getForecast(lat : String, lon: String, exclude:String, appid:String){
        compositeDisposable.add(
            repository.getForecast(lat, lon, exclude, appid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    val response = it
                    dataForecast.postValue(response)
                },{
                    apiResponse.postValue(it.message)
                })
        )
    }
}