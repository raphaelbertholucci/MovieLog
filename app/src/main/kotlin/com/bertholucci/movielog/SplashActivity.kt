package com.bertholucci.movielog

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bertholucci.core.base.BaseActivity
import com.bertholucci.core.route.intentToHome
import com.bertholucci.movielog.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun getViewBinding() = ActivitySplashBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed(::navigateToHome, 2500)
    }

    private fun navigateToHome() {
        startActivity(intentToHome())
    }
}