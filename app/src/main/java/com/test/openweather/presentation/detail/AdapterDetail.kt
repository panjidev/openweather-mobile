package com.test.openweather.presentation.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.openweather.R
import com.test.openweather.databinding.ItemWeatherBinding
import com.test.openweather.model.ForecastResponse

class AdapterDetail (private val listForecast : List<ForecastResponse.Daily>):
        RecyclerView.Adapter<AdapterDetail.ViewHolder>(){

    private var limit = 3

    inner class ViewHolder(itemView: ItemWeatherBinding) : RecyclerView.ViewHolder(itemView.root){
        val binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = listForecast[position]
        val celcius = listItem.temp?.day?.toInt()?.minus(273)
        val min = listItem.temp?.min?.toInt()?.minus(273)
        val max = listItem.temp?.max?.toInt()?.minus(273)
        holder.binding.temp.text = "Forecast : ${min}\u2103 / ${max}℃"
        holder.binding.degree.text = " ${celcius.toString()} \u2103"
        holder.binding.description.text = listItem.weather[0].main
        holder.binding.humidity.text = "Humidity : ${listItem.humidity}"
        holder.binding.pressure.text = "Pressure : ${listItem.pressure}"
        holder.binding.windspeed.text ="WindSpeed : ${listItem.wind_speed}"
        Glide.with(holder.binding.ivPic.context)
            .load("http://openweathermap.org/img/w/${listItem.weather[0].icon}.png")
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.binding.ivPic)
    }

    override fun getItemCount(): Int {
        if(listForecast.size > limit){
            return limit
        }else{
            return listForecast.size
        }
    }

}