package com.test.openweather.presentation.search

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.openweather.databinding.ActivitySearchBinding
import com.test.openweather.presentation.home.HomeActivity

class SearchActivity : AppCompatActivity(){
      private val binding : ActivitySearchBinding by lazy {
          ActivitySearchBinding.inflate(layoutInflater)
      }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(binding.root)
        initData()
    }

    private fun initData(){
         binding.btnSearch.setOnClickListener {
             val intent = Intent(this@SearchActivity, HomeActivity::class.java)
             intent.putExtra(HomeActivity.CITY, binding.search.text.toString())
             startActivity(intent)
         }

    }
}