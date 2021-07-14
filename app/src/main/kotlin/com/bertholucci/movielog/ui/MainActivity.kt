package com.bertholucci.movielog.ui

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bertholucci.core.base.BaseActivity
import com.bertholucci.movielog.R
import com.bertholucci.movielog.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun init() {
        binding.navigation.setupWithNavController(findNavController(R.id.nav_host_home_fragment))
    }
}