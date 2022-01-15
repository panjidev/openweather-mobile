package com.test.openweather.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.test.openweather.R
import com.test.openweather.databinding.ActivitySplashscreenBinding
import com.test.openweather.presentation.home.HomeActivity
import com.test.openweather.utils.Constant.DELAY

class SplashActivity : AppCompatActivity(){
    private val binding: ActivitySplashscreenBinding by lazy {
        ActivitySplashscreenBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(binding.root)
        val animSlide = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.slide
        )
        binding.ivLogo.animation = animSlide
        val splashTread: Thread = object : Thread(){
            override fun run() {
                try {
                    sleep(DELAY.toLong())
                } catch (e: InterruptedException){
                } finally {
                    val intent = Intent(this@SplashActivity, HomeActivity::class.java)
                    intent.putExtra(HomeActivity.CITY, "bandung")
                    startActivity(intent)
                    finish()
                }
            }
        }
        splashTread.start()
    }

}