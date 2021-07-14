package com.bertholucci.movielog.ui

import android.os.Handler
import android.os.Looper
import com.bertholucci.core.base.BaseActivity
import com.bertholucci.core.route.intentToHome
import com.bertholucci.movielog.R
import com.bertholucci.movielog.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun init() {
        Handler(Looper.getMainLooper()).postDelayed(::navigateToHome, 2500)
    }

    private fun navigateToHome() {
        startActivity(intentToHome())
    }
}