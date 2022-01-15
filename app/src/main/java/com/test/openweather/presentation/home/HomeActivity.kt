package com.test.openweather.presentation.home

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.test.openweather.BuildConfig
import com.test.openweather.R
import com.test.openweather.databinding.ActivityMainBinding
import com.test.openweather.presentation.detail.DetailActivity
import com.test.openweather.presentation.search.SearchActivity
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class HomeActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var cityName = ""
    private var lat = ""
    private var lon = ""
    private var id = ""
    private val viewModel: MainViewModel by viewModel()
    companion object {
        const val CITY = "city"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(binding.root)
        initData()
        observe()
    }

    private fun initData(){
        cityName = intent.getStringExtra(CITY).toString()
        viewModel.getWeather(cityName, BuildConfig.TOKEN)
        binding.pgBar.visibility = View.VISIBLE
        binding.btnSearch.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
        binding.btnMore.setOnClickListener {
            val intent = Intent(this@HomeActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.CITY, binding.location.text)
            intent.putExtra(DetailActivity.LAT, lat)
            intent.putExtra(DetailActivity.LON, lon)
            intent.putExtra(DetailActivity.ID, id)
            startActivity(intent)
        }
        binding.btnFavorite.setOnClickListener{
            val intent = Intent(this@HomeActivity, SavedActivity::class.java)
            startActivity(intent)
            true
        }
    }

    private fun observe(){
        viewModel.apiResponse.observe(this,{
            binding.location.setTextColor(Color.RED)
            binding.location.text = it
            binding.btnMore.isEnabled = false
        })

        viewModel.dataWeather.observe(this,{
                val currentTime = Calendar.getInstance().time
                val celcius = it.main?.temp?.toInt()?.minus(273)
                lat = it.coord?.lat.toString()
                lon = it.coord?.lon.toString()
                id = it.id.toString()
                binding.degree.text = " ${celcius.toString()} \u2103"
                binding.location.text = "${it.name} , ${it.sys?.country}"
                binding.date.text = currentTime.toString()
                binding.condition.text = it.weather[0].main
                binding.humidity.text = "Humidity : ${it.main?.humidity}"
                binding.pressure.text = "Pressure : ${it.main?.pressure}"
                binding.descCondition.text = it.weather[0].description
                binding.pgBar.visibility = View.GONE
                Glide.with(this)
                    .load("http://openweathermap.org/img/w/${it.weather[0].icon}.png")
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(binding.ivPic)

            })
    }
}