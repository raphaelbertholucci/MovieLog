package com.bertholucci.movielog.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bertholucci.core.base.BaseActivity
import com.bertholucci.core.route.intentToHome
import com.bertholucci.movielog.R
import com.bertholucci.movielog.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed(::navigateToHome, 1500)
    }

    private fun navigateToHome() {
        startActivity(intentToHome())
    }
}