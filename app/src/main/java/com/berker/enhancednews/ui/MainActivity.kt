package com.berker.enhancednews.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.berker.enhancednews.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initActionBarBackButton()
    }

    private fun initActionBarBackButton() {
        val navController = this.findNavController(R.id.nav_host_fragment_container)
        NavigationUI.setupActionBarWithNavController(
            this,
            navController
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment_container)
        return navController.navigateUp()
    }
}