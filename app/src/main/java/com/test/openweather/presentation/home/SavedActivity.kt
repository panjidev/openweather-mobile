package com.test.openweather.presentation.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.openweather.databinding.ActivitySavedBinding
import com.test.openweather.model.Favorite
import com.test.openweather.presentation.detail.AdapterFavorite
import com.test.openweather.presentation.detail.FavoriteViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SavedActivity: AppCompatActivity() {

    private val binding: ActivitySavedBinding by lazy {
        ActivitySavedBinding.inflate(layoutInflater)
    }

    private val viewModelFav: FavoriteViewModel by viewModel()
    private val list = ArrayList<Favorite>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initData()
        observe()
    }

    private fun initData(){
        title = "Favorite"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun observe(){
        viewModelFav.getAllFav().observe(this, {
            binding.pgBar.visibility = View.VISIBLE
            list.clear()
            it.let {
                list.addAll(it)
                initList()
            }
        })
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun initList() {
       val adapter = AdapterFavorite(list){
           val intent = Intent(this,HomeActivity::class.java)
           intent.putExtra(HomeActivity.CITY, list[it].city_name)
           startActivity(intent)
       }
        binding.rvItem.layoutManager =  LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        binding.rvItem.adapter = adapter
        adapter.notifyDataSetChanged()
        binding.pgBar.visibility = View.GONE
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}