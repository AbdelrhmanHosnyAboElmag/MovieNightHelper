package com.example.movienighthelper.ui.activity

import android.os.Bundle
import androidx.navigation.ui.navigateUp
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.movienighthelper.R
import com.example.movienighthelper.base.BaseActivity
import com.example.movienighthelper.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavLister()
    }

    override fun onResume() {
        super.onResume()
    }

    /**
     * Setup activity navigation controller
     */
    private fun setupNavLister() {
        findNavController(R.id.myNavHostFragment)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.myNavHostFragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}