package com.test.openweather.presentation.detail

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.openweather.BuildConfig
import com.test.openweather.databinding.ActivityDetailBinding
import com.test.openweather.model.Favorite
import com.test.openweather.model.ForecastResponse
import com.test.openweather.presentation.home.HomeActivity
import com.test.openweather.presentation.home.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity: AppCompatActivity() {
    private val binding : ActivityDetailBinding  by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    private val viewModel: MainViewModel by viewModel()
    private val viewModelFav: FavoriteViewModel by viewModel()
    private var cityName = ""
    private var lat = ""
    private var lon = ""
    private var fav: Favorite? = null
    private var cityId = ""
    private var isInsert = false

    companion object {
        const val LAT = "lat"
        const val LON = "lon"
        const val CITY = "city"
        const val ID = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initData()
        observer()
        initListener()
    }

    private fun initData() {
        title = "Daily Forecast"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        fav = Favorite()
        cityName = intent.getStringExtra(CITY).toString()
        cityId = intent.getStringExtra(ID).toString()
        lat = intent.getStringExtra(LAT).toString()
        lon = intent.getStringExtra(LON).toString()
        binding.pgBar.visibility = View.VISIBLE
        viewModel.getForecast(lat, lon, "current,minutely,hourly,alerts", BuildConfig.TOKEN)
    }

    private fun observer(){
        viewModelFav.getFav(cityName).observe(this,{
            if (it!=null) {
                isInsert = false
                binding.fabAdd.setColorFilter(Color.RED)
            } else {
                isInsert = true
                binding.fabAdd.setColorFilter(Color.GRAY)
            }
        })

        viewModel.dataForecast.observe(this,{
            val listforecast = AdapterDetail(it.daily)
            binding.rvItem.layoutManager = LinearLayoutManager(this)
            binding.rvItem.adapter = listforecast
            binding.pgBar.visibility = View.GONE
        })
    }

    private fun initListener(){
        binding.fabAdd.setOnClickListener {
            fav.let {
                fav?.city_name = cityName
            }
            if(isInsert){
                viewModelFav.insert(fav as Favorite)
            } else{
                viewModelFav.delete(cityName)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}