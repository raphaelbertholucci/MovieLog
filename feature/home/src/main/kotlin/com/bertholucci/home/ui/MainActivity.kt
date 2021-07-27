package com.bertholucci.home.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bertholucci.core.base.BaseActivity
import com.bertholucci.home.R
import com.bertholucci.home.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.navigation.setupWithNavController(findNavController(R.id.nav_host_home_fragment))
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
}